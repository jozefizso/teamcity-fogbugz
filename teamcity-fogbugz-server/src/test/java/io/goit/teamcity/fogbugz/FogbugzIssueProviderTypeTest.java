package io.goit.teamcity.fogbugz;

import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FogbugzIssueProviderTypeTest {
    FogbugzIssueProviderType providerType;

    @BeforeTest
    public void beforeTest() {
        PluginDescriptor descriptor = new PluginDescriptorStub();
        this.providerType = new FogbugzIssueProviderType(descriptor);
    }

    @Test
    public void testGetType() throws Exception {
        // Act
        String actualType = this.providerType.getType();

        // Assert
        assertEquals(actualType, FogbugzConstants.FOGBUGZ_PROVIDER_TYPE);
    }

    @Test
    public void testGetDisplayName() throws Exception {
        // Act
        String actualDisplayName = this.providerType.getDisplayName();

        // Assert
        assertEquals(actualDisplayName, FogbugzConstants.FOGBUGZ_DISPLAY_NAME);
    }

    @Test
    public void testGetEditParametersUrl() throws Exception {
        // Act
        String actualEditUrl = this.providerType.getEditParametersUrl();

        // Assert
        assertEquals(actualEditUrl, "/teamcity/resources/admin/editIssueProvider.jsp");
    }

    @Test
    public void testGetIssueDetailsUrl() throws Exception {
        // Act
        String actualIssuesUrl = this.providerType.getIssueDetailsUrl();

        // Assert
        assertEquals(actualIssuesUrl, "/teamcity/resources/popup.jsp");
    }
}
