package io.omni.example.mobile.account;

import io.omni.example.activation.AccountActivation;
import io.omni.example.activation.AccountFilterActivation;
import io.omni.example.mobile.TestBase;
import io.omni.example.screens.app.accounts.AccountsScreen;
import io.omni.example.screens.app.accounts.SelectMultipleAccountsForm;
import io.omni.example.screens.app.accounts.UpdateSelectedAccountsForm;
import io.omni.example.tools.api.salesforce.SalesforceApiUtils;
import io.omni.example.tools.api.salesforce.data.hierarchies.account.AccountHierarchy;
import io.omni.example.tools.api.salesforce.data.hierarchies.accountfilter.AccountListHierarchy;
import io.omni.example.tools.api.salesforce.data.providers.AccountFilterProvider;
import io.omni.example.tools.api.salesforce.data.providers.SObjectProvider;
import io.omni.example.tools.api.salesforce.fields.AccountTerritoryFieldsField;
import io.omni.example.tools.api.salesforce.fields.AccountTerritoryProductField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static io.omni.example.tools.api.salesforce.data.OwnerMapping.MARTA_KRAVCHUK;
import static io.omni.example.tools.api.salesforce.recordtypes.AccountRecordType.MP;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

/**
 * {@link AccountActivation#activateBulkUpdateForAccountsSearch}
 * {@link AccountFilterActivation#activateAccountBulkFilterSets}
 * <p>
 * CREATED DATA:
 * data is created during the test
 **/
@Owner(MARTA_KRAVCHUK)
public class VerifyFieldsVisibleToSROnBulkUpdateAreDeterminedByAccessTypeAndFieldSetsConfiguredTest extends TestBase {

    private static final String TEST_CASE_ID = "C8148056";
    private static final int NUMBER_OF_ACCOUNTS = 3;
    private static final String SHOULD_NOT_BE_CLICKABLE_MSG = "%s button should not be clickable.";
    private static final String SHOULD_BE_CLICKABLE_MSG = "%s button should be clickable.";
    private static final String FIELD_SHOULD_BE_AVAILABLE_MSG = "Field '%s' should be available.";

    private static final String UPDATE_SELECTED = "Update Selected";
    private static final String UNSELECT_ALL = "Unselect All";
    private static final String SELECT_ALL = "Select All";

    private String listName;
    private String listId;
    private List<String> accountIds;

    private AccountsScreen accountsScreen;
    private SelectMultipleAccountsForm selectMultipleAccountsForm;
    private UpdateSelectedAccountsForm updateSelectedAccountsForm;

    @BeforeClass
    public void setup() {
        createTestData();

        accountsScreen = sessionHelper
                .loginAs(FIRST_SALES_REP, FIRST_SALES_REP_TERRITORY)
                .navigateToAccountsScreen();
    }

    @DataProvider
    public Object[][] positiveDataForAtp() {
        return new Object[][]{
                {AccountTerritoryProductField.ATP_TEXT1},
                {AccountTerritoryProductField.ATP_TEXT2},
                {AccountTerritoryProductField.ATP_TEXT3},
                {AccountTerritoryProductField.ATP_TEXT4},
                {AccountTerritoryProductField.ATP_TEXT5},
                {AccountTerritoryProductField.ATP_TEXT6}
        };
    }

    @DataProvider
    public Object[][] positiveDataForAtf() {
        return new Object[][]{
                {AccountTerritoryFieldsField.ATF_TEXT1},
                {AccountTerritoryFieldsField.ATF_TEXT2}
        };
    }

    @DataProvider
    public Object[][] negativeDataForAtf() {
        return new Object[][]{
                {AccountTerritoryFieldsField.ATF_TEXT3},
                {AccountTerritoryFieldsField.ATF_TEXT4},
                {AccountTerritoryFieldsField.ATF_TEXT5},
                {AccountTerritoryFieldsField.ATF_TEXT6}
        };
    }

    @TmsLink(TEST_CASE_ID)
    @Test
    public void verifyNumberOfAccountRecordsInsideSelectedList() {
        accountsScreen
                .openAccountFilterForm()
                .selectFilterOrList(listName);

        Assert.assertEquals(accountsScreen.getAccountsNumber(), NUMBER_OF_ACCOUNTS,
                format("Numbers of account records should be '%d' for selected list '%s'.",
                        NUMBER_OF_ACCOUNTS, listName));
    }

