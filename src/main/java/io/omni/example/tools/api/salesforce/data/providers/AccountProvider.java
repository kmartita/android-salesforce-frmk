package io.omni.example.tools.api.salesforce.data.providers;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.SalesforceApiUtils;
import io.omni.example.tools.api.salesforce.data.SalesforceData;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.requests.SalesforceRequests;
import io.omni.example.tools.api.salesforce.fields.AccountField;
import io.omni.example.tools.api.salesforce.recordtypes.AccountRecordType;
import io.omni.example.tools.api.salesforce.sobject.SObject;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AccountProvider {

    private static final String DELIMITER = ";";

    private GenericModel<AccountField> generateModelByRequiredAndMandatoryFields(AccountRecordType recordType,
                                                                                 List<String> territories) {
        String recordTypeId = recordType.getRecordTypeId();
        String territoriesAsString = String.join(DELIMITER, territories);

        Set<AccountField> requiredFields = new SalesforceRequests()
                .getEditRequiredLayoutFieldSet(SObject.ACCOUNT, recordTypeId, AccountField::getByName);
        requiredFields.removeAll(Set.of(AccountField.MIDDLE_NAME, AccountField.SUFFIX));

        return GenericModel.preGenerate(requiredFields)
                .setField(AccountField.RECORD_TYPE_ID, recordTypeId)
                .setField(AccountField.TERRITORY, territoriesAsString)
                .setField(AccountField.TERRITORY_MANUAL, territoriesAsString)
                .build();
    }

    private GenericModel<AccountField> generateModelByRequiredAndMandatoryFields(AccountRecordType recordType,
                                                                                 String territory) {
        return generateModelByRequiredAndMandatoryFields(recordType, Collections.singletonList(territory));
    }

    public SalesforceData<AccountField> createAccount(SfUserModel user, AccountRecordType recordType, String territory) {
        GenericModel<AccountField> model = generateModelByRequiredAndMandatoryFields(recordType, territory);
        return new SalesforceApiUtils(user).createSObject(SObject.ACCOUNT, model);
    }
}
