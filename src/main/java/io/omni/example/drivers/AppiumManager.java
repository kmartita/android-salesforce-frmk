package io.omni.example.drivers;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.IOException;

public class AppiumManager {

    public static final AppiumDriverLocalService appium = new AppiumServiceBuilder()
            .usingPort(4723)
            .withArgument(() -> "--base-path", "/wd/hub")
            .build();

    public static void startAppiumServer(){
        try {
            Runtime.getRuntime().exec(String.format("emulator @%s -wipe-data", Devices.SAMSUNG_GALAXY_TAB_S8.getLabel()));
            Thread.sleep(20000);  //TODO change this wait
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        appium.start();
        appium.clearOutPutStreams();
    }

    public static void killAppiumServer() {
        appium.stop();
    }
}
