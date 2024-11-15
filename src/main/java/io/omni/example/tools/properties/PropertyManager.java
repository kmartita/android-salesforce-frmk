package io.omni.example.tools.properties;

import io.omni.example.models.SfUserModel;

import static io.omni.example.tools.properties.PropertyFileHelper.getEnvFile;
import static io.omni.example.tools.properties.PropertyFileHelper.readFromPropertyFile;

public class PropertyManager {

    public static final String LOCAL = "local.properties";
    public static final String REGRESSION = "regression.properties";
    public static final String REGRESSION1 = "regression1.properties";

    public static SfUserModel getAdminUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "lviv_adminusername"),
                readFromPropertyFile(getEnvFile(), "lviv_adminpassword"));
    }

   public static SfUserModel getFirstSalesRepUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "sr1_username"),
                readFromPropertyFile(getEnvFile(), "sr1_password"));
    }

    public static String getTerritoryForFirstSalesRep() {
        return readFromPropertyFile(getEnvFile(), "territory_1_sr1");
    }
}
