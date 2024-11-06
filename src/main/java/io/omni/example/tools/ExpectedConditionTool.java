package io.omni.example.tools;

import io.omni.example.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Supplier;

public class ExpectedConditionTool {

    static WebDriverWait wait;
    private static final int SHORT_TIMEOUT = 5;

    public static void initWait() {
        wait = (WebDriverWait) new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(StaleElementReferenceException.class);
    }

    public static WebElement waitUntilVisible(WebElement element) {
        return wait
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilVisible(WebElement element, int seconds) {
        return wait
                .withTimeout(Duration.of(seconds, ChronoUnit.SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilVisible(By locator) {
        return wait
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitUntilVisible(By locator, int seconds) {
        return wait
                .withTimeout(Duration.of(seconds, ChronoUnit.SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitUntilAllVisible(By locator) {
        return wait
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static WebElement waitUntilClickable(WebElement element) {
        return wait
                .withTimeout(Duration.of(20, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitUntilClickable(By locator) {
        return wait
                .withTimeout(Duration.of(20, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitUntilClickable(By locator, int seconds) {
        return wait
                .withTimeout(Duration.of(seconds, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitUntilCondition(Supplier<Boolean> condition) {
        wait
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .until(driver -> condition.get());
    }

    public static void waitUntilCondition(Supplier<Boolean> condition, int seconds) {
        wait
                .withTimeout(Duration.of(seconds, ChronoUnit.SECONDS))
                .until(driver -> condition.get());
    }

    public static boolean tryWaitUntilCondition(Supplier<Boolean> condition) {
        try {
            return wait
                    .withTimeout(Duration.of(SHORT_TIMEOUT, ChronoUnit.SECONDS))
                    .until(driver -> condition.get());
        } catch (TimeoutException e) {
            return false;
        }
    }
}
