package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

public class RecordTypeModel {

    @SerializedName("Id")
    private String id;

    @SerializedName("Name")
    private String name;

    @SerializedName("SobjectType")
    private String sObjectType;

    @SerializedName("DeveloperName")
    private String developerName;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getsObjectType() { return sObjectType; }

    public String getDeveloperName() { return developerName; }

}
