package io.omni.example.tools.api.salesforce.fields;

import com.github.javafaker.Faker;
import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.Generate;
import io.omni.example.tools.api.HasLabel;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.Custom;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Arrays;
import java.util.Locale;

import static java.lang.String.format;

public enum AccountFilterField implements HasName, HasLabel, Generate {

    NAME("Filter Name", "Name") {
        @Override
        public Object generate() {
            return "AQA - " + faker.artist().name() + faker.lorem().word();
        }
    },
    RECORD_TYPE("Record Type", "RecordTypeId") {
        @Override
        public Object generate() {
            throwUnsupportedOperationException(this);
            return null;
        }
    },
    IS_ROUTE("Is Route", "IsRoute__c"),
    MULTI_DAYS_ROUTE("Multi Days Route", "MultiDaysRoute__c"),
    TIME_ZONE("Time Zone", "TimeZone__c"),
    @Custom
    DESCRIPTION("Description", "Description__c");

    private static final Faker faker = new Faker(Locale.US);

    private final String label;
    private final String name;

    AccountFilterField(String label, String apiName) {
        this.label = label;
        this.name = apiName;
    }

    @Override
    public String getName() {
        boolean isCustom = FieldUtils.getField(this.getClass(), this.name()).isAnnotationPresent(Custom.class);
        return isCustom ? name : ManagePackageData.updateFieldName(this.name);
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("\nAuto data generation is not supported for " +
                "[object=%s, field=%s]\n", SObject.ACCOUNT_FILTER.getName(), this));
    }

    private static void throwUnsupportedOperationException(AccountFilterField field) {
        throw new UnsupportedOperationException(
                "\nAuto data generation is not supported for <Account Filter> Field: <" + field + ">\n");
    }

    public static AccountFilterField getByName(String name) {
        return Arrays.stream(AccountFilterField.values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown field=%s of object=%s\n",
                        name, SObject.ACCOUNT_FILTER.getName())));
    }

}
