package io.omni.example.tools.api.salesforce.fields;

import com.github.javafaker.Faker;
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
import java.util.Locale;

import static java.lang.String.format;

public enum AccountTerritoryProductField implements HasName, HasLabel, HasType, Generate {

    DECILE("Decile", "Decile__c") {
        @Override
        public Object generate() {
            return String.valueOf(faker.number().numberBetween(1, 10));
        }
    },
    PRODUCT("Product", "Product__c"),
    ACCOUNT("Account", "Account__c"),
    TERRITORY("Territory", "Territory__c"),
    @Custom
    ATP_TEXT1("ATP_Text1", "ATP_Text1__c"),
    @Custom
    ATP_TEXT2("ATP_Text2", "ATP_Text2__c"),
    @Custom
    ATP_TEXT3("ATP_Text3", "ATP_Text3__c"),
    @Custom
    ATP_TEXT4("ATP_Text4", "ATP_Text4__c"),
    @Custom
    ATP_TEXT5("ATP_Text5", "ATP_Text5__c"),
    @Custom
    ATP_TEXT6("ATP_Text6", "ATP_Text6__c");

    private final String name;
    private final String label;

    private static final Faker faker = new Faker(Locale.US);

    AccountTerritoryProductField(String label, String name) {
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

    public static AccountTerritoryProductField getByName(String name) {
        return Arrays.stream(AccountTerritoryProductField.values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown field=%s of object=%s\n",
                        name, SObject.ACCOUNT_TERRITORY_PRODUCT.getName())));
    }

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("\nAuto data generation is not supported for " +
                "[object=%s, field=%s]\n", SObject.ACCOUNT_TERRITORY_PRODUCT.getName(), this));
    }

    @Override
    public FieldType getFieldType() {
        return new SalesforceRequests().getFieldType(SObject.ACCOUNT_TERRITORY_PRODUCT,this);
    }
}
