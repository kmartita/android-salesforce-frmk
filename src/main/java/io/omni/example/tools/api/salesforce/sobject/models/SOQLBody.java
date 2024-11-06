package io.omni.example.tools.api.salesforce.sobject.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SOQLBody<T> {

    @SerializedName("totalSize")
    private int size;

    @SerializedName("records")
    private List<T> records;

    public int getSize() {
        return size;
    }

    public List<T> getRecords() {
        return records;
    }
}
