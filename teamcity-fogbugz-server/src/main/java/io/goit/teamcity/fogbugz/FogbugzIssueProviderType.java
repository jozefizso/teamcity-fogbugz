package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.issueTracker.IssueProviderType;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;

public class FogbugzIssueProviderType extends IssueProviderType {
    @NotNull
    private final String myConfigUrl;
    @NotNull
    private final String myPopupUrl;

    public FogbugzIssueProviderType(@NotNull final PluginDescriptor pluginDescriptor) {
        myConfigUrl = pluginDescriptor.getPluginResourcesPath("buildServerResources/editIssueProvider.jsp");
        myPopupUrl = pluginDescriptor.getPluginResourcesPath("popup.jsp");
    }

    @NotNull
    @Override
    public String getType() {
        return "fogbugz";
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "FogBugz";
    }

    @NotNull
    @Override
    public String getEditParametersUrl() {
        return myConfigUrl;
    }

    @NotNull
    @Override
    public String getIssueDetailsUrl() {
        return myPopupUrl;
    }
}
