package io.omni.example.tools.api.salesforce.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectDescribeModel {

    @SerializedName("fields")
    private List<FieldModel> fields;

    @SerializedName("recordTypeInfos")
    private List<RecordTypeInfoModel> recordTypes;

    @SerializedName("editLayoutSections")
    private List<EditLayoutSectionsModel> editLayoutSections;

    public List<RecordTypeInfoModel> getRecordTypes() {
        return recordTypes;
    }

    public List<FieldModel> getFields(){
        return fields;
    }

    public List<EditLayoutSectionsModel> getEditLayoutSections(){ return editLayoutSections; }
}
