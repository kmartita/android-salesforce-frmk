package io.omni.example.tools.api.salesforce.recordtypes;

import io.omni.example.tools.api.IsRecordType;
import io.omni.example.tools.api.salesforce.data.requests.RecordTypeRequests;
import io.omni.example.tools.api.salesforce.sobject.SObject;

import java.util.Arrays;
import java.util.List;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;
import static java.lang.String.format;

public enum AccountRecordType implements IsRecordType {

    BC("BC (Business Contact)", "BC"),
    DEPARTMENT("Department"),
    GPO("GPO (Group Purchasing Organization)", "GPO"),
    IDN("IDN (Integrated Delivery Network)", "IDN"),
    INSTITUTION("Institution"),
    MP("MP (Medical Professional)", "MP"),
    PERSON_ACCOUNT("Person Account", "PersonAccount"),
    PHARMACY("Pharmacy"),
    PRACTICE("Practice"),
    WHOLESALER("Wholesaler"),
    HCO("HCO"){
        @Override
        public String getApiName(){
            return "HCO";
        }
    };

    private final String uiName;
    private final String apiName;

    AccountRecordType(String uiName) {
        this.uiName = uiName;
        this.apiName = uiName;
    }

    AccountRecordType(String uiName, String apiName) {
        this.uiName = uiName;
        this.apiName = apiName;
    }

    public static AccountRecordType getByUiName(String uiName) {
        return Arrays.stream(AccountRecordType.values())
                .filter(value -> value.getUiName().equalsIgnoreCase(uiName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown record type=%s of object=%s\n",
                        uiName, SObject.ACCOUNT.getName())));
    }

    public static AccountRecordType getByAbbreviationName(String uiName) {
        return Arrays.stream(AccountRecordType.values())
                .filter(value -> value.getAbbreviationName().equals(uiName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown record type=%s of object=%s\n",
                        uiName, SObject.ACCOUNT.getName())));
    }

    public static List<String> getAccountSearchRecordTypesForSalesRepProfile() {
        return Arrays.asList(MP.getAbbreviationName(),
                BC.getAbbreviationName(), INSTITUTION.getAbbreviationName(),
                DEPARTMENT.getAbbreviationName(), GPO.getAbbreviationName(),
                PHARMACY.getAbbreviationName(), WHOLESALER.getAbbreviationName());
    }

    @Override
    public String getUiName() {
        return uiName;
    }

    @Override
    public String getApiName() {
        return OCE_PREFIX + this.apiName;
    }

    public String getAbbreviationName() {
        return this.apiName;
    }

    public String getRecordTypeId() {
        return new RecordTypeRequests()
                .getSObjectRecordTypeId(SObject.ACCOUNT, this);
    }

    @Override
    public String getMetadataName() {
        String metadataPrefix = (this == BC || this == MP || this == PERSON_ACCOUNT)
                ? SObject.PERSON_ACCOUNT.getName()
                : SObject.ACCOUNT.getName();

        String metadataSuffix = (this == PERSON_ACCOUNT)
                ? this.apiName
                : OCE_PREFIX + this.apiName;

        return metadataPrefix + "." + metadataSuffix;
    }
}
