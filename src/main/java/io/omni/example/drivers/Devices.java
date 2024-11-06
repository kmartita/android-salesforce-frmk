package io.omni.example.drivers;

public enum Devices {

    SAMSUNG_GALAXY_TAB_S8("SamGalTabS8", "Samsung Galaxy Tab S8");

    private final String name;
    private final String label;

    Devices(String label, String name) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label;
    }
}
