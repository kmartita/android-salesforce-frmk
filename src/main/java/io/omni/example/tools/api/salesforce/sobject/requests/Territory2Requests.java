package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.models.Territory2Model;
import io.omni.example.tools.properties.PropertyManager;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

public class Territory2Requests extends BaseRequests {

    public Territory2Requests() {
        super(PropertyManager.getAdminUser());
    }

    private List<Territory2Model> getTerritory2Models() {
        try {
            String sql = new SelectBuilder()
                    .column(DefaultField.DEVELOPER_NAME.getName())
                    .column(DefaultField.NAME.getName())
                    .column(DefaultField.ID.getName())
                    .from(SObject.TERRITORY2.getName())
                    .toString();
            return Objects.requireNonNull(getSOQLService().territory2Request(sql)
                            .execute().body())
                    .getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Get Territory2 name by '{0}' id.")
    public String getTerritory2Name(String id) {
        return getTerritory2Models()
                .stream()
                .filter(record -> id.equalsIgnoreCase(record.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(format("The presentation by id <%s> not found.", id)))
                .getName();
    }
}
