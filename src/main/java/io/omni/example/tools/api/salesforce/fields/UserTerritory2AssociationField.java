package io.omni.example.tools.api.salesforce.fields;

import io.omni.example.tools.api.HasName;

public enum UserTerritory2AssociationField implements HasName {

    USER_ID("User", "UserId"),
    TERRITORY_2_ID("Territory", "Territory2Id");

    private final String name;
    private final String label;

    UserTerritory2AssociationField(String label, String name) {
        this.label = label;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label;
    }
}
