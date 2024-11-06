package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

public class RecordTypeInfoModel {

    @SerializedName("name")
    private String name;

    @SerializedName("recordTypeId")
    private String recordTypeId;

    @SerializedName("available")
    private boolean isAvailable;

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getRecordTypeId() {
        return recordTypeId;
    }

}
