package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.issueTracker.IssueData;
import jetbrains.buildServer.util.cache.EhCacheUtil;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.parsers.DocumentBuilder;
import java.io.InputStream;

import static org.testng.Assert.assertEquals;

public class FogbugzIssueFetcherTest {
    @Test(dataProvider = "hostWithoutTrailingSlash")
    public void testGetUrl_HostWithoutTrailingSlash(String host, String expectedUrl) throws Exception {
        // Arrange
        FogbugzIssueFetcher fetcher = createFetcher();

        // Act
        String actualUrl = fetcher.getUrl(host, "123");

        // Assert
        assertEquals(actualUrl, expectedUrl);
    }

    @Test(dataProvider = "hostWithTrailingSlash")
    public void testGetUrl_HostWithTrailingSlash(String host, String expectedUrl) throws Exception {
        // Arrange
        FogbugzIssueFetcher fetcher = createFetcher();

        // Act
        String actualUrl = fetcher.getUrl(host, "456");

        // Assert
        assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testParseXml_resolvedFeatureRequest() throws Exception {
        // Arrange
        String xml = "<response>" +
            "<cases count=\"1\">" +
            "<case ixBug=\"123456\" operations=\"edit,reopen,email,remind\">" +
            "<sTitle><![CDATA[Case Title]]></sTitle>" +
            "<sStatus><![CDATA[Closed (Already Exists)]]></sStatus>" +
            "<sPriority><![CDATA[Should fix]]></sPriority>" +
            "<dtResolved>2015-09-07T16:35:35Z</dtResolved>" +
            "<sCategory><![CDATA[Feature]]></sCategory>" +
            "</case>" +
            "</cases>" +
            "</response>";

        FogbugzIssueFetcher.FogbugzFetchFunction fetcher = createFetcherFunction();
        InputStream data = IOUtils.toInputStream(xml, "UTF-8");
        DocumentBuilder db = fetcher.createDocumentBuilder();

        // Act
        IssueData actualIssue = fetcher.parseXml(data, db);

        // Assert
        assertEquals(actualIssue.getId(), "12345");
        assertEquals(actualIssue.getSummary(), "Case Title");
        assertEquals(actualIssue.getType(), "Feature");
        assertEquals(actualIssue.isResolved(), true);
        assertEquals(actualIssue.isFeatureRequest(), true);
    }

    @Test
    public void testParseXml_unresolvedBug() throws Exception {
        // Arrange
        String xml = "<response>" +
            "<cases count=\"1\">" +
            "<case ixBug=\"123456\" operations=\"edit,reopen,email,remind\">" +
            "<sTitle><![CDATA[Bug in program]]></sTitle>" +
            "<sStatus><![CDATA[Active (Working On)]]></sStatus>" +
            "<sPriority><![CDATA[Should fix]]></sPriority>" +
            "<dtResolved />" +
            "<sCategory><![CDATA[Bug]]></sCategory>" +
            "</case>" +
            "</cases>" +
            "</response>";

        FogbugzIssueFetcher.FogbugzFetchFunction fetcher = createFetcherFunction();
        InputStream data = IOUtils.toInputStream(xml, "UTF-8");
        DocumentBuilder db = fetcher.createDocumentBuilder();

        // Act
        IssueData actualIssue = fetcher.parseXml(data, db);

        // Assert
        assertEquals(actualIssue.getId(), "12345");
        assertEquals(actualIssue.getSummary(), "Bug in program");
        assertEquals(actualIssue.getType(), "Bug");
        assertEquals(actualIssue.isResolved(), false);
        assertEquals(actualIssue.isFeatureRequest(), false);
    }

    private static FogbugzIssueFetcher createFetcher() {
        EhCacheUtil cache = new EhCacheUtilStub();
        FogbugzIssueFetcher fetcher = new FogbugzIssueFetcher(cache);

        return fetcher;
    }

    private static FogbugzIssueFetcher.FogbugzFetchFunction createFetcherFunction() {
        EhCacheUtil cache = new EhCacheUtilStub();
        FogbugzIssueFetcher fetcher = new FogbugzIssueFetcher(cache);

        Credentials credentials = new UsernamePasswordCredentials("user", "password");
        FogbugzIssueFetcher.FogbugzFetchFunction function = fetcher.new FogbugzFetchFunction("example.fogbugz.com", "12345", credentials);

        return function;
    }

    @DataProvider(name = "hostWithoutTrailingSlash")
    public Object[][] provideHostWithoutTrailingSlash() {
        return new Object[][] {
            { "https://example.fogbugz.com", "https://example.fogbugz.com/default.asp?123" },
            { "https://dev.example.com/fb", "https://dev.example.com/fb/default.asp?123" },
        };
    }

    @DataProvider(name = "hostWithTrailingSlash")
    public Object[][] provideHostWithTrailingSlash() {
        return new Object[][] {
            { "https://example.fogbugz.com/", "https://example.fogbugz.com/default.asp?456" },
            { "https://dev.example.com/fb/", "https://dev.example.com/fb/default.asp?456" },
        };
    }

}
