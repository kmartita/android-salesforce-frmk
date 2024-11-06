package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectPicklistFieldValuesModel {

    @SerializedName("values")
    private List<PicklistValuesModel> values;

    public List<PicklistValuesModel> getPicklistValues(){
        return values;
    }
}
