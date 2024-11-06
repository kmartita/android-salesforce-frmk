package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

public class LayoutModel {

    @SerializedName("Id")
    private String id;

    @SerializedName("FullName")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }
}
