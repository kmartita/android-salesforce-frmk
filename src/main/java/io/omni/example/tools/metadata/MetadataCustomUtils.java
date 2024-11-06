package io.omni.example.tools.metadata;

import com.google.common.base.Strings;
import com.sforce.soap.metadata.CustomMetadata;
import com.sforce.soap.metadata.CustomMetadataValue;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.PermissionSets;
import io.omni.example.tools.api.salesforce.fields.configuration.DbSchemaField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.models.DBSchemaModel;
import io.omni.example.tools.api.salesforce.sobject.requests.DBSchemaRequests;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MetadataCustomUtils extends MetadataUtils {

    private CustomMetadata initCustomMetadataForDBSchema(SObject sObject) {
        DBSchemaModel dbSchemaModel = new DBSchemaRequests().getDbSchemaBySObject(sObject);
        String namespacePrefix = Strings.isNullOrEmpty(dbSchemaModel.getNamespacePrefix()) ? StringUtils.EMPTY : dbSchemaModel.getNamespacePrefix() + "__";
        String dbSchemaName = StringUtils.remove(SObject.DB_SCHEMA.getName(), "__mdt") + "." + namespacePrefix + dbSchemaModel.getDeveloperName();
        return initCustomMetadata(dbSchemaModel.getLabel(), dbSchemaName);
    }

    private void upsertDbSchema(SObject dbSchemaName, CustomMetadataValue... customMetadataValues) {
        CustomMetadata dbSchemaCustomMetadata = initCustomMetadataForDBSchema(dbSchemaName);
        upsertCustomMetadata(dbSchemaCustomMetadata, customMetadataValues);
    }

    private void upsertCustomMetadata(CustomMetadata customMetadata, CustomMetadataValue... customMetadataValues) {
        customMetadata.setValues(customMetadataValues);
        upsertMetadata(customMetadata);
    }

    private CustomMetadata initCustomMetadata(String label, String fullName) {
        CustomMetadata customMetadata = new CustomMetadata();
        customMetadata.setLabel(label);
        customMetadata.setFullName(fullName);
        return customMetadata;
    }

    private CustomMetadataValue initCustomMetadataValue(Object fieldValue) {
        CustomMetadataValue customMetadataValue = new CustomMetadataValue();
        customMetadataValue.setField(DbSchemaField.PERMISSION_SETS.getName());
        customMetadataValue.setValue(fieldValue);
        return customMetadataValue;
    }

    @Step
    public void updateDBSchemaPermissionProfiles(SObject sObject, List<PermissionSets> permissionSets) {
        String permissionSetsString = ";" + permissionSets.stream().map(PermissionSets::getName).collect(Collectors.joining(";")) + ";";
        CustomMetadataValue permissionSetsMetadataValue = initCustomMetadataValue(permissionSetsString);
        upsertDbSchema(sObject, permissionSetsMetadataValue);
    }
}
