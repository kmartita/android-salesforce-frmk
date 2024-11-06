package io.omni.example.activation;

import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.providers.configuration.ApplicationSettingsProvider;
import io.omni.example.tools.api.salesforce.data.providers.configuration.ConfigurationProvider;
import io.omni.example.tools.api.salesforce.fields.configuration.ApplicationSettingsField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.requests.OrganizationRequests;

import static io.omni.example.tools.api.salesforce.data.providers.configuration.setup.UserProfiles.SALES_REPRESENTATIVE;

public class ApplicationActivation {

    private final ConfigurationProvider configurationProvider = new ConfigurationProvider();
    private final ApplicationSettingsProvider applicationSettingsProvider = new ApplicationSettingsProvider();

    public void configureOrganizationApplicationSettings(){
        GenericModel<ApplicationSettingsField> model = applicationSettingsProvider.generateDefaultApplicationSettingsModel();
        String organizationId = new OrganizationRequests().getOrganizationId();
        configurationProvider.activateConfigurationForOwnerId(SObject.APPLICATION_SETTINGS, organizationId, model);
    }

    public void configureApplicationSettingsForSalesRepProfile(){
        GenericModel<ApplicationSettingsField> model = applicationSettingsProvider.generateDefaultApplicationSettingsModel();
        applicationSettingsProvider.activateApplicationSettingsForProfile(SALES_REPRESENTATIVE, model);
    }
}
