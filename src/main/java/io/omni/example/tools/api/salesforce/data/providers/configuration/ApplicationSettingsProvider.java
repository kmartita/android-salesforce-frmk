package io.omni.example.tools.api.salesforce.data.providers.configuration;

import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.UserProfiles;
import io.omni.example.tools.api.salesforce.fields.configuration.ApplicationSettingsField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.qameta.allure.Step;

public class ApplicationSettingsProvider extends ConfigurationProvider {

    public GenericModel<ApplicationSettingsField> generateDefaultApplicationSettingsModel() {
        return GenericModel.builder(ApplicationSettingsField.class)
                .setField(ApplicationSettingsField.ENABLE_ADVANCED_SEARCH, true)
                .setField(ApplicationSettingsField.STOP_UPDATING_GENERIC_WORKFLOW_METADATA, true)
                .setField(ApplicationSettingsField.USE_SOBJECTS_FOR_GENERIC_WORKFLOW, true)
                .setField(ApplicationSettingsField.ENABLE_PERMISSION_SETS_FOR_DB_SCHEMA, true)
                .build();
    }

    @Step
    public void activateApplicationSettingsForProfile(UserProfiles profile,
                                                      GenericModel<ApplicationSettingsField> model) {
        activateConfigurationForProfile(profile, SObject.APPLICATION_SETTINGS, model);
    }
}
