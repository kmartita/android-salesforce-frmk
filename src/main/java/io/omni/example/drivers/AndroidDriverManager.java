package io.omni.example.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.nio.file.FileSystems;

import static io.omni.example.tools.properties.EnvManager.ANDROID_URL;
import static io.omni.example.tools.properties.EnvManager.APP_NAME;

public class AndroidDriverManager {

    public AndroidDriver setupAndroid(Devices device) {
        AndroidDriver driver;
        String userDir = System.getProperty("user.dir");
        String testAppPath = String.join(FileSystems.getDefault().getSeparator(),
                userDir, "app", APP_NAME);

        DesiredCapabilities desiredCaps = getDesiredCapabilities(device, testAppPath);
        try {
            driver = new AndroidDriver(new URL(ANDROID_URL), desiredCaps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.context("NATIVE_APP");

            ScreenOrientation orientation = driver.getOrientation();
            if (driver.manage().window().getSize().getWidth() < driver.manage().window().getSize().getHeight()) {
                if (orientation.equals(ScreenOrientation.PORTRAIT)) {
                    driver.rotate(ScreenOrientation.LANDSCAPE);
                } else {
                    driver.rotate(ScreenOrientation.PORTRAIT);
                }
            }

            return driver;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static DesiredCapabilities getDesiredCapabilities(Devices device, String testAppPath) {
        DesiredCapabilities desiredCaps = new DesiredCapabilities();
        desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCaps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 45000);
        desiredCaps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Flutter");
        desiredCaps.setCapability(MobileCapabilityType.NO_RESET, true);
        desiredCaps.setCapability(MobileCapabilityType.APP, testAppPath);
        desiredCaps.setCapability(MobileCapabilityType.DEVICE_NAME, device.getLabel());
        desiredCaps.setCapability("unicodeKeyboard", true);
        desiredCaps.setCapability("resetKeyboard", true);
        return desiredCaps;
    }
}
