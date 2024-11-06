package io.omni.example.tools.api.salesforce.data.hierarchies.account;

import io.omni.example.tools.api.salesforce.data.SalesforceData;
import io.omni.example.tools.api.salesforce.fields.AccountAddressField;
import io.omni.example.tools.api.salesforce.fields.AccountField;
import io.omni.example.tools.api.salesforce.fields.AccountTerritoryFieldsField;
import io.omni.example.tools.api.salesforce.fields.AddressField;

import java.util.List;

import static java.lang.String.format;

public class AccountHierarchy {

    private final SalesforceData<AccountField> account;
    private final List<SalesforceData<AddressField>> addresses;
    private final List<SalesforceData<AccountAddressField>> accountAddresses;
    private final SalesforceData<AccountTerritoryFieldsField> atf;

    public AccountHierarchy(SalesforceData<AccountField> account,
                            List<SalesforceData<AddressField>> addresses,
                            List<SalesforceData<AccountAddressField>> accountAddresses,
                            SalesforceData<AccountTerritoryFieldsField> atf) {
        this.account = account;
        this.addresses = addresses;
        this.accountAddresses = accountAddresses;
        this.atf = atf;
    }

    public SalesforceData<AccountField> getAccountData() {
        return account;
    }

    public List<SalesforceData<AddressField>> getAddressesData() { return addresses; }

    public SalesforceData<AddressField> getPreferredAddressData() { return getAddressesData().stream().findFirst().get(); }

    public List<SalesforceData<AccountAddressField>> getAccountAddressesData() {
        return accountAddresses;
    }

    public SalesforceData<AccountAddressField> getPreferredAccountAddressData() { return getAccountAddressesData().stream().findFirst().get(); }

    public SalesforceData<AccountTerritoryFieldsField> getAtfData() { return atf; }

    public String toString(){
        return format("Account hierarchy based on: [id=%s]", account.getId());
    }
}
