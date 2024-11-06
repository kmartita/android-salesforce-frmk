package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;

public class AccountModel {

    @SerializedName("Id")
    private String id;

    @SerializedName("Name")
    private String name;

    @SerializedName(OCE_PREFIX + "Territories__c")
    private String territories;

    @SerializedName(OCE_PREFIX + "PrimaryAccountAddress__c")
    private String addressId;

    @SerializedName("RecordTypeId")
    private String recordTypeId;

    @SerializedName(OCE_PREFIX + "Specialty__c")
    private String specialty;

    @SerializedName("LastModifiedDate")
    private String lastModifiedDate;

    @SerializedName("Description")
    private String description;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName(OCE_PREFIX + "RecordTypeName__c")
    private String recordTypeName;

    @SerializedName(OCE_PREFIX + "OfflineUniqueId__c")
    private String offlineUniqueId;

    @SerializedName(OCE_PREFIX + "ProfessionalTitle__c")
    private String professionalTitle;

    public String getOfflineUniqueId() {
        return offlineUniqueId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return (name == null) ? StringUtils.EMPTY : name;
    }

    public String getTerritories() {
        return territories;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getRecordTypeId() {
        return recordTypeId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getLastName() {
        return (lastName == null) ? StringUtils.EMPTY : lastName;
    }

    public String getFirstName() {
        return (firstName == null) ? StringUtils.EMPTY : firstName;
    }

    public LocalDateTime getLastModifiedDateAsLocalDateTime() {
        return LocalDateTime.parse(getLastModifiedDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS"));
    }

    public String getRecordTypeName() {
        return recordTypeName;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

}
