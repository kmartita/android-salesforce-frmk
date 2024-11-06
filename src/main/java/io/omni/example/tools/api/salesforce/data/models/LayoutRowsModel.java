package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LayoutRowsModel {

    @SerializedName("layoutItems")
    private List<LayoutItemsModel> layoutItems;

    public List<LayoutItemsModel> getLayoutItems(){ return layoutItems; }
}
