package io.omni.example.tools.api.salesforce.data.providers.configuration;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModelBuilder;
import io.omni.example.tools.api.salesforce.fields.configuration.AccountDynamicListConfigField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.qameta.allure.Step;

import static io.omni.example.tools.metadata.MetadataFieldSetUtils.FieldSets.ATF_BULK_UPDATE;
import static io.omni.example.tools.metadata.MetadataFieldSetUtils.FieldSets.ATP_BULK_UPDATE;

public class AccountFilterConfigurationProvider extends ConfigurationProvider {

    private GenericModel<AccountDynamicListConfigField> generateFilterConfigModel(boolean creationEnabled, boolean sharingEnabled, boolean webAllAccountsFilterEnabled) {
        GenericModelBuilder<AccountDynamicListConfigField> builder = GenericModel.builder(AccountDynamicListConfigField.class);
        return builder
                .setField(AccountDynamicListConfigField.ACCOUNT_FILTER_CREATE_ENABLED, creationEnabled)
                .setField(AccountDynamicListConfigField.ACCOUNT_FILTER_SHARE_ENABLED, sharingEnabled)
                .setField(AccountDynamicListConfigField.WEB_ALL_ACCOUNTS_FILTER_ENABLED, webAllAccountsFilterEnabled)
                .build();
    }

    private GenericModel<AccountDynamicListConfigField> generateBulkConfigModel(){
        return generateFilterConfigModel(true, true, false)
                .edit(AccountDynamicListConfigField.ATF_AVAILABLE_FIELDS, ATF_BULK_UPDATE.getApiName())
                .edit(AccountDynamicListConfigField.ATP_AVAILABLE_FIELDS, ATP_BULK_UPDATE.getApiName());
    }

    @Step
    public void activateAccountDynamicListBulkConfiguration(SfUserModel user) {
        GenericModel<AccountDynamicListConfigField> configuration = generateBulkConfigModel();
        activateConfigurationForUser(user, SObject.ACCOUNT_DYNAMIC_LIST_CONFIG, configuration);
    }
}
