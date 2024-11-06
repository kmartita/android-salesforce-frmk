package io.omni.example.tools.api.salesforce.data.providers;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.SalesforceApiUtils;
import io.omni.example.tools.api.salesforce.data.SalesforceData;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModelBuilder;
import io.omni.example.tools.api.salesforce.fields.AccountAddressField;
import io.omni.example.tools.api.salesforce.recordtypes.AccountRecordType;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.properties.PropertyManager;

import java.util.EnumSet;
import java.util.Set;

public class AccountAddressProvider {

    private Set<AccountAddressField> getAccountAddressFields() {
        return EnumSet.of(AccountAddressField.WEB_ADDRESS,
                AccountAddressField.MAIL_RESTRICTION,
                AccountAddressField.FAX_RESTRICTION,
                AccountAddressField.VISIT_RESTRICTION,
                AccountAddressField.CALL_RESTRICTION,
                AccountAddressField.EMAIL_RESTRICTION,
                AccountAddressField.PHONE,
                AccountAddressField.FAX__C,
                AccountAddressField.PHONE2,
                AccountAddressField.EMAIL,
                AccountAddressField.IS_PRIMARY,
                AccountAddressField.LICENSE_EXPIRATION_DATE,
                AccountAddressField.LICENSE_NUMBER,
                AccountAddressField.IS_COPY_TDDD_DATA);
    }

    private GenericModel<AccountAddressField> generateModelByFields(String accountId, String addressId) {
        GenericModelBuilder<AccountAddressField> builder = GenericModel.builder(AccountAddressField.class);
        Set<AccountAddressField> fields = getAccountAddressFields();

        for (AccountAddressField field : fields) {
            if (!field.equals(AccountAddressField.ACCOUNT) && !field.equals(AccountAddressField.ADDRESS)) {
                builder.setField(field, field.generate());
            }
        }
        builder.setField(AccountAddressField.ACCOUNT, accountId);
        builder.setField(AccountAddressField.ADDRESS, addressId);
        builder.setField(AccountAddressField.ADDRESS_TYPE,"Billing;Shipping");
        return builder.build();
    }

    public SalesforceData<AccountAddressField> createAccountAddress(AccountRecordType recordType, String accountId, String addressId){
        SfUserModel admin = PropertyManager.getAdminUser();
        GenericModel<AccountAddressField> model = generateModelByFields(accountId, addressId);

        switch (recordType) {
            case BC:
            case MP:
                break;
            case INSTITUTION:
            case IDN:
            case GPO:
            case DEPARTMENT:
            case PHARMACY:
            case PRACTICE:
            case WHOLESALER:
            default:
                model = model.removeFields(AccountAddressField.LICENSE_EXPIRATION_DATE, AccountAddressField.LICENSE_NUMBER);
        }

        return new SalesforceApiUtils(admin).createSObject(SObject.ACCOUNT_ADDRESS, model);
    }

}
