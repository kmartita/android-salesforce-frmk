package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.properties.PropertyManager;

import java.io.IOException;
import java.util.Objects;

import static java.lang.String.format;

public class OrganizationRequests extends BaseRequests {

    public OrganizationRequests() {
        super(PropertyManager.getAdminUser());
    }

    public String getOrganizationId() {
        try {
            String sql = new SelectBuilder()
                    .column(DefaultField.ID.getName())
                    .from(SObject.ORGANIZATION.getName())
                    .toString();

            return Objects.requireNonNull(Objects.requireNonNull(getSOQLService()
                            .organizationRequest(sql)
                            .execute()
                            .body())
                    .getRecords()
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(format("The id for '%s' not found.",
                            SObject.ORGANIZATION.getName())))
                    .getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
