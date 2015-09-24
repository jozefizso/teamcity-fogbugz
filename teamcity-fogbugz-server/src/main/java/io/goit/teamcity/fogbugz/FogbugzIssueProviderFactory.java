package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.issueTracker.AbstractIssueProviderFactory;
import jetbrains.buildServer.issueTracker.IssueFetcher;
import jetbrains.buildServer.issueTracker.IssueProvider;
import jetbrains.buildServer.issueTracker.IssueProviderType;
import org.jetbrains.annotations.NotNull;

public class FogbugzIssueProviderFactory extends AbstractIssueProviderFactory {
    public FogbugzIssueProviderFactory(@NotNull final IssueProviderType type, @NotNull IssueFetcher fetcher) {
        super(type, fetcher);
    }

    @NotNull
    public IssueProvider createProvider() {
        return new FogbugzIssueProvider(this.myType, this.myFetcher);
    }
}
