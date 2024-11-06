package io.omni.example.tools.metadata;

import com.sforce.soap.metadata.*;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.IsRecordType;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.PermissionSets;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.omni.example.tools.metadata.MetadataUtils.MetadataTypes.PERMISSION_SET;

public class MetadataPermissionSetUtils extends MetadataUtils {

    @Step
    private Metadata[] readPermissionSetMetadataByName(PermissionSets permissionSets) {
        return readMetadataRecords(PERMISSION_SET, permissionSets.getMetadataName());
    }

    @Step
    public void createNewPermissionSetByName(PermissionSets permissionSets) {
        Metadata[] permissionSetRecords = readPermissionSetMetadataByName(permissionSets);
        if (permissionSetRecords.length == 0 || !(permissionSetRecords[0] instanceof PermissionSet)) {
            PermissionSet permissionSet = new PermissionSet();
            permissionSet.setFullName(permissionSets.getName());
            permissionSet.setLabel(permissionSets.getName());
            upsertMetadata(permissionSet);
        }
    }

    @Step
    public void configureAllObjectPermissionsForPermissionSet(PermissionSets permissionSets,
                                                              SObject object,
                                                              boolean allowRead, boolean allowCreate, boolean allowEdit,
                                                              boolean allowDelete, boolean modifyAll, boolean viewAll) {
        PermissionSet permissionSet = (PermissionSet) readPermissionSetMetadataByName(permissionSets)[0];

        PermissionSetObjectPermissions objectPermission = new PermissionSetObjectPermissions();
        objectPermission.setObject(object.getName());
        objectPermission.setAllowCreate(allowCreate);
        objectPermission.setAllowDelete(allowDelete);
        objectPermission.setAllowEdit(allowEdit);
        objectPermission.setAllowRead(allowRead);
        objectPermission.setModifyAllRecords(modifyAll);
        objectPermission.setViewAllRecords(viewAll);

        List<PermissionSetObjectPermissions> objectPermissions = new ArrayList<>(Arrays.asList(permissionSet.getObjectPermissions()));
        objectPermissions.add(objectPermission);
        permissionSet.setObjectPermissions(objectPermissions.toArray(new PermissionSetObjectPermissions[0]));
        updateMetadata(permissionSet);
    }

    @Step
    public void configureCrudObjectPermissionsForPermissionSet(PermissionSets permissionSets,
                                                               SObject object,
                                                               boolean allowRead, boolean allowCreate, boolean allowEdit,
                                                               boolean allowDelete) {
        configureAllObjectPermissionsForPermissionSet(permissionSets, object, allowRead, allowCreate, allowEdit,
                allowDelete, false, false);
    }

    @SafeVarargs
    @Step
    public final <RT extends Enum<RT> & IsRecordType> void configureAssignmentRecordTypesForPermissionSet(PermissionSets permissionSets,
                                                                                                          RT... recordTypes) {
        PermissionSet permissionSet = (PermissionSet) readPermissionSetMetadataByName(permissionSets)[0];
        List<PermissionSetRecordTypeVisibility> permissionSetRecordTypeVisibilities = new ArrayList<>(Arrays.asList(permissionSet.getRecordTypeVisibilities()));
        for (RT recordType : recordTypes) {
            PermissionSetRecordTypeVisibility permissionSetRecordTypeVisibility = new PermissionSetRecordTypeVisibility();
            permissionSetRecordTypeVisibility.setRecordType(recordType.getMetadataName());
            permissionSetRecordTypeVisibility.setVisible(true);

            permissionSetRecordTypeVisibilities.add(permissionSetRecordTypeVisibility);
        }
        permissionSet.setRecordTypeVisibilities(permissionSetRecordTypeVisibilities.toArray(new PermissionSetRecordTypeVisibility[0]));
        updateMetadata(permissionSet);
    }

    @Step
    public <Field extends HasName> void updateFieldPermissionSet(PermissionSets permissionSets,
                                                                 SObject sObject,
                                                                 Field field,
                                                                 boolean allowRead, boolean allowEdit) {
        if (allowEdit && !allowRead) {
            throw new RuntimeException("You can't have edit set to 'true' and read set to 'false' in same field permission");
        }

        PermissionSet permissionSet = (PermissionSet) readPermissionSetMetadataByName(permissionSets)[0];
        PermissionSetFieldPermissions[] fieldPermission = permissionSet.getFieldPermissions();

        for (int i = 0; i < fieldPermission.length; i++) {
            String fieldLabel = fieldPermission[i].getField();

            if (fieldLabel.equalsIgnoreCase(sObject.getName() + "." + field.getName())) {
                fieldPermission[i].setReadable(allowRead);
                fieldPermission[i].setEditable(allowEdit);
                permissionSet.setFieldPermissions(fieldPermission);
                updateMetadata(permissionSet);
                break;
            }
            if (i == fieldPermission.length - 1) {
                throw new RuntimeException(String.format("Field [%s] not found.", field.getName()));
            }
        }
    }
}
