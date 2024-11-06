package io.omni.example.tools.api.salesforce.fields;

import com.github.javafaker.Faker;
import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.Generate;
import io.omni.example.tools.api.HasLabel;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.HasType;
import io.omni.example.tools.api.salesforce.data.requests.PicklistRequests;
import io.omni.example.tools.api.salesforce.data.requests.SalesforceRequests;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.lang.String.format;

public enum AccountField implements HasName, HasLabel, HasType, Generate {

    FIRST_NAME("First Name", "FirstName") {
        @Override
        public Object generate() {
            return FAKER.name().firstName().replace("'", StringUtils.EMPTY);
        }
    },
    SPECIALITY("Specialty", "Specialty__c") {
        @Override
        public Object generate() {
            List<String> values = getPicklistValues();
            return values.get(FAKER.number().numberBetween(0, values.size()));
        }
    },
    LAST_NAME("Last Name", "LastName") {
        @Override
        public Object generate() {
            return FAKER.name().lastName().replace("'", StringUtils.EMPTY) + " " + FAKER.number().numberBetween(1, 999);
        }
    },
    NAME("Account Name", "Name") {
        @Override
        public Object generate() {
            return FAKER.company().name().replace("'", StringUtils.EMPTY).replace(",", StringUtils.EMPTY) + " Hospital";
        }
    },
    GENDER("Gender", "Gender__c") {
        @Override
        public Object generate() {
            List<String> values = getPicklistValues();
            return values.get(FAKER.number().numberBetween(0, values.size()));
        }
    },
    BIRTHDAY("Birthday", "Birthday__c") {
        @Override
        public Object generate() {
            return LocalDate
                    .now()
                    .minusYears(FAKER.number().numberBetween(18, 70))
                    .minusMonths(FAKER.number().numberBetween(0, 11))
                    .minusDays(FAKER.number().numberBetween(0, 30))
                    .toString();
        }
    },
    CREATED_BY("Created By ID", "CreatedById"),
    ABBREVIATED_NAME("Abbreviated Name", "AbbreviatedName__c"),
    PROFESSIONAL_TITLE("Professional Title", "ProfessionalTitle__c") {
        @Override
        public Object generate() {
            List<String> values = getPicklistValues();
            return values.get(FAKER.number().numberBetween(0, values.size()));
        }
    },
    RECORD_TYPE_ID("Record Type ID", "RecordTypeId"),
    SALUTATION("Salutation", "Salutation") {
        @Override
        public Object generate() {
            List<String> values = getPicklistValues();
            return values.get(FAKER.number().numberBetween(0, values.size()));
        }
    },
    MIDDLE_NAME("Middle Name", "MiddleName"),
    SUFFIX("Suffix", "Suffix"),
    TERRITORY("Territories", "Territories__c"),
    TERRITORY_MANUAL("TerritoriesManual", "TerritoriesManual__c"),
    NPI("NPI", "NPI__c") {
        @Override
        public Object generate() {
            return FAKER.number().digits(10);
        }
    },
    CALL_RECORD_TYPES("Call Record Types", "CallRecordTypes__c"),
    AVAILABLE_PAYMENT_TERMS("Available Payment Terms", "AvailablePaymentTerms__c"),
    DEFAULT_PAYMENT_TERM("Default Payment Term", "DefaultPaymentTerm__c"),
    ENABLE_ORDER("Enable Order", "EnableOrder__c"),
    ORDER_TYPE("Order Type", "OrderType__c"),
    PRIMARY_ACCOUNT_ADDRESS("Primary Account Address", "PrimaryAccountAddress__c"),
    RECORD_TYPE_NAME("Record Type Name", "RecordTypeName__c"),
    DESCRIPTION("Description", "Description"),
    COUNTRY("Country", "CountryCode__c"),
    SPECIALTY("Specialty", "Specialty__c") {
        @Override
        public Object generate() {
            List<String> values = getPicklistValues();
            values.remove("Allergy");
            return values.get(FAKER.number().numberBetween(0, values.size()));
        }
    },
    PHONE("Account Phone", "Phone") {
        @Override
        public Object generate() {
            return FAKER.phoneNumber().phoneNumber();
        }
    },
    WEBSITE("Website", "Website") {
        @Override
        public Object generate() {
            return FAKER.internet().url();
        }
    },
    PARENT_ACCOUNT("Parent Account", "ParentAccount__c"),
    LAST_OPT_UPDATE("Last Opt Update", "LastOptUpdate__c"),
    RESTRICTED_PRODUCTS_NAMES("Restricted Product Names", "RestrictedProductsNames__c"),
    PERSON_EMAIL("Email", "PersonEmail"),
    STATUS("Status", "Status__c");

    private static final Faker FAKER = new Faker(Locale.US);
    private final String name;
    private final String label;

    AccountField(String label, String name) {
        this.label = label;
        this.name = name;
    }

    public static AccountField getByName(String name) {
        return Arrays.stream(AccountField.values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown field=%s of object=%s\n",
                        name, SObject.ACCOUNT.getName())));
    }

    @Override
    public String getName() {
        return ManagePackageData.updateFieldName(this.name);
    }

    public String getLabel() {
        return this.label;
    }

    public List<String> getPicklistValues() {
        return new PicklistRequests()
                .getFieldPicklistValues(SObject.ACCOUNT, this);
    }

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("\nAuto data generation is not supported for " +
                "[object=%s, field=%s]\n", SObject.ACCOUNT.getName(), this));
    }

    @Override
    public FieldType getFieldType() {
        return new SalesforceRequests().getFieldType(SObject.ACCOUNT, this);
    }
}
