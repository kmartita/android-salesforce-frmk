package io.omni.example.tools.api.salesforce.data.providers;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.SalesforceApiUtils;
import io.omni.example.tools.api.salesforce.data.SalesforceData;
import io.omni.example.tools.api.salesforce.data.hierarchies.account.AccountHierarchy;
import io.omni.example.tools.api.salesforce.data.hierarchies.accountfilter.AccountListHierarchy;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.fields.AccountFilterField;
import io.omni.example.tools.api.salesforce.fields.AccountListItemField;
import io.omni.example.tools.api.salesforce.recordtypes.AccountFilterRecordType;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.omni.example.tools.api.salesforce.sobject.SObject.ACCOUNT_FILTER;
import static io.omni.example.tools.api.salesforce.sobject.SObject.ACCOUNT_LIST_ITEM;

public class AccountFilterProvider {

    private GenericModel<AccountFilterField> generateAccountFilterModelWithName(AccountFilterRecordType recordType) {
        return GenericModel.builder(AccountFilterField.class)
                .setField(AccountFilterField.NAME, AccountFilterField.NAME.generate())
                .setField(AccountFilterField.RECORD_TYPE, recordType.getRecordTypeId())
                .build();
    }

    @Step
    public AccountListHierarchy createStaticListWithAccounts(SfUserModel user, List<AccountHierarchy> accounts) {
        SalesforceApiUtils salesforceApiUtils = new SalesforceApiUtils(user);
        List<SalesforceData<AccountListItemField>> accountListItems = new ArrayList<>();

        GenericModel<AccountFilterField> listModel = generateAccountFilterModelWithName(AccountFilterRecordType.LIST);
        SalesforceData<AccountFilterField> list = salesforceApiUtils.createSObject(ACCOUNT_FILTER, listModel);

        List<String> accountIds = accounts
                .stream()
                .map(account -> account.getAccountData().getId())
                .collect(Collectors.toList());

        accountIds.forEach(id -> {
            GenericModel<AccountListItemField> accountListItemModel = GenericModel.builder(AccountListItemField.class)
                    .setField(AccountListItemField.ACCOUNT_FILTER, list.getId())
                    .setField(AccountListItemField.CUSTOMER, id)
                    .build();

            SalesforceData<AccountListItemField> accountListItem = salesforceApiUtils.createSObject(ACCOUNT_LIST_ITEM, accountListItemModel);
            accountListItems.add(accountListItem);
        });

        return new AccountListHierarchy(list, accountListItems);
    }
}