    @TmsLink(TEST_CASE_ID)
    @Test(dependsOnMethods = "verifyNumberOfAccountRecordsInsideSelectedList")
    public void verifyMultiSelectFormWithoutSelectedAccounts() {
        selectMultipleAccountsForm = accountsScreen.openMultiSelectForm();

        soft.assertFalse(selectMultipleAccountsForm.isUpdateSelectedButtonClickable(),
                format(SHOULD_NOT_BE_CLICKABLE_MSG, UPDATE_SELECTED));
        soft.assertFalse(selectMultipleAccountsForm.isUnselectAllButtonClickable(),
                format(SHOULD_NOT_BE_CLICKABLE_MSG, UNSELECT_ALL));
        soft.assertTrue(selectMultipleAccountsForm.isSelectAllButtonClickable(),
                format(SHOULD_BE_CLICKABLE_MSG, SELECT_ALL));

        soft.assertAll();
    }

    @TmsLink(TEST_CASE_ID)
    @Test(dependsOnMethods = "verifyMultiSelectFormWithoutSelectedAccounts")
    public void verifySelectingAllAccounts() {
        selectMultipleAccountsForm.selectAllAccounts();

        soft.assertEquals(selectMultipleAccountsForm.getSelectedAccountNumber(), NUMBER_OF_ACCOUNTS,
                format("%s accounts should be selected.", NUMBER_OF_ACCOUNTS));
        soft.assertTrue(selectMultipleAccountsForm.isUpdateSelectedButtonClickable(),
                format(SHOULD_BE_CLICKABLE_MSG, UPDATE_SELECTED));

        soft.assertAll();
    }

    @TmsLink(TEST_CASE_ID)
    @Test(dependsOnMethods = "verifySelectingAllAccounts")
    public void verifyUpdatingSelectedAccounts() {
        updateSelectedAccountsForm = selectMultipleAccountsForm.updateSelected();

        Assert.assertTrue(updateSelectedAccountsForm.isUpdateAccountFormDisplayed(),
                "Update Accounts Form should be selected.");
    }

    @TmsLink(TEST_CASE_ID)
    @Test(dataProvider = "positiveDataForAtp", dependsOnMethods = "verifyUpdatingSelectedAccounts")
    public void verifyEachProductFieldThatAddedToForm(AccountTerritoryProductField atpField) {
        updateSelectedAccountsForm
                .openProductTab()
                .searchField(atpField);

        Assert.assertTrue(updateSelectedAccountsForm.isFieldDisplayed(atpField),
                format(FIELD_SHOULD_BE_AVAILABLE_MSG, atpField));
    }

    @TmsLink(TEST_CASE_ID)
    @Test(dataProvider = "positiveDataForAtf", dependsOnMethods = "verifyEachProductFieldThatAddedToForm", alwaysRun = true)
    public void verifyEachTerritoryFieldThatAddedToForm(AccountTerritoryFieldsField atfField) {
        updateSelectedAccountsForm
                .openTerritoryTab()
                .searchField(atfField);

        Assert.assertTrue(updateSelectedAccountsForm.isFieldDisplayed(atfField),
                format(FIELD_SHOULD_BE_AVAILABLE_MSG, atfField));
    }

    @TmsLink(TEST_CASE_ID)
    @Test(dataProvider = "negativeDataForAtf", dependsOnMethods = "verifyEachTerritoryFieldThatAddedToForm", alwaysRun = true)
    public void verifyEachTerritoryFieldThatNotAddedToForm(AccountTerritoryFieldsField atfField) {
        updateSelectedAccountsForm
                .openTerritoryTab()
                .searchText("ATF_Text");

        Assert.assertFalse(updateSelectedAccountsForm.isFieldDisplayed(atfField),
                format("Field '%s' should be not available.", atfField));
    }

    @Step
    private void createTestData(){
        List<AccountHierarchy> accounts = new ArrayList<>();
        IntStream.range(0, NUMBER_OF_ACCOUNTS)
                .mapToObj(i -> new SObjectProvider().createAccountWithAddressAndAtf(FIRST_SALES_REP, MP, FIRST_SALES_REP_TERRITORY))
                .forEach(accounts::add);
        AccountListHierarchy list = new AccountFilterProvider().createStaticListWithAccounts(FIRST_SALES_REP, accounts);
        listName = list.getListName();
        listId = list.getList().getId();

        accountIds = accounts
                .stream()
                .map(account -> account.getAccountData().getId())
                .collect(toList());
    }

    @AfterClass(alwaysRun = true)
    public void removeObjects() {
        SalesforceApiUtils salesforceApiUtils = new SalesforceApiUtils();
        accountIds.forEach(id -> salesforceApiUtils.deleteSObject(SObject.ACCOUNT, id));
        salesforceApiUtils.deleteSObject(SObject.ACCOUNT_FILTER, listId);
    }
}
