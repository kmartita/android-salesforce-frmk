package io.omni.example.tools.api.salesforce.fields.configuration;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.HasName;

public enum ApplicationSettingsField implements HasName {

    ENABLE_PERMISSION_SETS_FOR_DB_SCHEMA("EnablePermissionSetsForDBSchema__c"),
    ENABLE_ADVANCED_SEARCH("EnableAdvancedSearch__c"),
    STOP_UPDATING_GENERIC_WORKFLOW_METADATA("StopUpdatingGenericWorkflowMetadata__c"),
    USE_SOBJECTS_FOR_GENERIC_WORKFLOW("UseSObjectsForGenericWorkflow__c"),
    ENABLE_IPAD_AFFILIATION_V2("EnableiPadAffiliationsV2__c");

    private final String name;

    ApplicationSettingsField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ManagePackageData.updateFieldName(this.name);
    }
}
