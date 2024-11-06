package io.omni.example.tools.api.salesforce.data.requests;

import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.properties.PropertyManager;
import io.omni.example.tools.api.salesforce.data.models.PicklistValuesModel;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class PicklistRequests extends BaseRequests {

    //all requests from salesforce are performed by admin user
    public PicklistRequests() {
        super(PropertyManager.getAdminUser());
    }

    public <Field extends Enum<Field> & HasName> List<String> getFieldPicklistValues(SObject sObject, Field field) {
        try {
            List<String> list =
                    Objects.requireNonNull(getSalesforceService()
                                    .objectDescribe(sObject.getName())
                                    .execute().body())
                            .getFields().stream()
                            .filter(i -> i.getName().equals(field.getName()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException(format("Field '%s' for sObject '%s' not found.", field, sObject)))
                            .getPicklistValues()
                            .stream()
                            .map(PicklistValuesModel::getValue)
                            .collect(Collectors.toList());
            Collections.shuffle(list);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
