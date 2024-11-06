package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

public class SalesforceObjectModel {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String sObjectType;

    public String getId() {
        return id;
    }

    public String getsObjectType() {
        return sObjectType;
    }
}
