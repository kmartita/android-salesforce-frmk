package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;

public class ConfigurationModel {

    @SerializedName("Id")
    private String id;

    @SerializedName("SetupOwnerId")
    private String setupOwnerId;

    public String getId() {
        return id;
    }

    public String getSetupOwnerId() {
        return setupOwnerId;
    }
}
