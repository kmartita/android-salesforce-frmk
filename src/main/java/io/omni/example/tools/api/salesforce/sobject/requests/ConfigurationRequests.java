package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.models.ConfigurationModel;
import io.omni.example.tools.properties.PropertyManager;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ConfigurationRequests extends BaseRequests {

    public ConfigurationRequests() {
        super(PropertyManager.getAdminUser());
    }

    public List<ConfigurationModel> getConfiguration(SObject sObject) {
        try {
            String sql = new SelectBuilder()
                    .column(DefaultField.ID.getName())
                    .column(DefaultField.SETUP_OWNER_ID.getName())
                    .from(sObject.getName())
                    .toString();

            return Objects.requireNonNull(Objects.requireNonNull(getSOQLService()
                            .configurationRequest(sql)
                            .execute()
                            .body())
                    .getRecords());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
