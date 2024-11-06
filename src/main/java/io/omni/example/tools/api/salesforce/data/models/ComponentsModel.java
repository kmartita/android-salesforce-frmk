package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

public class ComponentsModel {

    @SerializedName("value")
    private String value;

    public String getValue(){ return value; }
}
