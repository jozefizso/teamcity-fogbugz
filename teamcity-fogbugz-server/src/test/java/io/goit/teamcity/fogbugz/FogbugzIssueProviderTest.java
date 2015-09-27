package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class FogbugzIssueProviderTest {

    @Test
    public void testExtractId_returnInputValue() {
        // Arrange
        FogbugzIssueProvider provider = createProvider("(?i)(?<=bugzid:? )(\\d+)");

        // Act
        String actualId = provider.extractId("BugzId: 123456");

        // Assert
        assertEquals(actualId, "BugzId: 123456");
    }

    @NotNull
    private static FogbugzIssueProvider createProvider(String pattern) {
        Pattern compiled = Pattern.compile(pattern);
        FogbugzIssueFetcher fetcher = new FogbugzIssueFetcher(new EhCacheUtilStub());

        return new FogbugzIssueProvider(compiled, fetcher);
    }
}
