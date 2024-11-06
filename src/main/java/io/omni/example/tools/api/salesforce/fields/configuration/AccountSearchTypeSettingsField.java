package io.omni.example.tools.api.salesforce.fields.configuration;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.HasName;

public enum AccountSearchTypeSettingsField implements HasName {

    ENABLE_MAPS("EnableMaps__c"),
    ACCOUNT_RECORD_TYPE_API_NAME("AccountRecordTypeAPIName__c", "AccountRecordTypeAPIName"),
    ENABLE_BULK_ACTION("EnableBulkAction__c"),
    ENABLE_BULK_UPDATE("EnableBulkUpdate__c"),
    ENABLE_MULTI_SELECT("EnableMultiSelect__c");

    private final String apiName;
    private final String label;

    AccountSearchTypeSettingsField(String apiName) {
        this(apiName, apiName);
    }

    AccountSearchTypeSettingsField(String apiName, String label) {
        this.apiName = apiName;
        this.label = label;
    }

    @Override
    public String getName() {
        return ManagePackageData.updateFieldName(this.apiName);
    }

    public String getLabel() {
        return this.label;
    }
}
