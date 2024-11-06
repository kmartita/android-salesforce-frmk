package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;

public class ProfileModel {

    @SerializedName("Id")
    private String id;

    @SerializedName("Username")
    private String username;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
