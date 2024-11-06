package io.omni.example.tools.api.salesforce;

public enum SyncTransactionAction {

    INSERT("Insert"),
    UPDATE("Update"),
    DELETE("Delete");

    private final String name;

    SyncTransactionAction(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
