package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.data.models.PermissionSetAssignmentModel;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.PermissionSets;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.fields.configuration.PermissionSetAssignmentField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.properties.PropertyManager;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

public class PermissionSetAssignmentRequest extends BaseRequests {

    public PermissionSetAssignmentRequest() {
        super(PropertyManager.getAdminUser());
    }

    public List<PermissionSetAssignmentModel> getPermissionSetAssignmentBy(SfUserModel user,
                                                                           PermissionSets permissionSet) {

        SfUserModel admin = PropertyManager.getAdminUser();
        String userId = new UsersRequests(admin).getUserId(user.getUsername());
        String permissionSetId = new PermissionSetRequest().getPermissionSetIdByName(permissionSet.getName());

        try {
            String sql = new SelectBuilder()
                    .column(DefaultField.ID.getName())
                    .from(SObject.PERMISSION_SET_ASSIGNMENT.getName())
                    .where(format("%s = '%s'", PermissionSetAssignmentField.ASSIGNEE_ID.getName(), userId))
                    .and(format("%s = '%s'", PermissionSetAssignmentField.PERMISSION_SET_ID.getName(), permissionSetId))
                    .toString();

            return Objects.requireNonNull(Objects.requireNonNull(getSOQLService()
                            .permissionSetAssignmentRequest(sql)
                            .execute()
                            .body())
                    .getRecords());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
