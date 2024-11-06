package io.omni.example.tools.utilities;

import io.appium.java_client.AppiumDriver;
import io.omni.example.drivers.DriverManager;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.Objects;

import static io.omni.example.tools.ExpectedConditionTool.waitUntilClickable;
import static io.omni.example.tools.ExpectedConditionTool.waitUntilVisible;

public class MobileElementUtils {

    public final static int DEFAULT_HORIZONTAL_SWIPE_ATTEMPTS = 7;
    public final static int DEFAULT_VERTICAL_SWIPE_ATTEMPTS = 5;
    protected static AppiumDriver driver;
    protected final int screenHeight;
    protected final int screenWidth;
    private final Point centerOfScreen;
    private final Point leftOfScreen;

    public MobileElementUtils() {
        driver = DriverManager.getDriver();
        screenWidth = driver.manage().window().getSize().getWidth();
        screenHeight = driver.manage().window().getSize().getHeight();
        centerOfScreen = new Point(screenWidth / 2, screenHeight / 2);
        leftOfScreen = new Point(10, screenHeight / 2);
    }

    public static MobileElementUtils getInstance() {
        return new MobileElementUtils();
    }

    @Step
    public boolean isElementShown(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    @Step
    public boolean isElementShown(WebElement element, int seconds) {
        try {
            waitUntilVisible(element, seconds);
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step
    public boolean isElementShown(By locator, int seconds) {
        try {
            waitUntilVisible(locator, seconds);
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step
    public boolean isElementShown(By locator) {
        try {
            waitUntilVisible(locator);
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step
    public boolean isElementClickable(WebElement element) {
        try {
            waitUntilClickable(element);
            return true;
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    @Step
    public boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        } catch (StaleElementReferenceException e) {
            return isElementEnabled(element);
        }
    }

    @Step
    public boolean isElementClickableByAttribute(WebElement element) {
        return element.getAttribute("clickable").equals("true");
    }

    @Step
    public boolean isCheckBoxChecked(WebElement element) {
        return element.getAttribute("checked").equals("true");
    }

    @Step
    public String getValueFromElement(WebElement element) {
        waitUntilVisible(element);
        return element.getAttribute("content-desc");
    }

    @Step
    public String getTextFromElement(WebElement element) {
        String value = element.getAttribute("text");
        return Objects.requireNonNullElse(value, StringUtils.EMPTY);
    }

    @Step
    public String getHintFromElement(WebElement element) {
        String value = element.getAttribute("hint");
        return Objects.requireNonNullElse(value, StringUtils.EMPTY);
    }

    @Step
    public void swipe(Point from, Point to) {
        PointerInput pointer = new PointerInput(PointerInput.Kind.TOUCH, "test");
        Sequence a = new Sequence(pointer, 1);
        a.addAction(pointer.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), from.x, from.y));
        a.addAction(pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        a.addAction(pointer.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), to.x, to.y));
        a.addAction(pointer.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(a));
    }

    @Step
    public void swipe(WebElement from, WebElement to) {
        swipe(from.getRect().getPoint().moveBy(from.getRect().getWidth() / 2, from.getRect().getHeight() / 2),
                to.getRect().getPoint().moveBy(to.getRect().getWidth() / 2, to.getRect().getHeight() / 2));
    }

    @Step
    public void sign(WebElement areaForSigning) {
        waitUntilVisible(areaForSigning);
        Rectangle rect = areaForSigning.getRect();
        swipe(rect.getPoint().moveBy(rect.getWidth() * 5 / 10, rect.getHeight() * 5 / 10),
                rect.getPoint().moveBy(rect.getWidth() * 9 / 10, rect.getHeight() / 10));
        swipe(rect.getPoint().moveBy(rect.getWidth() * 5 / 10 + 10, rect.getHeight() * 5 / 10 + 10),
                rect.getPoint().moveBy(rect.getWidth() * 9 / 10 + 10, rect.getHeight() / 10 + 10));
    }

    @Step
    public void swipeDownByLeftSide() {
        swipe(leftOfScreen, centerOfScreen.moveBy(0, -screenHeight / 4));
    }

    @Step
    public void swipeUpByLeftSide() {
        swipe(leftOfScreen, centerOfScreen.moveBy(0, screenHeight / 4));
    }

    @Step
    public void defaultMidSwipeDown() { //TODO maybe we should have up and down methods separate + large swipe
        swipe(centerOfScreen, centerOfScreen.moveBy(0, -screenHeight / 4));
    }

    @Step
    public void defaultMidSwipeUp() { //TODO maybe we should have up and down methods separate + large swipe
        swipe(centerOfScreen, centerOfScreen.moveBy(0, screenHeight / 4));
    }

    @Step
    public void defaultSmallSwipe(boolean isUp) {
        if (isUp) swipe(centerOfScreen, centerOfScreen.moveBy(0, screenHeight / 3));
        else swipe(centerOfScreen, centerOfScreen.moveBy(0, -screenHeight / 3));
    }

    @Step
    public void shortSwipeLeft() {
        swipe(centerOfScreen, centerOfScreen.moveBy(-20, 0));
    }

    @Step
    public void defaultMidSwipeLeft() {
        swipe(centerOfScreen, centerOfScreen.moveBy(-screenWidth / 3, 0));
    }

    @Step
    public void defaultMidSwipeRight() {
        swipe(centerOfScreen, centerOfScreen.moveBy(screenWidth / 3, 0));
    }

    @Step
    public void shortSwipeRight() {
        swipe(centerOfScreen, centerOfScreen.moveBy(20, 0));
    }

    @Step("Swipe horizontally to element inside the element.")
    public void swipeHorizontallyInsideElementToElement(WebElement insideElement, By selector) {
        swipeLeftInsideElementToElement(insideElement, selector, DEFAULT_HORIZONTAL_SWIPE_ATTEMPTS);
        if (!isElementShown(selector, 2)) {
            swipeRightInsideElementToElement(insideElement, selector, DEFAULT_HORIZONTAL_SWIPE_ATTEMPTS);
        }
    }

    @Step("Swipe left inside element to element.")
    public void swipeLeftInsideElementToElement(WebElement element, By toElement, int swipeNumber) {
        int x = element.getLocation().getX() + element.getRect().getWidth() / 2;
        Point elementPoint = getCenterOfElement(element);
        int swipeCount = 0;

        while (swipeCount < swipeNumber) {
            if (isElementShown(toElement, 2))
                break;
            else {
                swipe(elementPoint, elementPoint.moveBy(-x, 0));
                swipeCount++;
            }
        }
    }

    @Step("Swipe left inside element")
    public void swipeLeftInsideElement(WebElement element) {
        int x = element.getLocation().getX() + element.getRect().getWidth() / 2;
        Point elementPoint = getCenterOfElement(element);
        swipe(elementPoint, elementPoint.moveBy(-x/2, 0));
    }

    @Step("Swipe down inside element")
    public void swipeDownInsideElement(WebElement element) {
        int y = element.getLocation().getY() + element.getRect().getHeight() / 2;
        Point elementPoint = getCenterOfElement(element);
        swipe(elementPoint, elementPoint.moveBy(0, -y));
    }

    @Step("Swipe right inside element to element.")
    public void swipeRightInsideElementToElement(WebElement element, By toElement, int swipeNumber) {
        int x = element.getLocation().getX() + element.getRect().getWidth() / 2;
        Point elementPoint = getCenterOfElement(element);
        int swipeCount = 0;

        while (swipeCount < swipeNumber) {
            if (isElementShown(toElement, 2))
                break;
            else {
                swipe(elementPoint, elementPoint.moveBy(x, 0));
                swipeCount++;
            }
        }
    }

    @Step("Swipe vertically to element inside the element.")
    public void swipeVerticallyInsideElementToElement(WebElement insideElement, By selector) {
        swipeDownInsideElementToElement(insideElement, selector, DEFAULT_VERTICAL_SWIPE_ATTEMPTS);
        if (!isElementShown(selector, 2)) {
            swipeUpInsideElementToElement(insideElement, selector, DEFAULT_VERTICAL_SWIPE_ATTEMPTS);
        }
    }

    @Step("Swipe vertically to element inside the element.")
    public void swipeVerticallyInsideElementToElement(WebElement insideElement, WebElement toElement) {
        swipeDownInsideElementToElement(insideElement, toElement, DEFAULT_VERTICAL_SWIPE_ATTEMPTS);
        if (!isElementShown(toElement)) {
            swipeUpInsideElementToElement(insideElement, toElement, DEFAULT_VERTICAL_SWIPE_ATTEMPTS);
        }
    }

    @Step("Swipe down inside element to element.")
    public void swipeDownInsideElementToElement(WebElement element, By toElement, int swipeNumber) {
        int y = element.getLocation().getY() + element.getRect().getHeight() / 2;
        Point elementPoint = getCenterOfElement(element);
        int swipeCount = 0;

        while (swipeCount < swipeNumber) {
            if (isElementShown(toElement, 2))
                break;
            else {
                swipe(elementPoint, elementPoint.moveBy(0, -y));
                swipeCount++;
            }
        }
    }

    @Step("Swipe down inside element to element.")
    public void swipeDownInsideElementToElement(WebElement element, WebElement toElement, int swipeNumber) {
        try {
            int y = element.getLocation().getY() + element.getRect().getHeight() / 2;
            Point elementPoint = getCenterOfElement(element);
            int swipeCount = 0;

            while (swipeCount < swipeNumber) {
                if (isElementShown(toElement))
                    break;
                else {
                    swipe(elementPoint, elementPoint.moveBy(0, -y));
                    swipeCount++;
                }
            }
        } catch (StaleElementReferenceException e) {
            swipeDownInsideElementToElement(element, toElement, swipeNumber);
        }
    }

    @Step("Swipe up inside element to element.")
    public void swipeUpInsideElementToElement(WebElement element, By toElement, int swipeNumber) {
        int y = element.getLocation().getY() + element.getRect().getHeight() / 2;
        Point elementPoint = getCenterOfElement(element);
        int swipeCount = 0;

        while (swipeCount < swipeNumber) {
            if (isElementShown(toElement))
                break;
            else {
                swipe(elementPoint, elementPoint.moveBy(0, y));
                swipeCount++;
            }
        }
    }

    @Step("Swipe up inside element to element.")
    public void swipeUpInsideElementToElement(WebElement element, WebElement toElement, int swipeNumber) {
        int y = element.getLocation().getY() + element.getRect().getHeight() / 2;
        Point elementPoint = getCenterOfElement(element);
        int swipeCount = 0;

        while (swipeCount < swipeNumber) {
            if (isElementShown(toElement))
                break;
            else {
                swipe(elementPoint, elementPoint.moveBy(0, y));
                swipeCount++;
            }
        }
    }

    @Step("Swipe down until element is visible")
    public void swipeDownUntilVisible(WebElement element) {
        int count = 0;
        while (!isElementShown(element) && count < 12) {
            defaultMidSwipeDown();
            count++;
        }
        if (count < 12) {
            Point point = element.getLocation();
            if (point.getY() > screenHeight * 0.7) {
                defaultMidSwipeDown();
            }
        }
    }

    public Point getCenterOfElement(WebElement element) {
        try {
            int x = element.getLocation().getX() + element.getRect().getWidth() / 2;
            int y = element.getLocation().getY() + element.getRect().getHeight() / 2;
            return new Point(x, y);
        } catch (StaleElementReferenceException e) {
            return getCenterOfElement(element);
        }
    }

    @Step("Swipe to element '{0}'")
    public void swipeToElement(WebElement element) {
        swipeToElement(element, DEFAULT_VERTICAL_SWIPE_ATTEMPTS);
    }

    @Step("Swipe to element '{0}'")
    public void swipeToElement(WebElement element, int swipeCount) {
        int count = 0;

        while (count < swipeCount) {
            if (isElementShown(element))
                break;
            else {
                defaultMidSwipeDown();
                count++;
            }
        }

        while (count > 0) {
            if (isElementShown(element))
                break;
            else {
                defaultMidSwipeUp();
                count--;
            }
        }
    }
}
