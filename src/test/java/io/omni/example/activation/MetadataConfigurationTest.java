package io.omni.example.activation;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

public class MetadataConfigurationTest {

    private static final String FIRST_PRIORITY_CONFIGURATION = "runApplicationActivations";

    @BeforeSuite
    public void beforeSuiteConfigTest(ITestContext iTestContext) {
        XmlSuite suite = iTestContext.getSuite().getXmlSuite();
        suite.setThreadCount(1);
        suite.setGroupByInstances(Boolean.TRUE);
    }

    @Test
    public void runApplicationActivations() {
        ApplicationActivation applicationActivation = new ApplicationActivation();
        applicationActivation.configureOrganizationApplicationSettings();
        applicationActivation.configureApplicationSettingsForSalesRepProfile();
    }

    @Test(dependsOnMethods = FIRST_PRIORITY_CONFIGURATION, alwaysRun = true)
    public void runAccountActivations() {
        new AccountActivation().activateAccounts();
    }

    @Test(dependsOnMethods = FIRST_PRIORITY_CONFIGURATION, alwaysRun = true)
    public void runAccountFilterActivations() {
        new AccountFilterActivation().activateAccountFilters();
    }
}
