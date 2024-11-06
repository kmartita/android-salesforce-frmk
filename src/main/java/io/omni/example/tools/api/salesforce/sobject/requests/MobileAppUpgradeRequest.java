package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.fields.MobileAppUpgradeField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.api.salesforce.sobject.models.MobileAppUpgradeModel;

import java.io.IOException;
import java.util.List;

import static io.omni.example.tools.properties.PropertyManager.getAdminUser;

public class MobileAppUpgradeRequest extends BaseRequests {

    public MobileAppUpgradeRequest() {
        super(getAdminUser());
    }

    public List<MobileAppUpgradeModel> getMobileAppUpgradeModels() {
        try {
            String sql = new SelectBuilder()
                    .column(DefaultField.ID.getName())
                    .column(MobileAppUpgradeField.RELEASE_DATE.getName())
                    .from(SObject.MOBILE_APP_UPGRADE.getName())
                    .toString();
            return getSOQLService().performMobileAppUpgradeRequest(sql)
                    .execute().body().getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
