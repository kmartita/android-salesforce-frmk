package io.omni.example.tools.api.salesforce.fields;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.HasName;

public enum AccountListItemField implements HasName {

    ACCOUNT_FILTER("Account Filter", "AccountFilter__c"),
    CUSTOMER("Customer", "Customer__c"),
    START_TIME("Start Time", "StartTime__c"),
    DURATION("Duration", "Duration__c"),
    LOCATION("Location", "Location__c");

    private final String label;

    private final String apiName;

    AccountListItemField(String label, String apiName) {
        this.label = label;
        this.apiName = apiName;
    }

    public String getName() {
        return ManagePackageData.updateFieldName(this.apiName);
    }

    public String getLabel() {
        return this.label;
    }
}
