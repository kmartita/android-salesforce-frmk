package io.omni.example.tools.api.salesforce.data.hierarchies.accountfilter;

import io.omni.example.tools.api.salesforce.data.SalesforceData;
import io.omni.example.tools.api.salesforce.fields.AccountFilterField;
import io.omni.example.tools.api.salesforce.fields.AccountListItemField;

import java.util.List;

public class AccountListHierarchy {

    private final SalesforceData<AccountFilterField> list;
    private List<SalesforceData<AccountListItemField>> listAccountListItem;

    public AccountListHierarchy(SalesforceData<AccountFilterField> list,
                                List<SalesforceData<AccountListItemField>> listAccountListItem) {
        this.list = list;
        this.listAccountListItem = listAccountListItem;
    }

    public SalesforceData<AccountFilterField> getList() {
        return list;
    }

    public List<SalesforceData<AccountListItemField>> getListAccountListItem() {
        return listAccountListItem;
    }

    public String getListName() {
        return getList().getGenericModel().getString(AccountFilterField.NAME);
    }
}
