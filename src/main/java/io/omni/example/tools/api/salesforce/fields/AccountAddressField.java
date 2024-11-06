package io.omni.example.tools.api.salesforce.fields;

import com.github.javafaker.Faker;
import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.Generate;
import io.omni.example.tools.api.HasLabel;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.HasType;
import io.omni.example.tools.api.salesforce.data.requests.SalesforceRequests;
import io.omni.example.tools.api.salesforce.sobject.SObject;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

import static java.lang.String.format;

public enum AccountAddressField implements HasName, HasLabel, HasType, Generate {

    ADDRESS_LINE_1("Address Line 1", "AddressLine1__c"),
    ADDRESS_LINE_2("Address Line 2", "AddressLine2__c"),
    ADDRESS_LINE_3("Address Line 3", "AddressLine3__c"),
    CITY("City", "City__c"){
        @Override
        public Object generate() { return FAKER.address().city(); }
    },
    COUNTRY("Country", "Country__c"),
    DEA_LICENSE_ADDRESS("DEA License Address", "DeaLicenseAddress__c"),
    WORK_PHONE("Work Phone", "Phone__c"),
    ZIP_CODE("Zip Code", "ZipCode__c"),
    WEB_ADDRESS("Web Address", "WebAddress__c") {
        @Override
        public Object generate() { return FAKER.internet().url(); }
    },
    MAIL_RESTRICTION("Mail Restriction", "MailRestriction__c") {
        @Override
        public Object generate() {
            return FAKER.bool().bool();
        }
    },
    FAX_RESTRICTION("Fax Restriction", "FaxRestriction__c") {
        @Override
        public Object generate() {
            return FAKER.bool().bool();
        }
    },
    VISIT_RESTRICTION("Visit Restriction", "VisitRestriction__c") {
        @Override
        public Object generate() {
            return FAKER.bool().bool();
        }
    },
    CALL_RESTRICTION("Call Restriction", "CallRestriction__c") {
        @Override
        public Object generate() {
            return false;
        }
    },
    EMAIL_RESTRICTION("Email Restriction", "EmailRestriction__c") {
        @Override
        public Object generate() {
            return FAKER.bool().bool();
        }
    },
    PHONE("Work Phone", "Phone__c") {
        @Override
        public Object generate() {
            String number = FAKER.phoneNumber().phoneNumber();
            return number.substring(0, Math.min(number.length(), 10));
        }
    },
    FAX__C("Fax", "Fax__c") {
        @Override
        public Object generate() {
            return FAKER.phoneNumber().phoneNumber();
        }
    },
    PHONE2("Mobile Phone", "Phone2__c") {
        @Override
        public Object generate() {
            return FAKER.phoneNumber().phoneNumber();
        }
    },
    EMAIL("Email", "Email__c") {
        @Override
        public Object generate() {
            return FAKER.internet().emailAddress();
        }
    },
    IS_PRIMARY("Primary", "IsPrimary__c") {
        @Override
        public Object generate() {
            return true;
        }
    },
    LICENSE_EXPIRATION_DATE("License Expiration Date", "LicenseExpirationDate__c") {
        @Override
        public Object generate() {
            return LocalDate.now().plusMonths(100).toString();
        }
    },
    LICENSE_NUMBER("License #", "LicenseNumber__c") {
        @Override
        public Object generate() {
            return String.valueOf(FAKER.number().numberBetween(10000, 99999));
        }
    },
    IS_COPY_TDDD_DATA("Is Copy TDDD Data", "IsCopyTDDDData__c"){
        @Override
        public Object generate(){
            return true;
        }
    },
    ACCOUNT("Account", "Account__c"),
    ADDRESS("Address", "Address__c"),
    ADDRESS_TYPE("Address Type", "AddressType__c") {
        @Override
        public Object generate() {
            return "Billing";
        }
    },
    PREFERRED_ADDRESS("Preferred Address", "FullAddress__c"),
    WORKPLACE("Workplace", "Workplace__c"),
    DEA("DEA", "DEA__c"),
    DEA_EXPIRATION_DATE("DEA Expiration Date", "DeaExpirationDate__c"),
    STATE("State", "State__c");

    private final String name;
    private final String label;

    private static final Faker FAKER = new Faker(Locale.US);

    AccountAddressField(String label, String name) {
        this.label = label;
        this.name = name;
    }

    @Override
    public String getName() {
        return ManagePackageData.updateFieldName(this.name);
    }
    public String getLabel() {
        return this.label;
    }

    public static AccountAddressField getByName(String name) {
        return Arrays.stream(AccountAddressField.values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown field=%s of object=%s\n",
                        name, SObject.ACCOUNT_ADDRESS.getName())));
    }

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("\nAuto data generation is not supported for " +
                "[object=%s, field=%s]\n", SObject.ACCOUNT_ADDRESS.getName(), this));
    }

    @Override
    public FieldType getFieldType() {
        return new SalesforceRequests().getFieldType(SObject.ACCOUNT_ADDRESS,this);
    }
}
