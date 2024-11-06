package io.omni.example.tools.api.salesforce.fields;

import io.omni.example.tools.ManagePackageData;
import io.omni.example.tools.api.HasName;

public enum UserField implements HasName {

    USER_NAME("Username"),
    PREFERENCES("Preferences__c"),
    MDM_PREFERRED_COUNTRY_IMS("MDM_Preferred_Country_ims__c"),
    FIRST_NAME("FirstName"),
    LAST_NAME("LastName"),
    IS_ACTIVE("IsActive"),
    EMAIL("Email"),
    ALIAS("Alias"),
    PHONE("Phone"),
    MOBILE_PHONE("MobilePhone"),
    STREET("Street"),
    COMMUNITY_NICKNAME("CommunityNickname"),
    TIMEZONE_SID_KEY("TimeZoneSidKey"),
    LOCALE_SID_KEY("LocaleSidKey"),
    EMAIL_ENCODING_KEY("EmailEncodingKey"),
    DEFAULT_CURRENCY_ISO_CODE("DefaultCurrencyIsoCode"),
    CURRENCY_ISO_CODE("CurrencyIsoCode"),
    PROFILE_ID("ProfileId"),
    MANAGER_ID("ManagerId"),
    LANGUAGE_LOCALE_KEY("LanguageLocaleKey"),
    OK_PREFERRED_COUNTRY_IMS("OK_Preferred_Country_ims__c");

    private final String name;

    UserField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return ManagePackageData.updateFieldName(this.name);
    }

}
