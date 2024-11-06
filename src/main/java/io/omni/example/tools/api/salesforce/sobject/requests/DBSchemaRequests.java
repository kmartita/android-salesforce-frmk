package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.fields.configuration.DbSchemaField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.models.DBSchemaModel;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static io.omni.example.tools.properties.PropertyManager.getAdminUser;

public class DBSchemaRequests extends BaseRequests {

    public DBSchemaRequests() {
        super(getAdminUser());
    }

    public List<DBSchemaModel> getDbSchemas() {
        try {
            String sql = new SelectBuilder()
                    .column(DefaultField.NAMESPACE_PREFIX.getName())
                    .column(DefaultField.DEVELOPER_NAME.getName())
                    .column(DefaultField.LABEL.getName())
                    .column(DbSchemaField.IS_ACTIVE.getName())
                    .column(DbSchemaField.PERMITTED_PROFILES.getName())
                    .column(DbSchemaField.S_OBJECT.getName())
                    .from(SObject.DB_SCHEMA.getName())
                    .toString();
            return Objects.requireNonNull(Objects.requireNonNull(getSOQLService().performDBSchemaRequest(sql)
                            .execute().body())
                    .getRecords());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Get DB schema by '{0}' sobject.")
    public DBSchemaModel getDbSchemaBySObject(SObject sObject) {
        return getDbSchemas()
                .stream()
                .filter(dbSchema -> sObject.getName().equalsIgnoreCase(dbSchema.getSObject()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("There is no DB schema with '%s' sobject.", sObject.getName())));
    }
}
