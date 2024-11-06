package io.omni.example.tools.api.salesforce.recordtypes;

import io.omni.example.tools.api.IsRecordType;
import io.omni.example.tools.api.salesforce.data.requests.RecordTypeRequests;
import io.omni.example.tools.api.salesforce.sobject.SObject;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;

public enum AccountFilterRecordType implements IsRecordType {

    FILTER("Filter"),
    LIST("List");

    private final String name;

    AccountFilterRecordType(String name) {
        this.name = name;
    }

    public String getRecordTypeId(){
        return new RecordTypeRequests()
                .getSObjectRecordTypeId(SObject.ACCOUNT_FILTER, this);
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
        return getMetadataName(SObject.ACCOUNT_FILTER);
    }

}
