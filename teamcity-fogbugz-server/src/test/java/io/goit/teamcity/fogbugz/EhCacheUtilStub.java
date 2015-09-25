package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.ServerPaths;
import jetbrains.buildServer.util.EventDispatcher;
import jetbrains.buildServer.util.cache.EhCacheUtil;
import jetbrains.buildServer.util.cache.ResetCacheRegisterImpl;
import net.sf.ehcache.Cache;
import org.jetbrains.annotations.NotNull;

public class EhCacheUtilStub extends EhCacheUtil {

    public EhCacheUtilStub() {
        super(new ServerPaths("", "", "", ""), EventDispatcher.create(BuildServerListener.class), new ResetCacheRegisterImpl());
    }

    @NotNull
    @Override
    public Cache createCache(@NotNull String name) {
        return createInMemoryCache(name);
    }
}
