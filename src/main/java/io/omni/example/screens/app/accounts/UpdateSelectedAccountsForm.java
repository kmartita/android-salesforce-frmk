package io.omni.example.screens.app.accounts;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.screens.app.AbstractForm;
import io.omni.example.tools.api.HasLabel;
import io.omni.example.tools.api.HasName;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static io.omni.example.tools.ExpectedConditionTool.waitUntilCondition;
import static java.lang.String.format;

public class UpdateSelectedAccountsForm extends AbstractForm {

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Update Account']")
    private WebElement updateAccountHeader;

    @AndroidFindBy(xpath = "//android.view.View[starts-with(@content-desc,'Product') and contains(@content-desc,'Tab')]")
    private WebElement productTabButton;

    @AndroidFindBy(xpath = "//android.view.View[starts-with(@content-desc,'Territory') and contains(@content-desc,'Tab')]")
    private WebElement territoryTabButton;

    @Step("Check is field {field} displayed.")
    public <Field extends Enum<Field> & HasName & HasLabel> boolean isFieldDisplayed(Field field){
        return isElementDisplayed(By.xpath(format(VIEW_CONTAINS_LOCATOR, field.getLabel())));
    }

    @Step("Check is 'Update Account' form displayed.")
    public boolean isUpdateAccountFormDisplayed(){
        return isElementDisplayed(updateAccountHeader);
    }

    @Step("Open 'Product' tab.")
    public UpdateSelectedAccountsForm openProductTab() {
        click(productTabButton);
        waitUntilCondition(() -> isTabOpened(productTabButton));
        return this;
    }

    @Step("Open 'Territory' tab.")
    public UpdateSelectedAccountsForm openTerritoryTab() {
        click(territoryTabButton);
        waitUntilCondition(() -> isTabOpened(territoryTabButton));
        return this;
    }

    @Step("Search field {field}.")
    public <Field extends Enum<Field> & HasName & HasLabel> void searchField(Field field){
        searchText(field.getLabel());
    }

    @Step("Search text {text}.")
    public void searchText(String text){
        searchValue(text);
        waitUntilCondition(() -> hasSearchFieldValue(text));
    }
}
