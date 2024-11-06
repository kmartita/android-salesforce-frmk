package io.omni.example.tools.api.salesforce.fields;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.HasName;

public enum TerritoryProductExclusion implements HasName {

    PRODUCT("Product", "Product__c"),
    TERRITORY("Territory", "Territory__c");

    private final String name;
    private final String label;

    TerritoryProductExclusion(String label, String name) {
        this.label = label;
        this.name = name;
    }

    @Override
    public String getName() {
        return ManagePackageData.updateFieldName(this.name);
    }
    public String getLabel() {return this.label;}
}
