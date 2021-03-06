package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.issueTracker.AbstractIssueProvider;
import jetbrains.buildServer.issueTracker.IssueFetcher;
import jetbrains.buildServer.issueTracker.IssueProviderType;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FogbugzIssueProvider extends AbstractIssueProvider {
    public FogbugzIssueProvider(@NotNull final IssueProviderType type, @NotNull IssueFetcher fetcher) {
        super(type.getType(), fetcher);
    }

    /**
     * Special overload for unit testing the pattern matching.
     */
    protected FogbugzIssueProvider(@NotNull Pattern pattern, @NotNull IssueFetcher fetcher) {
        super(pattern, fetcher);
    }

    @Override
    @NotNull
    protected String extractId(@NotNull String match) {
        Matcher matcher = myPattern.matcher(match);
        matcher.find();
        if (matcher.groupCount() >= 1) {
            return matcher.group(1);
        }

        return "";
    }

    @Override
    protected boolean useIdPrefix() {
        return super.useIdPrefix();
    }
}
