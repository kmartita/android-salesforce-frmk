package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class ResponseContentApiModel<T> {

    @SerializedName("salesforce_objects")
    private List<T> salesforceObjects;

    public List<T> getSalesforceObjects() {
        return salesforceObjects;
    }

    public T getFirstObject() {
        return Objects.requireNonNull(salesforceObjects).get(0);
    }

}
