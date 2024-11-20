package io.omni.example.mobile;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.omni.example.drivers.DriverManager;
import io.omni.example.helpers.SessionHelper;
import io.omni.example.models.SfUserModel;
import io.omni.example.tools.ExpectedConditionTool;
import io.omni.example.tools.TestListener;
import io.omni.example.tools.api.HasLabel;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.MobileAppUpgradeConfiguration;
import io.omni.example.tools.properties.EnvManager;
import io.omni.example.tools.properties.PropertyManager;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static io.omni.example.drivers.Devices.SAMSUNG_GALAXY_TAB_S8;
import static io.omni.example.helpers.SessionManager.getSession;
import static io.omni.example.tools.properties.EnvManager.APP_BUNDLE_ID;
import static io.omni.example.tools.properties.PropertyManager.*;
import static java.lang.String.format;

@Listeners({TestListener.class})
public class TestBase {

    protected final static SfUserModel FIRST_SALES_REP = getFirstSalesRepUser();
    protected final static String FIRST_SALES_REP_TERRITORY = getTerritoryForFirstSalesRep();

    protected static SessionHelper sessionHelper;
    public SoftAssert soft;

    @BeforeSuite(alwaysRun = true)
    public void initAppium() {
        MobileAppUpgradeConfiguration.updateMobileAppReleaseDate();
        DriverManager.startServer();
        sessionHelper = getSession();
    }

    @BeforeClass(alwaysRun = true)
    public void init() {
        ((AndroidDriver) DriverManager.getDriver()).activateApp(APP_BUNDLE_ID);
        ExpectedConditionTool.initWait();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        soft = new SoftAssert();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        ((AndroidDriver) DriverManager.getDriver()).terminateApp(APP_BUNDLE_ID);
        DriverManager.resetDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void killAppium() {
        AllureEnvironmentWriter.allureEnvironmentWriter(ImmutableMap.<String, String>builder()
                .put("Device", SAMSUNG_GALAXY_TAB_S8.getName())
                .put("Env", EnvManager.BASE_URL)
                .build(), System.getProperty("user.dir")
                + "/allure-results/");
        DriverManager.resetSimulator();
        DriverManager.killServer();
    }

    @Step("Compare models: actual {actual} with expected {expected}")
    public <Field extends Enum<Field> & HasName & HasLabel> void generalModelsShouldBeEqual(GenericModel<Field> actual,
                                                                                            GenericModel<Field> expected) {

        actual.includedFields().forEach(field ->
                soft.assertEquals(actual.get(field), expected.get(field),
                        format("\nNot equal values for field '%s':\n", field)));
        soft.assertAll();
    }
}
