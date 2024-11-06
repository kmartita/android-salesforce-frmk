package io.omni.example.tools.api.salesforce.data.providers.configuration;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.SalesforceApiUtils;
import io.omni.example.tools.api.salesforce.data.models.PermissionSetAssignmentModel;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.PermissionSets;
import io.omni.example.tools.api.salesforce.fields.configuration.PermissionSetAssignmentField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.requests.PermissionSetAssignmentRequest;
import io.omni.example.tools.api.salesforce.sobject.requests.PermissionSetRequest;
import io.omni.example.tools.api.salesforce.sobject.requests.UsersRequests;
import io.omni.example.tools.properties.PropertyManager;

import java.util.List;

public class PermissionSetConfigurationProvider {

    public void assignPermissionSetToUser(SfUserModel user, PermissionSets permissionSet) {
        SfUserModel admin = PropertyManager.getAdminUser();
        String userId = new UsersRequests(admin).getUserId(user.getUsername());
        String permissionSetId = new PermissionSetRequest().getPermissionSetIdByName(permissionSet.getName());

        List<PermissionSetAssignmentModel> permissionSetAssignments = new PermissionSetAssignmentRequest()
                .getPermissionSetAssignmentBy(user, permissionSet);

        if (permissionSetAssignments.isEmpty()) {
            GenericModel<PermissionSetAssignmentField> model = GenericModel.builder(PermissionSetAssignmentField.class)
                    .setField(PermissionSetAssignmentField.ASSIGNEE_ID, userId)
                    .setField(PermissionSetAssignmentField.PERMISSION_SET_ID, permissionSetId)
                    .build();

            new SalesforceApiUtils(admin).createSObject(SObject.PERMISSION_SET_ASSIGNMENT, model);
        }
    }

    public void assignPermissionSetToUser(SfUserModel user, List<PermissionSets> permissionSets) {
        permissionSets.forEach(permissionSet -> assignPermissionSetToUser(user, permissionSet));
    }
}
