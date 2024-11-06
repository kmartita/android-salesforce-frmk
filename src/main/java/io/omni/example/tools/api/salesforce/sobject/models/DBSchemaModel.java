package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;

public class DBSchemaModel {

    @SerializedName("DeveloperName")
    private String developerName;

    @SerializedName("Label")
    private String label;

    @SerializedName("NamespacePrefix")
    private String namespacePrefix;

    @SerializedName(OCE_PREFIX + "SObject__c")
    private String sObject;

    @SerializedName(OCE_PREFIX + "IsActive__c")
    private boolean isActive;

    @SerializedName(OCE_PREFIX + "PermittedProfiles__c")
    private String permittedProfiles;

    public String getSObject() {
        return sObject;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public String getLabel() {
        return label;
    }

    public String getNamespacePrefix() {
        return namespacePrefix;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getPermittedProfiles() {
        return permittedProfiles;
    }
}
