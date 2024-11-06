package io.omni.example.screens.app.accounts;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.screens.app.AbstractForm;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SelectMultipleAccountsForm extends AbstractForm {

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Update Selected']")
    private WebElement updateSelectedButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Select All']")
    private WebElement selectAllButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Unselect All']")
    private WebElement unselectAllButton;

    @AndroidFindBy(xpath = "//android.view.View[android.widget.CheckBox]")
    private List<WebElement> visibleAccountsList;

    @AndroidFindBy(xpath = "//android.view.View[android.widget.CheckBox]/android.widget.CheckBox")
    private List<WebElement> visibleAccountsCheckboxesList;

    @Step("Check is 'Update Selected' button clickable.")
    public boolean isUpdateSelectedButtonClickable() {
        return mobileElementUtils().isElementClickable(updateSelectedButton);
    }

    @Step("Check is 'Select All' button clickable.")
    public boolean isSelectAllButtonClickable() {
        return mobileElementUtils().isElementClickable(selectAllButton);
    }

    @Step("Check is 'Unselect All' button clickable.")
    public boolean isUnselectAllButtonClickable() {
        return mobileElementUtils().isElementClickable(unselectAllButton);
    }

    @Step("Select all accounts.")
    public SelectMultipleAccountsForm selectAllAccounts() {
        click(selectAllButton);
        return this;
    }

    @Step("Get selected accounts by number.")
    public long getSelectedAccountNumber() {
        Set<String> names = new HashSet<>(getValuesFromElements(visibleAccountsList));
        int count = 0;
        while (count < 30) {
            if(visibleAccountsCheckboxesList.stream()
                    .map(this::isCheckBoxChecked)
                    .collect(Collectors.toList())
                    .contains(Boolean.FALSE)){
                break;
            }
            mobileElementUtils().defaultMidSwipeDown();
            names.addAll(getValuesFromElements(visibleAccountsList));
            count++;
        }
        names.addAll(getValuesFromElements(visibleAccountsList));
        long numberOfUnselectedAccounts = visibleAccountsCheckboxesList.stream()
                .filter(i -> !isCheckBoxChecked(i))
                .count();
        return names.size() - numberOfUnselectedAccounts;
    }

    @Step("Open 'Update Selected Account' form.")
    public UpdateSelectedAccountsForm updateSelected() {
        click(updateSelectedButton);
        return new UpdateSelectedAccountsForm();
    }
}
