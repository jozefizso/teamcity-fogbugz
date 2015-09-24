package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.issueTracker.AbstractIssueFetcher;
import jetbrains.buildServer.issueTracker.IssueData;
import jetbrains.buildServer.util.cache.EhCacheUtil;
import org.apache.commons.httpclient.Credentials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FogbugzIssueFetcher extends AbstractIssueFetcher {
    public FogbugzIssueFetcher(@NotNull EhCacheUtil cacheUtil) {
        super(cacheUtil);
    }

    @NotNull
    public IssueData getIssue(@NotNull String host, @NotNull String id, @Nullable final Credentials credentials) throws Exception {
        return null;
    }

    @NotNull
    public String getUrl(@NotNull String host, @NotNull String id) {
        return null;
    }
}
