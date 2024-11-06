package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.properties.PropertyManager;

import java.io.IOException;
import java.util.Objects;

import static java.lang.String.format;

public class ProfileRequests extends BaseRequests {

    public ProfileRequests() {
        super(PropertyManager.getAdminUser());
    }

    public String getProfileId(String name) {
        try {
            String sql = new SelectBuilder()
                    .column(DefaultField.ID.getName())
                    .from(SObject.PROFILE.getName())
                    .where(format("%s = '%s'", DefaultField.NAME.getName(), name))
                    .toString();

            return Objects.requireNonNull(Objects.requireNonNull(getSOQLService()
                            .profileRequest(sql)
                            .execute()
                            .body())
                    .getRecords()
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(format("The '%s' record where <%s = '%s'> not found.",
                            SObject.PROFILE.getName(),
                            DefaultField.NAME.getName(),
                            name)))
                    .getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
