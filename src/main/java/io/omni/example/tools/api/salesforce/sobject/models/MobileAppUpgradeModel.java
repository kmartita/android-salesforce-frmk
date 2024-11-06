package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;
import io.omni.example.tools.utilities.DateTimeUtils;

import java.time.LocalDate;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;

public class MobileAppUpgradeModel {

    @SerializedName("Id")
    private String id;

    @SerializedName(OCE_PREFIX + "Release_Date__c")
    private String releaseDate;

    public String getId() {
        return id;
    }

    public LocalDate getReleaseDate() {
        return (releaseDate == null) ? null : DateTimeUtils.convertStringToLocalDate(releaseDate, "yyyy-MM-dd");
    }
}
