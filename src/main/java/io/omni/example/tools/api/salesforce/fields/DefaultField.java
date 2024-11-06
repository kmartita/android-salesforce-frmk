package io.omni.example.tools.api.salesforce.fields;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.HasName;

public enum DefaultField implements HasName {

    ID("Id"),
    NAME("Name"),
    NAMESPACE_PREFIX("NamespacePrefix"),
    DEVELOPER_NAME("DeveloperName"),
    LABEL("Label"),
    LAST_MODIFIED_DATE("LastModifiedDate"),
    CREATED_DATE("CreatedDate"),
    IS_DELETED_OFFLINE("IsDeletedOffline__c"),
    OFFLINE_CREATED_DATE("OfflineCreatedDate__c"),
    OFFLINE_CREATED_BY_ID("OfflineCreatedbyID__c"),
    OFFLINE_LAST_MODIFIED_BY_ID("OfflineLastModifiedByID__c"),
    OFFLINE_LAST_MODIFIED_DATE( "OfflineLastModifiedDate__c"),
    OFFLINE_UNIQUE_ID("OfflineUniqueId__c"),
    SETUP_OWNER_ID("SetupOwnerId"),
    RECORD_TYPE_ID("RecordTypeId"),
    CREATED_BY_ID("CreatedById");

    private final String name;

    DefaultField(String name) {
        this.name = name;
    }

    @Override
    public final String getName() {
        return ManagePackageData.updateFieldName(this.name);
    }

}
