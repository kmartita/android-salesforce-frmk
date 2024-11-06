package io.omni.example.drivers;

import io.appium.java_client.AppiumDriver;
import java.io.IOException;

public class DriverManager {

    static AppiumDriver driver;

    public static AppiumDriver getDriver() {
        if (null == driver){
            driver = new AndroidDriverManager().setupAndroid(Devices.SAMSUNG_GALAXY_TAB_S8);
        }
        return driver;
    }

    public static void resetDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    public static void resetSimulator() {
        try {
            Runtime.getRuntime().exec("adb emu kill");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startServer() {
        AppiumManager.startAppiumServer();
    }

    public static void killServer() {
        AppiumManager.killAppiumServer();
    }

}
