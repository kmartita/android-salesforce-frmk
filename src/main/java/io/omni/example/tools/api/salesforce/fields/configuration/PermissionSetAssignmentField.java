package io.omni.example.tools.api.salesforce.fields.configuration;

import io.omni.example.tools.api.HasName;

public enum PermissionSetAssignmentField implements HasName {

    ASSIGNEE_ID("AssigneeId"),
    PERMISSION_SET_ID("PermissionSetId");

    private final String name;

    PermissionSetAssignmentField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
