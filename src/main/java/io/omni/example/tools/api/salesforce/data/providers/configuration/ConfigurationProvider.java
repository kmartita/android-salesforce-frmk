package io.omni.example.tools.api.salesforce.data.providers.configuration;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.SalesforceApiUtils;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.providers.configuration.setup.UserProfiles;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.models.ConfigurationModel;
import io.omni.example.tools.api.salesforce.sobject.requests.ConfigurationRequests;
import io.omni.example.tools.api.salesforce.sobject.requests.ProfileRequests;
import io.omni.example.tools.api.salesforce.sobject.requests.UsersRequests;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static io.omni.example.tools.properties.PropertyManager.getAdminUser;

public class ConfigurationProvider {

    protected static final SfUserModel admin = getAdminUser();

    @Step
    public <Field extends Enum<Field> & HasName> void activateConfigurationForOwnerId(SObject object,
                                                                                      String ownerId,
                                                                                      GenericModel<Field> model) {
        String configurationId;
        List<ConfigurationModel> configurations = new ConfigurationRequests()
                .getConfiguration(object)
                .stream()
                .filter(config -> ownerId.equals(config.getSetupOwnerId()))
                .collect(Collectors.toList());

        if (configurations.isEmpty()) {
            GenericModel<DefaultField> configOwnerModel = GenericModel.builder(DefaultField.class)
                    .setField(DefaultField.SETUP_OWNER_ID, ownerId)
                    .build();
            configurationId = new SalesforceApiUtils(admin)
                    .createSObject(object, configOwnerModel)
                    .getId();
        } else {
            configurationId = configurations
                    .stream()
                    .findFirst()
                    .get()
                    .getId();
        }
        new SalesforceApiUtils(admin).updateSObject(object, configurationId, model);
    }

    @Step
    public <Field extends Enum<Field> & HasName> void activateConfigurationForUser(SfUserModel user,
                                                                                   SObject object,
                                                                                   GenericModel<Field> model) {
        String userId = new UsersRequests(admin).getUserId(user.getUsername());
        activateConfigurationForOwnerId(object, userId, model);
    }

    @Step
    public <Field extends Enum<Field> & HasName> void activateConfigurationForProfile(UserProfiles profile,
                                                                                      SObject object,
                                                                                      GenericModel<Field> model) {
        String profileId = new ProfileRequests().getProfileId(profile.getName());
        activateConfigurationForOwnerId(object, profileId, model);
    }
}
