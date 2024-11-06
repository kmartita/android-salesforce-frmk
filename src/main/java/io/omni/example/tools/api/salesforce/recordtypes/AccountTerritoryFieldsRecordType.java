package io.omni.example.tools.api.salesforce.recordtypes;

import io.omni.example.tools.api.IsRecordType;
import io.omni.example.tools.api.salesforce.data.requests.RecordTypeRequests;
import io.omni.example.tools.api.salesforce.sobject.SObject;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;

public enum AccountTerritoryFieldsRecordType implements IsRecordType {

    HCO("HCO"),
    HCP("HCP");

    private final String name;

    AccountTerritoryFieldsRecordType(String name) {
        this.name = name;
    }

    public String getRecordTypeId(){
        return new RecordTypeRequests()
                .getSObjectRecordTypeId(SObject.ACCOUNT_TERRITORY_FIELDS, this);
    }

    @Override
    public String getUiName() {
        return this.name;
    }

    @Override
    public String getApiName() {
        return OCE_PREFIX + this.name;
    }

    @Override
    public String getMetadataName() {
        return getMetadataName(SObject.ACCOUNT_TERRITORY_FIELDS);
    }
}
