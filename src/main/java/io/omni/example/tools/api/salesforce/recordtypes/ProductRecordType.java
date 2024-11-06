package io.omni.example.tools.api.salesforce.recordtypes;

import io.omni.example.tools.api.IsRecordType;
import io.omni.example.tools.api.salesforce.data.requests.RecordTypeRequests;
import io.omni.example.tools.api.salesforce.sobject.SObject;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;

public enum ProductRecordType implements IsRecordType {

    BRAND("Brand", "Brand"),
    DETAIL("Detail", "Detail"),
    SAMPLE("Sample", "Sample"),
    ITEM("Item", "Item"),
    HIERARCHY("Hierarchy", "Hierarchy");

    private final String uiName;
    private final String apiName;

    ProductRecordType(String uiName, String apiName) {
        this.uiName = uiName;
        this.apiName = apiName;
    }

    @Override
    public String getUiName() {
        return uiName;
    }

    @Override
    public String getApiName() {
        return OCE_PREFIX + this.apiName;
    }

    public String getRecordTypeId(){
        return new RecordTypeRequests()
                .getSObjectRecordTypeId(SObject.PRODUCT, this);
    }

    @Override
    public String getMetadataName() {
        return getMetadataName(SObject.PRODUCT);
    }

}
