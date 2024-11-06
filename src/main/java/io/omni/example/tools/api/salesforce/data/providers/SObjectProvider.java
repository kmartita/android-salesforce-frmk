package io.omni.example.tools.api.salesforce.data.providers;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.data.SalesforceData;
import io.omni.example.tools.api.salesforce.data.hierarchies.account.AccountHierarchy;
import io.omni.example.tools.api.salesforce.fields.AccountAddressField;
import io.omni.example.tools.api.salesforce.fields.AccountField;
import io.omni.example.tools.api.salesforce.fields.AccountTerritoryFieldsField;
import io.omni.example.tools.api.salesforce.fields.AddressField;
import io.omni.example.tools.api.salesforce.recordtypes.AccountRecordType;

import static java.util.Collections.singletonList;

public class SObjectProvider {

    public AccountHierarchy createAccountWithAddressAndAtf(SfUserModel user,
                                                           AccountRecordType recordType,
                                                           String territory) {
        SalesforceData<AccountField> account = new AccountProvider().createAccount(user, recordType, territory);
        SalesforceData<AddressField> address = new AddressProvider().createDefaultAddress();
        SalesforceData<AccountAddressField> accountAddress = new AccountAddressProvider()
                .createAccountAddress(recordType, account.getId(), address.getId());
        SalesforceData<AccountTerritoryFieldsField> atf = new AccountTerritoryFieldsProvider()
                .createAccountTerritoryField(user, account.getId(), territory, accountAddress.getId());
        return new AccountHierarchy(account, singletonList(address), singletonList(accountAddress), atf);
    }
}
