package io.omni.example.screens.app.accounts;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.screens.app.AbstractForm;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.omni.example.tools.ExpectedConditionTool.waitUntilCondition;
import static io.omni.example.tools.utilities.WaitUtils.waitForSeconds;

public class AccountFiltersForm extends AbstractForm {

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Tag')]")
    private List<WebElement> accountFilterList;

    @Step("Search filter/list by name '{name}'.")
    public void searchFilterOrList(String name) {
        searchValue(name);
        waitForSeconds(3);
    }

    @Step("Select filter/list by name '{name}'.")
    public AccountsScreen selectFilterOrList(String name) {
        searchFilterOrList(name);
        waitUntilCondition(() -> accountFilterList.size() == 1);
        click(accountFilterList.get(0));
        waitUntilCondition(() -> accountFilterList.isEmpty());
        return new AccountsScreen();
    }
}
