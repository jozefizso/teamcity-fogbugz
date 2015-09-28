package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class FogbugzIssueProviderTest {

    @Test
    public void testConstructor() {
        // Arrange
        PluginDescriptorStub pluginDescriptor = new PluginDescriptorStub();
        FogbugzIssueProviderType providerType = new FogbugzIssueProviderType(pluginDescriptor);
        FogbugzIssueFetcher fetcher = new FogbugzIssueFetcher(new EhCacheUtilStub());
        String expectedType = providerType.getType();

        // Act
        FogbugzIssueProvider provider = new FogbugzIssueProvider(providerType, fetcher);
        String actualType = provider.getType();

        // Assert
        assertEquals(actualType, expectedType);
    }

    @Test
    public void testExtractId_returnInputValue() {
        // Arrange
        FogbugzIssueProvider provider = createProvider("(?i)(?<=bugzid:? )(\\d+)");

        // Act
        String actualId = provider.extractId("BugzId: 123456");

        // Assert
        assertEquals(actualId, "123456");
    }

    @Test
    public void testUseIdPrefix() {
        // Arrange
        FogbugzIssueProvider provider = createProvider(".*");

        // Act
        boolean actualValue = provider.useIdPrefix();

        // Assert
        assertFalse(actualValue);
    }

    @NotNull
    private static FogbugzIssueProvider createProvider(String pattern) {
        Pattern compiled = Pattern.compile(pattern);
        FogbugzIssueFetcher fetcher = new FogbugzIssueFetcher(new EhCacheUtilStub());

        return new FogbugzIssueProvider(compiled, fetcher);
    }
}
