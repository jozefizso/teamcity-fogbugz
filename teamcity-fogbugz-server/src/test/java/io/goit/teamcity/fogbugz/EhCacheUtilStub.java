package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.ServerPaths;
import jetbrains.buildServer.util.EventDispatcher;
import jetbrains.buildServer.util.cache.EhCacheUtil;
import jetbrains.buildServer.util.cache.ResetCacheRegisterImpl;
import net.sf.ehcache.Cache;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EhCacheUtilStub extends EhCacheUtil {

    public EhCacheUtilStub() {
        super(new ServerPaths("", "", "", ""), EventDispatcher.create(BuildServerListener.class), new ResetCacheRegisterImpl());
    }

    @NotNull
    @Override
    public Cache createCache(@NotNull String name) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("%MAX_ELEMS_IN_MEMORY%", "1000");
        params.put("%TTL_SECONDS%", "3600");
        params.put("%TTL_IDLE_SECONDS%", "3600");
        return this.createCache("<ehcache>" +
            "  <defaultCache" +
            "    maxElementsInMemory=\"%MAX_ELEMS_IN_MEMORY%\"" +
            "    eternal=\"false\"" +
            "    timeToIdleSeconds=\"%TTL_IDLE_SECONDS%\"" +
            "    timeToLiveSeconds=\"%TTL_SECONDS%\"" +
            "    overflowToDisk=\"false\"" +
            "    diskPersistent=\"false\"/>" +
            "</ehcache>", name, params);
    }
}
