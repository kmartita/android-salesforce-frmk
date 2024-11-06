package io.omni.example.tools.api.salesforce.fields.configuration;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.HasName;

public enum AccountDynamicListConfigField implements HasName {

    ACCOUNT_FILTER_CREATE_ENABLED("AccountFilterCreateEnabled", "AccountFilterCreateEnabled__c"),
    ACCOUNT_FILTER_SHARE_ENABLED("AccountFilterShareEnabled", "AccountFilterShareEnabled__c"),
    WEB_ALL_ACCOUNTS_FILTER_ENABLED("WebAllAccountsFilterEnabled", "WebAllAccountsFilterEnabled__c"),
    ACCOUNT_AVAILABLE_FIELDS("Account Field Set", "AccountAvailableFields__c"),
    ACCOUNT_ADDRESS_AVAILABLE_FIELDS("Account Address Field Set", "AccountAddressAvailableFields__c"),
    ATF_AVAILABLE_FIELDS("ATF Field Set", "ATFAvailableFields__c"),
    ATP_AVAILABLE_FIELDS("ATP Field Set", "ATPAvailableFields__c");

    private final String name;
    private final String label;

    AccountDynamicListConfigField(String label, String name) {
        this.label = label;
        this.name = name;
    }

    @Override
    public String getName() {
        return ManagePackageData.updateFieldName(this.name);
    }
    public String getLabel() {return this.label;}
}
