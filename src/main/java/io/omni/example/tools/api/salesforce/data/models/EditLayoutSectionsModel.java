package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditLayoutSectionsModel {

    @SerializedName("layoutRows")
    private List<LayoutRowsModel> layoutRows;

    public List<LayoutRowsModel> getLayoutRows(){ return layoutRows; }
}
