package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("Id")
    private String id;

    @SerializedName("Username")
    private String username;

    @SerializedName("Name")
    private String name;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("IsActive")
    private Boolean isActive;

    @SerializedName("TimeZoneSidKey")
    private String timeZoneSidKey;

    @SerializedName("ManagerId")
    private String managerId;

    public String getManagerId() {
        return managerId;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getTimeZoneKey() {
        return timeZoneSidKey;
    }
}
