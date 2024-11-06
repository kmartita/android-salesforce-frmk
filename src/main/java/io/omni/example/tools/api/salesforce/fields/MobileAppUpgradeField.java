package io.omni.example.tools.api.salesforce.fields;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.HasName;

public enum MobileAppUpgradeField implements HasName {

    RELEASE_DATE("Release_Date__c");

    private final String name;

    MobileAppUpgradeField(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return ManagePackageData.updateFieldName(this.name);
    }
}
