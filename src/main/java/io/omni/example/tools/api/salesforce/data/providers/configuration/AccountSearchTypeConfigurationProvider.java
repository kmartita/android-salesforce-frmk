package io.omni.example.tools.api.salesforce.data.providers.configuration;

import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModelBuilder;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.UserProfiles;
import io.omni.example.tools.api.salesforce.fields.configuration.AccountSearchTypeSettingsField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.qameta.allure.Step;

public class AccountSearchTypeConfigurationProvider extends ConfigurationProvider {

    public GenericModel<AccountSearchTypeSettingsField> generateBulkUpdateForAccountsSearchModel() {
        GenericModelBuilder<AccountSearchTypeSettingsField> builder = GenericModel.builder(AccountSearchTypeSettingsField.class);
        return builder.setField(AccountSearchTypeSettingsField.ENABLE_BULK_ACTION, true)
                .setField(AccountSearchTypeSettingsField.ENABLE_BULK_UPDATE, true)
                .setField(AccountSearchTypeSettingsField.ENABLE_MULTI_SELECT, true)
                .build();
    }

    @Step
    public void activateAccountsSearchForProfile(UserProfiles profile,
                                                 GenericModel<AccountSearchTypeSettingsField> model) {
        activateConfigurationForProfile(profile, SObject.ACCOUNT_SEARCH_TYPE_SETTINGS, model);
    }
}
