package io.omni.example.activation;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.data.providers.configuration.AccountFilterConfigurationProvider;
import io.omni.example.tools.api.salesforce.data.providers.configuration.PermissionSetConfigurationProvider;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.PermissionSets;
import io.omni.example.tools.api.salesforce.fields.*;
import io.omni.example.tools.api.salesforce.recordtypes.AccountRecordType;
import io.omni.example.tools.api.salesforce.recordtypes.AccountTerritoryFieldsRecordType;
import io.omni.example.tools.api.salesforce.recordtypes.ProductRecordType;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.metadata.*;
import io.omni.example.tools.properties.PropertyManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static io.omni.example.tools.api.salesforce.data.providers.configuration.setup.PermissionSets.ATF_BULK;
import static io.omni.example.tools.api.salesforce.data.providers.configuration.setup.PermissionSets.ATP_BULK;
import static io.omni.example.tools.api.salesforce.data.providers.configuration.setup.UserProfiles.OCE_ADMIN;
import static io.omni.example.tools.metadata.MetadataFieldSetUtils.FieldSets.ATF_BULK_UPDATE;
import static io.omni.example.tools.metadata.MetadataFieldSetUtils.FieldSets.ATP_BULK_UPDATE;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class AccountFilterActivation {

    protected static final SfUserModel FIRST_SALES_REP_USER = PropertyManager.getFirstSalesRepUser();

    private static final List<String> ATF_BULK_UPDATE_FIELD_SET = new ArrayList<>(asList(
            AccountTerritoryFieldsField.ATF_TEXT1.getName(),
            AccountTerritoryFieldsField.ATF_TEXT2.getName(),
            AccountTerritoryFieldsField.ATF_TEXT3.getName(),
            AccountTerritoryFieldsField.ATF_TEXT4.getName(),
            AccountTerritoryFieldsField.ATF_TEXT5.getName(),
            AccountTerritoryFieldsField.ATF_TEXT6.getName()));
    private static final List<String> ATP_BULK_UPDATE_FIELD_SET = new ArrayList<>(asList(
            AccountTerritoryProductField.ATP_TEXT1.getName(),
            AccountTerritoryProductField.ATP_TEXT2.getName(),
            AccountTerritoryProductField.ATP_TEXT3.getName(),
            AccountTerritoryProductField.ATP_TEXT4.getName(),
            AccountTerritoryProductField.ATP_TEXT5.getName(),
            AccountTerritoryProductField.ATP_TEXT6.getName()));

    private final AccountFilterConfigurationProvider accountFilterConfigurationProvider = new AccountFilterConfigurationProvider();
    private final MetadataProfileUtils metadataProfileUtils = new MetadataProfileUtils();
    private final MetadataFieldSetUtils metadataFieldSetUtils = new MetadataFieldSetUtils();
    private final MetadataCustomFieldUtils metadataCustomFieldUtils = new MetadataCustomFieldUtils();
    private final MetadataPermissionSetUtils metadataPermissionSetUtils = new MetadataPermissionSetUtils();
    private final PermissionSetConfigurationProvider permissionSetConfigurationProvider = new PermissionSetConfigurationProvider();
    private final MetadataCustomUtils metadataCustomUtils = new MetadataCustomUtils();

    public void activateAccountFilters() {
        activateAccountBulkFilterSets();
    }

    public void activateAccountBulkFilterSets() {
        Stream.of(AccountTerritoryFieldsField.ATF_TEXT1,
                        AccountTerritoryFieldsField.ATF_TEXT2,
                        AccountTerritoryFieldsField.ATF_TEXT3,
                        AccountTerritoryFieldsField.ATF_TEXT4,
                        AccountTerritoryFieldsField.ATF_TEXT5,
                        AccountTerritoryFieldsField.ATF_TEXT6)
                .forEach(field -> {
                    metadataCustomFieldUtils.createCustomTextField(SObject.ACCOUNT_TERRITORY_FIELDS, field);
                    metadataProfileUtils.configureFieldLevelSecurity(OCE_ADMIN,
                            SObject.ACCOUNT_TERRITORY_FIELDS, field, true, true);
                });

        Stream.of(AccountTerritoryProductField.ATP_TEXT1,
                        AccountTerritoryProductField.ATP_TEXT2,
                        AccountTerritoryProductField.ATP_TEXT3,
                        AccountTerritoryProductField.ATP_TEXT4,
                        AccountTerritoryProductField.ATP_TEXT5,
                        AccountTerritoryProductField.ATP_TEXT6)
                .forEach(field -> {
                    metadataCustomFieldUtils.createCustomTextField(SObject.ACCOUNT_TERRITORY_PRODUCT, field);
                    metadataProfileUtils.configureFieldLevelSecurity(OCE_ADMIN,
                            SObject.ACCOUNT_TERRITORY_PRODUCT, field, true, true);
                });

        metadataFieldSetUtils.configureFieldSet(ATF_BULK_UPDATE, ATF_BULK_UPDATE_FIELD_SET);
        metadataFieldSetUtils.configureFieldSet(ATP_BULK_UPDATE, ATP_BULK_UPDATE_FIELD_SET);

        List<PermissionSets> bulkPermissionSets = Arrays.asList(ATF_BULK, ATP_BULK);
        bulkPermissionSets.forEach(permissionSet -> {
            metadataPermissionSetUtils.createNewPermissionSetByName(permissionSet);
            metadataPermissionSetUtils.configureCrudObjectPermissionsForPermissionSet(permissionSet, SObject.CONTACT,
                    true, false, false, false);
            metadataPermissionSetUtils.configureCrudObjectPermissionsForPermissionSet(permissionSet,
                    SObject.ACCOUNT, true, true, true, true);
            metadataPermissionSetUtils.configureAssignmentRecordTypesForPermissionSet(permissionSet,
                    AccountRecordType.BC,
                    AccountRecordType.DEPARTMENT,
                    AccountRecordType.GPO,
                    AccountRecordType.IDN,
                    AccountRecordType.INSTITUTION,
                    AccountRecordType.MP,
                    AccountRecordType.PHARMACY,
                    AccountRecordType.WHOLESALER);
            metadataPermissionSetUtils.updateFieldPermissionSet(permissionSet, SObject.ACCOUNT, AccountField.TERRITORY,
                    true, true);
        });

        /* ATF Bulk **/
        metadataPermissionSetUtils.configureCrudObjectPermissionsForPermissionSet(ATF_BULK,
                SObject.ACCOUNT_TERRITORY_FIELDS, true, true, true, true);
        metadataPermissionSetUtils.configureAssignmentRecordTypesForPermissionSet(ATF_BULK,
                AccountTerritoryFieldsRecordType.HCO,
                AccountTerritoryFieldsRecordType.HCP);

        Stream.of(AccountTerritoryFieldsField.TERRITORY,
                AccountTerritoryFieldsField.ATF_TEXT1,
                AccountTerritoryFieldsField.ATF_TEXT2).forEach(field -> metadataPermissionSetUtils.updateFieldPermissionSet(ATF_BULK,
                SObject.ACCOUNT_TERRITORY_FIELDS, field, true, true));

        Stream.of(AccountTerritoryFieldsField.ATF_TEXT3,
                AccountTerritoryFieldsField.ATF_TEXT4).forEach(field -> metadataPermissionSetUtils.updateFieldPermissionSet(ATF_BULK,
                SObject.ACCOUNT_TERRITORY_FIELDS, field, true, false));

        /* ATP Bulk **/
        Stream.of(SObject.ACCOUNT_TERRITORY_PRODUCT,
                SObject.PRODUCT,
                SObject.TERRITORY_PRODUCT_EXCLUSION).forEach(object -> metadataPermissionSetUtils.configureCrudObjectPermissionsForPermissionSet(ATP_BULK,
                object, true, true, true, true));

        Stream.of(AccountTerritoryProductField.TERRITORY,
                AccountTerritoryProductField.ATP_TEXT1,
                AccountTerritoryProductField.ATP_TEXT2,
                AccountTerritoryProductField.ATP_TEXT3,
                AccountTerritoryProductField.ATP_TEXT4,
                AccountTerritoryProductField.ATP_TEXT5,
                AccountTerritoryProductField.ATP_TEXT6).forEach(field -> metadataPermissionSetUtils.updateFieldPermissionSet(ATP_BULK,
                SObject.ACCOUNT_TERRITORY_PRODUCT, field, true, true));

        metadataPermissionSetUtils.updateFieldPermissionSet(ATP_BULK,
                SObject.PRODUCT, ProductField.ACTIVE, true, true);
        metadataPermissionSetUtils.configureAssignmentRecordTypesForPermissionSet(ATP_BULK,
                ProductRecordType.BRAND,
                ProductRecordType.DETAIL,
                ProductRecordType.SAMPLE,
                ProductRecordType.ITEM,
                ProductRecordType.HIERARCHY);

        Stream.of(TerritoryProductExclusion.PRODUCT,
                TerritoryProductExclusion.TERRITORY).forEach(field -> metadataPermissionSetUtils.updateFieldPermissionSet(ATP_BULK,
                SObject.TERRITORY_PRODUCT_EXCLUSION, field, true, true));

        bulkPermissionSets.forEach(permissionSet ->
                permissionSetConfigurationProvider.assignPermissionSetToUser(FIRST_SALES_REP_USER, permissionSet));
        accountFilterConfigurationProvider.activateAccountDynamicListBulkConfiguration(FIRST_SALES_REP_USER);

        metadataCustomUtils.updateDBSchemaPermissionProfiles(SObject.ACCOUNT_TERRITORY_FIELDS, singletonList(ATF_BULK));
        metadataCustomUtils.updateDBSchemaPermissionProfiles(SObject.ACCOUNT_TERRITORY_PRODUCT, singletonList(ATP_BULK));
    }

}
