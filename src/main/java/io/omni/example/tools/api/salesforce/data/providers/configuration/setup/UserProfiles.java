package io.omni.example.tools.api.salesforce.data.providers.configuration.setup;

import io.omni.example.tools.api.HasName;

public enum UserProfiles implements HasName {

    OCE_ADMIN("OCE Admin"),
    SALES_REPRESENTATIVE("Sales Representative"),
    MEDICAL_SCIENCE_LIAISON("Medical Science Liaison"),
    KEY_ACCOUNT_MANAGER("Key Account Manager");

    private final String name;

    UserProfiles(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
