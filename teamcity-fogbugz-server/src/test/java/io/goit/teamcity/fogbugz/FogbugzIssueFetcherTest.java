package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.util.cache.EhCacheUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FogbugzIssueFetcherTest {
    @Test(dataProvider = "hostWithoutTrailingSlash")
    public void testGetUrl_HostWithoutTrailingSlash(String host, String expectedUrl) throws Exception {
        // Arrange
        FogbugzIssueFetcher fetcher = createFetcher();

        // Act
        String actualUrl = fetcher.getUrl(host, "123");

        // Assert
        assertEquals(actualUrl, expectedUrl);
    }

    @Test(dataProvider = "hostWithTrailingSlash")
    public void testGetUrl_HostWithTrailingSlash(String host, String expectedUrl) throws Exception {
        // Arrange
        FogbugzIssueFetcher fetcher = createFetcher();

        // Act
        String actualUrl = fetcher.getUrl(host, "456");

        // Assert
        assertEquals(actualUrl, expectedUrl);
    }

    private static FogbugzIssueFetcher createFetcher() {
        EhCacheUtil cache = new EhCacheUtilStub();
        FogbugzIssueFetcher fetcher = new FogbugzIssueFetcher(cache);

        return fetcher;
    }

    @DataProvider(name = "hostWithoutTrailingSlash")
    public Object[][] provideHostWithoutTrailingSlash() {
        return new Object[][] {
            { "https://example.fogbugz.com", "https://example.fogbugz.com/default.asp?123" },
            { "https://dev.example.com/fb", "https://dev.example.com/fb/default.asp?123" },
        };
    }

    @DataProvider(name = "hostWithTrailingSlash")
    public Object[][] provideHostWithTrailingSlash() {
        return new Object[][] {
            { "https://example.fogbugz.com/", "https://example.fogbugz.com/default.asp?456" },
            { "https://dev.example.com/fb/", "https://dev.example.com/fb/default.asp?456" },
        };
    }

}
