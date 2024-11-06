package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LayoutItemsModel {

    @SerializedName("required")
    private Boolean required;

    @SerializedName("layoutComponents")
    private List<LayoutComponentsModel> layoutComponents;

    public Boolean isRequired(){ return required != null && required; }

    public List<LayoutComponentsModel> getLayoutComponents(){ return layoutComponents; }
}
