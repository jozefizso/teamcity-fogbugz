package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.issueTracker.AbstractIssueFetcher;
import jetbrains.buildServer.issueTracker.IssueData;
import jetbrains.buildServer.util.cache.EhCacheUtil;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FogbugzIssueFetcher extends AbstractIssueFetcher {
    public FogbugzIssueFetcher(@NotNull EhCacheUtil cacheUtil) {
        super(cacheUtil);
    }

    public IssueData getIssue(@NotNull final String host, @NotNull final String id, @Nullable final Credentials credentials) throws Exception {
        String url = getUrl(host, id);
        return getFromCacheOrFetch(url, new FogbugzFetchFunction(host, id, credentials));
    }

    @NotNull
    public String getUrl(@NotNull final String host, @NotNull final String id) {
        return appendTrailingSlash(host) + FogbugzConstants.FOGBUGZ_DEFAULT_PAGE_URL + "?" + id;
    }

    private String appendTrailingSlash(@NotNull String host) {
        if (host.endsWith("/")) {
            return host;
        }

        return host + "/";
    }

    public class FogbugzFetchFunction implements AbstractIssueFetcher.FetchFunction {
        private String host;
        private String id;
        private Credentials credentials;

        public FogbugzFetchFunction(@NotNull final String host, @NotNull final String id, @Nullable final Credentials credentials) {
            this.host = appendTrailingSlash(host);
            this.id = id;
            this.credentials = credentials;
        }

        @NotNull
        public IssueData fetch() throws Exception {
            String token = null;

            try {
                String loginUrl = getLogonUrl(host, credentials);
                InputStream data = fetchHttpFile(loginUrl, credentials);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(data);
                doc.getDocumentElement().normalize();
                Node element = doc.getFirstChild().getFirstChild();
                if (element.getNodeName() == "error") {
                    throw new RuntimeException(element.getTextContent());
                }

                token = element.getTextContent();
                String caseUrl = getCaseUrl(host, id, token);

                data = fetchHttpFile(caseUrl, credentials);
                doc = db.parse(data);
                element = doc.getFirstChild().getFirstChild();
                if (element.getNodeName() == "error") {
                    throw new RuntimeException(element.getTextContent());
                }

                String summary;
                String status = "";
                Boolean resolved = false;

                Node fbCase = element.getFirstChild();
                if (fbCase == null) {
                    summary = String.format("Case #%s does not exist.", id);
                } else {
                    Node summaryNode = fbCase.getFirstChild();
                    Node statusNode = summaryNode.getNextSibling();
                    Node resolvedNode = summaryNode.getNextSibling();

                    summary = summaryNode.getTextContent();
                    status = statusNode.getTextContent();
                    resolved = !"".equals(resolvedNode.getTextContent());
                }

                return new IssueData(id, summary, status, getUrl(host, id), resolved);
            } finally {
                if (null != token) {
                    fetchHttpFile(getLogoffUrl(host, token), credentials);
                }
            }
        }

        private String getLogonUrl(String host, Credentials credentials) throws UnsupportedEncodingException {
            UsernamePasswordCredentials localCredentials = (UsernamePasswordCredentials)credentials;
            String username = URLEncoder.encode(localCredentials.getUserName(), "UTF-8");
            String password = URLEncoder.encode(localCredentials.getPassword(), "UTF-8");
            return host + "api.asp?cmd=logon&email=" + username + "&password=" + password;
        }

        private String getLogoffUrl(String host, String token) {
            return host + "api.asp?cmd=logoff&token=" + token;
        }

        private String getCaseUrl(String host, String id, String token) throws UnsupportedEncodingException {
            id = URLEncoder.encode(id, "UTF-8");
            return host + "api.asp?cmd=search&cols=sLatestTextSummary,sStatus,dtResolved&q=" + id + "&token=" + token;
        }
    }
}
