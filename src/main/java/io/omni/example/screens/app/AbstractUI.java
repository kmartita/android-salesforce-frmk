package io.omni.example.screens.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.omni.example.drivers.DriverManager;
import io.omni.example.tools.utilities.MobileElementUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Collections;

import static io.omni.example.tools.ExpectedConditionTool.waitUntilClickable;

public abstract class AbstractUI {

    public final static String VIEW_EQUALS_LOCATOR = "//android.view.View[@content-desc=\"%s\"]";
    public final static String VIEW_CONTAINS_LOCATOR = "//android.view.View[contains(@content-desc,\"%s\")]";

    protected static AppiumDriver driver;
    protected final int screenHeight;
    protected final int screenWidth;
    private MobileElementUtils mobileElementUtils;

    public AbstractUI() {
        driver = DriverManager.getDriver();
        screenWidth = driver.manage().window().getSize().getWidth();
        screenHeight = driver.manage().window().getSize().getHeight();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

    protected MobileElementUtils mobileElementUtils() {
        return mobileElementUtils != null ? mobileElementUtils : (mobileElementUtils = MobileElementUtils.getInstance());
    }

    public void click(WebElement element) {
        try {
            waitUntilClickable(element).click();
        } catch (StaleElementReferenceException e) {
            waitUntilClickable(element).click();
        }
    }

    public void click(By locator) {
        try {
            waitUntilClickable(locator).click();
        } catch (StaleElementReferenceException e) {
            waitUntilClickable(locator).click();
        }
    }

    public void click(By locator, int seconds) {
        try {
            waitUntilClickable(locator, seconds).click();
        } catch (StaleElementReferenceException e) {
            waitUntilClickable(locator, seconds).click();
        }
    }

    @Step("Type {value} into field")
    public void type(WebElement element, String value) {
        click(element);
        sendKeys(value);
    }

    public void sendKeys(String text) {
        ((AndroidDriver) driver).context("FLUTTER");
        driver.executeScript("flutter:enterText", text);
        ((AndroidDriver) driver).context("NATIVE_APP");
    }

    @Step
    public void tap(Point point) {
        PointerInput pointer = new PointerInput(PointerInput.Kind.TOUCH, "test");
        Sequence a = new Sequence(pointer, 1);
        a.addAction(pointer.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), point.x, point.y));
        a.addAction(pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        a.addAction(pointer.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), point.x, point.y));
        a.addAction(pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(a));
    }

    @Step
    public String getValueFromElement(WebElement element) {
        return mobileElementUtils().getValueFromElement(element);
    }

    @Step
    public boolean isElementDisplayed(WebElement element) {
        return mobileElementUtils().isElementShown(element);
    }

    @Step
    public boolean isElementDisplayed(By locator) {
        return mobileElementUtils().isElementShown(locator);
    }
}
