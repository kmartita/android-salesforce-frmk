package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.models.PermissionSetModel;
import io.omni.example.tools.properties.PropertyManager;

import java.io.IOException;
import java.util.Objects;

import static java.lang.String.format;

public class PermissionSetRequest extends BaseRequests {

    private static final String EXCEPTION_MESSAGE = "The '%s' record where <%s = '%s'> not found.";
    public PermissionSetRequest() {
        super(PropertyManager.getAdminUser());
    }

    public String getPermissionSetIdByName(String name) {
        String sql = new SelectBuilder()
                .column(DefaultField.ID.getName())
                .from(SObject.PERMISSION_SET.getName())
                .where(format("%s = '%s'", DefaultField.NAME.getName(), name))
                .toString();

        return getFirstPermissionSetModel(sql, format(EXCEPTION_MESSAGE, SObject.PERMISSION_SET.getName(),
                DefaultField.NAME.getName(), name)).getId();
    }

    private PermissionSetModel getFirstPermissionSetModel(String sql, String exceptionMsg) {
        try {
            return Objects.requireNonNull(Objects.requireNonNull(getSOQLService()
                            .permissionSetRequest(sql)
                            .execute()
                            .body())
                    .getRecords()
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(exceptionMsg)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
