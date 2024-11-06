package io.omni.example.tools.api.salesforce.fields;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.Generate;
import io.omni.example.tools.api.HasLabel;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.HasType;
import io.omni.example.tools.api.salesforce.Custom;
import io.omni.example.tools.api.salesforce.data.requests.SalesforceRequests;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Arrays;

import static java.lang.String.format;

public enum AccountTerritoryFieldsField implements HasName, HasLabel, HasType, Generate {

    ANNUAL_CALL_GOAL("Annual Call Goal", "AnnualCallGoal__c"),
    CALL_PLAN_GOAL("Call Plan Goal", "POACallGoal__c"),
    NEXT_CALL_DATE("Next Call Date", "NextCallDate__c"),
    TERRITORY("Territory", "Territory__c"),
    ACCOUNT("Account", "Account__c"),
    PREFERRED_ADDRESS("Preferred Address", "PreferredAddress__c"),
    ACCOUNT_RELATIONSHIP( "Account","Account__r"),
    AVAILABLE_OFFLINE("Available Offline","AvailableOffline__c"),
    OFFLINE_DOWNLOAD_OVERRIDE("Offline Download Override","OfflineDownloadOverride__c"),
    @Custom
    ATF_TEXT1("ATF_Text1", "ATF_Text1__c"),
    @Custom
    ATF_TEXT2("ATF_Text2", "ATF_Text2__c"),
    @Custom
    ATF_TEXT3("ATF_Text3", "ATF_Text3__c"),
    @Custom
    ATF_TEXT4("ATF_Text4", "ATF_Text4__c"),
    @Custom
    ATF_TEXT5("ATF_Text5", "ATF_Text5__c"),
    @Custom
    ATF_TEXT6("ATF_Text6", "ATF_Text6__c");

    private final String name;
    private final String label;

    AccountTerritoryFieldsField(String label, String name) {
        this.label = label;
        this.name = name;
    }

    @Override
    public String getName() {
        boolean isCustom = FieldUtils.getField(this.getClass(), this.name()).isAnnotationPresent(Custom.class);
        return isCustom ? name : ManagePackageData.updateFieldName(this.name);
    }

    public String getLabel() {
        return this.label;
    }

    public static AccountTerritoryFieldsField getByName(String name) {
        return Arrays.stream(AccountTerritoryFieldsField.values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown field=%s of object=%s\n",
                        name, SObject.ACCOUNT_TERRITORY_FIELDS.getName())));
    }

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("\nAuto data generation is not supported for " +
                "[object=%s, field=%s]\n", SObject.ACCOUNT_TERRITORY_FIELDS.getName(), this));
    }

    @Override
    public FieldType getFieldType() {
        return new SalesforceRequests().getFieldType(SObject.ACCOUNT_TERRITORY_FIELDS,this);
    }
}
