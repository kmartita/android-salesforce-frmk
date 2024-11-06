package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;

public class ProductModel {

    @SerializedName("Name")
    private String name;

    @SerializedName("Id")
    private String id;

    @SerializedName(OCE_PREFIX + "ParentProduct__c")
    private String parentProduct;

    @SerializedName(OCE_PREFIX + "DTP__c")
    private String dtp;

    @SerializedName("RecordTypeId")
    private String recordTypeId;

    @SerializedName(OCE_PREFIX + "Description__c")
    private String description;

    @SerializedName(OCE_PREFIX + "ParentTherapeuticArea__c")
    private String parentTherapeuticArea;

    @SerializedName(OCE_PREFIX + "ParentIndication__c")
    private String parentIndication;

    @SerializedName(OCE_PREFIX + "DetailType__c")
    private String detailType;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getParentProduct() {
        return parentProduct;
    }

    public String getDtp() {
        return dtp;
    }

    public boolean isDtp() {
        return getDtp().equals("Y");
    }

    public String getRecordTypeId() {
        return recordTypeId;
    }

    public String getParentTherapeuticArea() {
        return parentTherapeuticArea;
    }

    public String getParentIndication() {
        return parentIndication;
    }

    public String getDetailType() {
        return detailType;
    }

}
