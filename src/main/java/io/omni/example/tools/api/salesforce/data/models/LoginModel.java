package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("instance_url")
    private String instanceUrl;

    public String getAccessToken() {
        return accessToken;
    }

    public String getInstanceUrl() {
        return instanceUrl;
    }
}
