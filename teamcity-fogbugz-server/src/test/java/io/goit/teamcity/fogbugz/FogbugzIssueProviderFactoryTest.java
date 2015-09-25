package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.issueTracker.IssueProvider;
import jetbrains.buildServer.serverSide.ServerPaths;
import jetbrains.buildServer.util.cache.EhCacheUtil;
import jetbrains.buildServer.util.cache.EhCacheUtilBase;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Expectation;
import org.jmock.api.Invocation;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FogbugzIssueProviderFactoryTest {
    @Test
    public void testCreateProvider() throws Exception {
        FogbugzIssueProviderFactory factory = createFactory();

        FogbugzIssueProvider provider = (FogbugzIssueProvider)factory.createProvider();

        assertEquals(provider.getType(), FogbugzConstants.FOGBUGZ_PROVIDER_TYPE);
    }

    private FogbugzIssueProviderFactory createFactory() {
        PluginDescriptor descriptor = new PluginDescriptorStub();
        FogbugzIssueProviderType providerType = new FogbugzIssueProviderType(descriptor);
        FogbugzIssueFetcher fetcher = new FogbugzIssueFetcher(new EhCacheUtilStub());

        FogbugzIssueProviderFactory factory = new FogbugzIssueProviderFactory(providerType, fetcher);
        return factory;
    }
}
