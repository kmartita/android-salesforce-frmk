package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LayoutComponentsModel {
    @SerializedName("value")
    private String value;

    @SerializedName("components")
    private List<ComponentsModel> components;

    public String getValue(){ return value; }

    public List<ComponentsModel> getComponents(){ return components; }

    public boolean hasComponents() {
        return components != null && !components.isEmpty();
    }
}
