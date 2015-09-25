package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.issueTracker.IssueProvider;
import jetbrains.buildServer.serverSide.ServerPaths;
import jetbrains.buildServer.util.cache.EhCacheUtil;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Expectation;
import org.jmock.api.Invocation;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FogbugzIssueProviderFactoryTest {
    Mockery mockery = new Mockery();

    @Test
    public void testCreateProvider() throws Exception {
        FogbugzIssueProviderFactory factory = createFactory();

        FogbugzIssueProvider provider = (FogbugzIssueProvider)factory.createProvider();

        assertEquals(provider.getType(), FogbugzConstants.FOGBUGZ_PROVIDER_TYPE);
    }

    private FogbugzIssueProviderFactory createFactory() {
        final PluginDescriptor descriptor = mockery.mock(PluginDescriptor.class);
        mockery.checking(new Expectations() {{
            allowing(descriptor).getPluginResourcesPath(with(any(String.class)));
        }});

        FogbugzIssueProviderType providerType = new FogbugzIssueProviderType(descriptor);
        FogbugzIssueFetcher fetcher = new FogbugzIssueFetcher(null);
        FogbugzIssueProviderFactory factory = new FogbugzIssueProviderFactory(providerType, fetcher);

        return factory;
    }
}
