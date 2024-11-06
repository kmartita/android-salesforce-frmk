package io.omni.example.tools.utilities;

import java.util.concurrent.TimeUnit;

public class WaitUtils {

    private static void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private static void waitFor(long time, TimeUnit timeUnit) {
        long millis = TimeUnit.MILLISECONDS.convert(time, timeUnit);
        waitFor(millis);
    }

    public static void waitForSeconds(long seconds) {
        waitFor(seconds, TimeUnit.SECONDS);
    }

    public static void waitForMilliSeconds(long seconds) {
        waitFor(seconds, TimeUnit.MILLISECONDS);
    }

}
