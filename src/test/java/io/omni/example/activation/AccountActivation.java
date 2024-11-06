package io.omni.example.activation;

import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.providers.configuration.AccountSearchTypeConfigurationProvider;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.UserProfiles;
import io.omni.example.tools.api.salesforce.fields.configuration.AccountSearchTypeSettingsField;

public class AccountActivation {

    private final AccountSearchTypeConfigurationProvider accountSearchTypeConfigurationProvider = new AccountSearchTypeConfigurationProvider();

    public void activateAccounts() {
        activateBulkUpdateForAccountsSearch();
    }

    public void activateBulkUpdateForAccountsSearch() {
        GenericModel<AccountSearchTypeSettingsField> accountSearchModel = accountSearchTypeConfigurationProvider.generateBulkUpdateForAccountsSearchModel();
        accountSearchTypeConfigurationProvider.activateAccountsSearchForProfile(UserProfiles.SALES_REPRESENTATIVE, accountSearchModel);
    }
}
