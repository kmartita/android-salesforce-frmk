package io.omni.example.screens.app.accounts;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.screens.app.AbstractMainScreen;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class AccountsScreen extends AbstractMainScreen {

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Filter') and contains(@content-desc,'Dropdown')]")
    private WebElement filterDropdownField;

    @AndroidFindBy(xpath = "//android.widget.Button[starts-with(@content-desc,'Select Account Type') and contains(@content-desc,'Dropdown')]")
    private WebElement selectAccountTypeField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Select']")
    private WebElement multiSelectButton;

    @Step("Open 'Account Filter Form'.")
    public AccountFiltersForm openAccountFilterForm() {
        click(filterDropdownField);
        return new AccountFiltersForm();
    }

    @Step("Get accounts by number.")
    public int getAccountsNumber() {
        String[] arr = getValueFromElement(selectAccountTypeField).split("\n");
        return Integer.parseInt(arr[arr.length - 1]);
    }

    @Step("Open multi-select form.")
    public SelectMultipleAccountsForm openMultiSelectForm() {
        click(multiSelectButton);
        return new SelectMultipleAccountsForm();
    }
}
