package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;

public class UserTerritory2AssociationModel {

    @SerializedName("Id")
    private String id;

    @SerializedName("Territory2Id")
    private String territory2Id;

    @SerializedName("UserId")
    private String userId;

    public String getId() {
        return id;
    }

    public String getTerritory2Id() {
        return territory2Id;
    }

    public String getUserId() {
        return userId;
    }
}
