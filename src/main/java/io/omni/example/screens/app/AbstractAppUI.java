package io.omni.example.screens.app;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.screens.app.siderbar.HomeScreen;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static io.omni.example.tools.ExpectedConditionTool.waitUntilCondition;
import static io.omni.example.tools.ExpectedConditionTool.waitUntilVisible;
import static java.lang.Boolean.parseBoolean;

public abstract class AbstractAppUI extends AbstractUI {

    private final String searchFieldLocator = "//android.widget.EditText";

    @AndroidFindBy(xpath = searchFieldLocator)
    protected WebElement searchField;

    @AndroidFindBy(xpath = searchFieldLocator + "/android.widget.Button")
    private WebElement searchCleanButton;

    @Step("Check if 'Search' field is displayed.")
    public boolean isSearchFieldDisplayed(){ return mobileElementUtils().isElementShown(searchField, 5); }

    @Step("Check if search 'Clean' button is displayed.")
    public boolean isSearchCleanButtonDisplayed(){ return mobileElementUtils().isElementShown(searchCleanButton, 3); }

    @Step("Check if tab is opened.")
    public boolean isTabOpened(WebElement tab) {
        try {
            return parseBoolean(tab.getAttribute("selected").toLowerCase());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Wait until synchronization is finished.")
    public HomeScreen waitUntilSyncFinished() {
        String locator = String.format(VIEW_EQUALS_LOCATOR, "Home Page");
        waitUntilVisible(By.xpath(locator), 500);
        return new HomeScreen();
    }

    @Step("Wait until delta synchronization is finished.")
    public HomeScreen waitUntilDeltaSyncFinished() {
        String locator = String.format(VIEW_CONTAINS_LOCATOR, "Sync Progress Indicator");
        waitUntilCondition(() -> !mobileElementUtils().isElementShown(By.xpath(locator), 5), 300);
        return new HomeScreen();
    }

    @Step("Clean search field.")
    protected void cleanSearchField() {
        if (isSearchFieldNotEmpty()) {
            if (isSearchCleanButtonDisplayed()) {
                click(searchCleanButton);
            } else {
                click(searchField);
                searchField.clear();
            }
        }
    }

    @Step("Search value '{value}'.")
    public void searchValue(String value) {
        waitUntilCondition(this::isSearchFieldDisplayed);
        cleanSearchField();
        click(searchField);
        sendKeys(value);
    }

    @Step("Check if search field has value '{value}'.")
    public boolean hasSearchFieldValue(String value) {
        return mobileElementUtils().getTextFromElement(searchField).equals(value)
                | mobileElementUtils().getTextFromElement(searchField).contains(value);
    }

    @Step("Check if search field is not empty.")
    protected boolean isSearchFieldNotEmpty(){
        String search = "Search";
        String textIntoSearch;
        String hintIntoSearch;
        try{
            waitUntilCondition(() -> mobileElementUtils().isElementShown(searchField));
            textIntoSearch = mobileElementUtils().getTextFromElement(searchField);
            hintIntoSearch = mobileElementUtils().getHintFromElement(searchField);
        } catch (StaleElementReferenceException e) {
            textIntoSearch = mobileElementUtils().getTextFromElement(driver.findElement(By.xpath(searchFieldLocator)));
            hintIntoSearch = mobileElementUtils().getHintFromElement(driver.findElement(By.xpath(searchFieldLocator)));
        }
        return !textIntoSearch.isEmpty() | textIntoSearch.equals(search) & !hintIntoSearch.startsWith(search);
    }

    @Step
    public List<String> getValuesFromElements(List<WebElement> elements) {
        return elements.stream().map(this::getValueFromElement).collect(Collectors.toList());
    }

    @Step
    public boolean isCheckBoxChecked(WebElement element) {
        return mobileElementUtils().isCheckBoxChecked(element);
    }
}
