package io.omni.example.tools.api.salesforce.fields.configuration;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.HasName;

public enum DbSchemaField implements HasName {

    IS_ACTIVE("IsActive__c"),
    WHERE_SOQL("WhereSoql__c"),
    MANDATORY_FIELDS("MandatoryFields__c"),
    DELTA_DATE_FIELD("DeltaDateField__c"),
    DEVELOPER_NAME("DeveloperName"),
    NAMESPACE_PREFIX("NamespacePrefix"),
    PERMITTED_PROFILES("PermittedProfiles__c"),
    ATTACHMENTS_SUPPORT("AttachmentsSupport__c"),
    S_OBJECT("SObject__c"),
    TYPE("Type__c"),
    PERMISSION_SETS("PermissionSets__c");

    private final String name;

    DbSchemaField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ManagePackageData.updateFieldName(this.name);
    }
}
