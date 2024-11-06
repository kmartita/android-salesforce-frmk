package io.omni.example.tools;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;
import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.QIDC_PREFIX;

public class ManagePackageData {

    public static String getPackagePrefix() {
        return OCE_PREFIX;
    }
    public static String getQIDCPrefix() { return QIDC_PREFIX; }

    public static String updateFieldName(String fieldName) {
        return ((fieldName.endsWith("__c") && !fieldName.contains("_ims_")) || fieldName.endsWith("__mdt")
                || fieldName.endsWith("__r") || fieldName.endsWith("__Share") || fieldName.endsWith("__s")) ? getPackagePrefix() + fieldName :
                fieldName.endsWith("_ims__c") ? getQIDCPrefix() + fieldName : fieldName;
    }
}
