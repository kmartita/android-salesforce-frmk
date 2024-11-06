package io.omni.example.tools.api.salesforce.data.providers;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.SalesforceApiUtils;
import io.omni.example.tools.api.salesforce.data.SalesforceData;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.fields.AddressField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.properties.PropertyManager;

import java.util.EnumSet;
import java.util.Set;

public class AddressProvider {

    private Set<AddressField> getAddressFields() {
        return EnumSet.of(AddressField.ADDRESS_NAME,
                AddressField.CITY,
                AddressField.ADDRESS_LINE_2,
                AddressField.COUNTRY,
                AddressField.COUNTRY_CODE,
                AddressField.STATE,
                AddressField.STATE_CODE,
                AddressField.ZIP_CODE,
                AddressField.IS_VALIDATED,
                AddressField.TDDD_LICENSE_EXPIRATION_DATE,
                AddressField.TDDD_LICENSE_NUMBER,
                AddressField.TDDD_LICENSE_EXEMPTION,
                AddressField.TDDD_CATEGORY,
                AddressField.TDDD_SUBCATEGORY,
                AddressField.TDDD_LICENSE_STATUS);
    }

    private GenericModel<AddressField> generateDefaultModelByFields(Set<AddressField> fields) {
        return GenericModel.preGenerate(fields)
                .setField(AddressField.STATE, "Illinois")
                .setField(AddressField.STATE_CODE, "IL")
                .build();
    }

    public SalesforceData<AddressField> createDefaultAddress() {
        SfUserModel admin = PropertyManager.getAdminUser();
        GenericModel<AddressField> model = generateDefaultModelByFields(getAddressFields());
        return new SalesforceApiUtils(admin).createSObject(SObject.ADDRESS, model);
    }
}
