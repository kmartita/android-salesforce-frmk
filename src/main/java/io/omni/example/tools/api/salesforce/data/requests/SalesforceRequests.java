package io.omni.example.tools.api.salesforce.data.requests;

import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.data.models.*;
import io.omni.example.tools.api.salesforce.fields.FieldType;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.properties.PropertyManager;
import io.qameta.allure.Step;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class SalesforceRequests extends BaseRequests {

    //all requests from salesforce are performed by admin user
    public SalesforceRequests() {
        super(PropertyManager.getAdminUser());
    }

    @Step
    public <Field extends Enum<Field>> Set<Field> getEditRequiredLayoutFieldSet(SObject sObject, String recordId, Function<String, Field> function) {
        try {
            Set<String> fields = new HashSet<>();
            List<LayoutComponentsModel> layoutComponents = Objects.requireNonNull(getSalesforceService()
                            .objectDescribeLayout(sObject.getName(), recordId)
                            .execute()
                            .body())
                    .getEditLayoutSections()
                    .stream()
                    .map(EditLayoutSectionsModel::getLayoutRows)
                    .flatMap(List::stream)
                    .map(LayoutRowsModel::getLayoutItems)
                    .flatMap(List::stream)
                    .filter(LayoutItemsModel::isRequired)
                    .map(LayoutItemsModel::getLayoutComponents)
                    .flatMap(List::stream)
                    .collect(toList());

            for (LayoutComponentsModel layoutComponent : layoutComponents) {
                if (layoutComponent.hasComponents()) {
                    fields.addAll(layoutComponent
                            .getComponents()
                            .stream()
                            .map(ComponentsModel::getValue)
                            .collect(toList()));
                } else {
                    fields.add(layoutComponent.getValue());
                }
            }

            return fields
                    .stream()
                    .map(function)
                    .collect(toSet());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Step
    public <Field extends Enum<Field> & HasName> FieldType getFieldType(SObject sObject, Field field) {
        try {
            String type = Objects.requireNonNull(getSalesforceService()
                            .objectDescribe(sObject.getName())
                            .execute().body())
                    .getFields().stream()
                    .filter(i -> i.getName().equals(field.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(format("Field '%s' for sObject '%s' not found.", field, sObject)))
                    .getType();
            return FieldType.getByName(type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
