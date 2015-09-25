package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public class PluginDescriptorStub implements PluginDescriptor {
    @NotNull
    public String getPluginName() {
        return null;
    }

    @NotNull
    public String getPluginResourcesPath() {
        return null;
    }

    @NotNull
    public String getPluginResourcesPath(@NotNull String relativePath) {
        return "/teamcity/resources/" + relativePath;
    }

    @Nullable
    public String getParameterValue(@NotNull String key) {
        return null;
    }

    @Nullable
    public String getPluginVersion() {
        return null;
    }

    @NotNull
    public File getPluginRoot() {
        return null;
    }
}
