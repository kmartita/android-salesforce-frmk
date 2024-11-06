package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

public class PermissionSetAssignmentModel {

    @SerializedName("Id")
    private String id;

    public String getId() {
        return id;
    }
}
