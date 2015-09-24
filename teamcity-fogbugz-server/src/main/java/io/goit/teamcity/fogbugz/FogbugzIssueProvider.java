package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.issueTracker.AbstractIssueProvider;
import jetbrains.buildServer.issueTracker.IssueFetcher;
import jetbrains.buildServer.issueTracker.IssueProviderType;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;

public class FogbugzIssueProvider extends AbstractIssueProvider {
    public FogbugzIssueProvider(@NotNull final IssueProviderType type, @NotNull IssueFetcher fetcher) {
        super(type.getType(), fetcher);
    }

    @Override
    @NotNull
    protected String extractId(@NotNull String match) {
        Matcher matcher = myPattern.matcher(match);
        matcher.find();
        return matcher.group(1);
    }
}
