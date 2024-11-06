package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.sobject.models.ProductModel;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.sobject.SObject;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static io.omni.example.tools.properties.PropertyManager.getAdminUser;

public class ProductRequests extends BaseRequests {

    public ProductRequests() { super(getAdminUser()); }

    public List<String> getProductNames() {
        try {
            String sql = new SelectBuilder()
                    .column(DefaultField.NAME.getName())
                    .from(SObject.PRODUCT.getName())
                    .toString();

            List<String> list = Objects.requireNonNull(getSOQLService()
                            .productQuery(sql)
                            .execute()
                            .body())
                    .getRecords()
                    .stream()
                    .map(ProductModel::getName)
                    .collect(Collectors.toList());
            Collections.shuffle(list);

            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
