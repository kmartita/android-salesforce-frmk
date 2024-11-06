package io.omni.example.tools.api;

import io.omni.example.tools.api.salesforce.sobject.SObject;

public interface IsRecordType {

    String getUiName();
    String getApiName();
    String getMetadataName();

    default String getMetadataName(SObject object) {
        return object.getName() + "." + this.getApiName();
    }

}
