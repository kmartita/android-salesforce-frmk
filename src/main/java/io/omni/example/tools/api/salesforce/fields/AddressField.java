package io.omni.example.tools.api.salesforce.fields;

import com.github.javafaker.Faker;
import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.Generate;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.sobject.SObject;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;

import static java.lang.String.format;

public enum AddressField implements HasName, Generate {

    ADDRESS_NAME("Address Line 1", "Name") {
        @Override
        public Object generate() {
            return faker.address().streetAddress(true);
        }
    },
    CITY("City", "City__c") {
        @Override
        public Object generate() {
            return faker.address().city();
        }
    },
    ADDRESS_LINE_2("Address Line 2", "AddressLine2__c") {
        @Override
        public Object generate() {
            return faker.address().streetAddress(true);
        }
    },
    COUNTRY("Country", "Country__c") {
        @Override
        public Object generate() {
            return "United States";
        }
    },
    COUNTRY_CODE("Country", "CountryCode__c") {
        @Override
        public Object generate() {
            return "US";
        }
    },
    STATE("State", "State__c") {
        @Override
        public Object generate() {
            return "Hawaii";
        }
    },
    STATE_CODE("State", "StateCode__c") {
        @Override
        public Object generate() {
            return "WV";
        }
    },
    ZIP_CODE("Zip Code", "ZipCode__c") {
        @Override
        public Object generate() {
            return faker.address().zipCode();
        }
    },
    IS_VALIDATED("Validated", "IsValidated__c") {
        @Override
        public Object generate() {
            return true;
        }
    },
    TDDD_LICENSE_EXEMPTION("TDDD License Exemption", "IsTDDDLicenseExemption__c") {
        @Override
        public Object generate() {
            return true;
        }
    },
    TDDD_LICENSE_EXPIRATION_DATE("TDDD License expiration Date", "TDDDExpirationDate__c") {
        @Override
        public Object generate() {
            return LocalDateTime.now().plusYears(1).toString();
        }
    },
    TDDD_CATEGORY("TDDD Category", "TDDDCategory__c") {
        @Override
        public Object generate() {
            return "Category II";
        }
    },
    TDDD_SUBCATEGORY("TDDD Sub-category", "TDDDSubcategory__c") {
        @Override
        public Object generate() {
            return "02";
        }
    },
    TDDD_LICENSE_STATUS("TDDD License Status", "TDDDLicenseStatus__c") {
        @Override
        public Object generate() {
            return "Active";
        }
    },
    TDDD_LICENSE_NUMBER("TDDD License #", "TDDDLicense__c") {
        @Override
        public Object generate() {
            return String.valueOf(faker.number().randomNumber(10, true));
        }
    },
    GEO_LOCATION_LATITUDE("GeoLocation (Latitude)", "GeoLocation__Latitude__s"),
    GEO_LOCATION_LONGITUDE("GeoLocation (Longitude)", "GeoLocation__Longitude__s");

    private final String name;
    private final String label;

    private static final Faker faker = new Faker(Locale.US);

    AddressField(String label, String name) {
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

    public static AddressField getByName(String name) {
        return Arrays.stream(AddressField.values())
                .filter(value -> value.getLabel().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown field=%s of object=%s\n",
                        name, SObject.ADDRESS.getName())));
    }

    @Override
    public Object generate() {
        throw new UnsupportedOperationException(format("\nAuto data generation is not supported for " +
                "[object=%s, field=%s]\n", SObject.ADDRESS.getName(), this));
    }
}
