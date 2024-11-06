package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FieldModel {

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("picklistValues")
    private List<PicklistValuesModel> picklistValues;

    public String getName() {
        return name;
    }

    public List<PicklistValuesModel> getPicklistValues() {
        return picklistValues;
    }

    public String getType() {
        return type;
    }
}
