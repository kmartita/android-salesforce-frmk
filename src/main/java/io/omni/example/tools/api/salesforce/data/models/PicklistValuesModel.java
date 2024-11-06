package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

public class PicklistValuesModel {

    @SerializedName("value")
    private String value;

    @SerializedName("label")
    private String label;

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
