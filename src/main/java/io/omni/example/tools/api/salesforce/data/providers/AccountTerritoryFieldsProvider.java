package io.omni.example.tools.api.salesforce.data.providers;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.SalesforceApiUtils;
import io.omni.example.tools.api.salesforce.data.SalesforceData;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.fields.AccountTerritoryFieldsField;
import io.omni.example.tools.api.salesforce.sobject.SObject;

public class AccountTerritoryFieldsProvider {

    public SalesforceData<AccountTerritoryFieldsField> createAccountTerritoryField(SfUserModel user,
                                                                                   String accountId,
                                                                                   String territory,
                                                                                   String accountAddressId) {
        GenericModel<AccountTerritoryFieldsField> model = GenericModel.builder(AccountTerritoryFieldsField.class)
                .setField(AccountTerritoryFieldsField.ACCOUNT, accountId)
                .setField(AccountTerritoryFieldsField.TERRITORY, territory)
                .setField(AccountTerritoryFieldsField.PREFERRED_ADDRESS, accountAddressId)
                .build();
        return new SalesforceApiUtils(user).createSObject(SObject.ACCOUNT_TERRITORY_FIELDS, model);
    }
}
