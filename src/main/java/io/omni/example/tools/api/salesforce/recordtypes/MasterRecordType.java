package io.omni.example.tools.api.salesforce.recordtypes;

import io.omni.example.tools.api.IsRecordType;
import io.omni.example.tools.api.salesforce.data.requests.RecordTypeRequests;
import io.omni.example.tools.api.salesforce.sobject.SObject;

public enum MasterRecordType implements IsRecordType {

    MASTER("Master");

    private final String name;

    MasterRecordType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getId(SObject sObject){
        return new RecordTypeRequests()
                .getSObjectRecordTypeId(sObject, this);
    }

    @Override
    public String getUiName() {
        return name;
    }

    @Override
    public String getApiName() {
        return name;
    }

    @Override
    public String getMetadataName() {
        return this.getApiName();
    }

}
