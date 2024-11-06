package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;

public class Territory2Model {

    @SerializedName("Id")
    private String id;

    @SerializedName("DeveloperName")
    private String developerName;

    @SerializedName("Name")
    private String name;

    public String getId() {
        return id;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public String getName() {
        return name;
    }
}
