package io.omni.example.screens.app.login;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.screens.app.AbstractMainScreen;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import static io.omni.example.tools.ExpectedConditionTool.waitUntilCondition;

public class SelectTerritoryScreen extends AbstractMainScreen {

    @AndroidFindBy(xpath = "//android.view.View[lower-case(@content-desc) = 'select territory']")
    private WebElement header;

    @Step("Select territory {territory}")
    public void selectTerritory(String territory) {
        if (mobileElementUtils().isElementShown(header, 25)) {
            By territoryLocator = By.xpath(String.format(VIEW_EQUALS_LOCATOR, territory));
            click(territoryLocator);
            try {
                waitUntilCondition(() -> !mobileElementUtils().isElementShown(header));
            } catch (TimeoutException ignored) {
                Rectangle territoryRect = driver.findElement(territoryLocator).getRect();
                tap(territoryRect.getPoint().moveBy(territoryRect.getHeight() / 2, territoryRect.getHeight() / 2)); //workaround for non-clickable area
            }
        }
    }
}
