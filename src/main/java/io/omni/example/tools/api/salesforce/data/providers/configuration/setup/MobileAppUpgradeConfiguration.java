package io.omni.example.tools.api.salesforce.data.providers.configuration.setup;

import io.omni.example.tools.api.salesforce.SalesforceApiUtils;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.fields.MobileAppUpgradeField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.models.MobileAppUpgradeModel;
import io.omni.example.tools.api.salesforce.sobject.requests.MobileAppUpgradeRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MobileAppUpgradeConfiguration {

    public static void updateMobileAppReleaseDate() {
        try {
            updateMobileAppReleaseDateToNextYear();
        } catch (Exception e) {
            System.out.println("Error during updating Mobile App Release Date, trying again." + e.getMessage());
            updateMobileAppReleaseDateToNextYear();
        }
    }

    private static void updateMobileAppReleaseDateToNextYear() {
        List<MobileAppUpgradeModel> models = new MobileAppUpgradeRequest().getMobileAppUpgradeModels();

        if (!models.isEmpty()) {
            List<String> ids = models.stream()
                    .filter(record ->
                            record.getReleaseDate().getYear() == LocalDate.now().getYear()
                                    ||
                                    record.getReleaseDate().getYear() == LocalDate.now().minusYears(1).getYear())
                    .map(MobileAppUpgradeModel::getId)
                    .collect(Collectors.toList());
            GenericModel<MobileAppUpgradeField> modelForUpdate = GenericModel.builder(MobileAppUpgradeField.class)
                    .setField(MobileAppUpgradeField.RELEASE_DATE, LocalDate.now().plusYears(1))
                    .build();

            SalesforceApiUtils salesforceApiUtils = new SalesforceApiUtils();
            ids.forEach(id -> salesforceApiUtils.updateSObject(SObject.MOBILE_APP_UPGRADE, id, modelForUpdate));
        }
    }
}
