package io.omni.example.tools.properties;

import io.omni.example.models.SfUserModel;

import static io.omni.example.tools.properties.PropertyFileHelper.getEnvFile;
import static io.omni.example.tools.properties.PropertyFileHelper.readFromPropertyFile;

public class PropertyManager {

    public static final String CREDS_FILE_FOR_OMNI= "creds_for_omni.properties";
    public static final String CREDS_FILE_FOR_REGRESSION= "creds_for_regression.properties";
    public static final String CREDS_FILE_FOR_REGRESSION1 = "creds_for_regression1.properties";

    public static SfUserModel getAdminUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "lviv_adminusername"),
                readFromPropertyFile(getEnvFile(), "lviv_adminpassword"));
    }

    public static SfUserModel getSalesRepUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "sr_username"),
                readFromPropertyFile(getEnvFile(), "sr_password"));
    }

    public static String getFirstTerritoryForSalesRep() {
        return readFromPropertyFile(getEnvFile(), "territory_1_sr");
    }

    public static SfUserModel getFirstSalesRepUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "sr1_username"),
                readFromPropertyFile(getEnvFile(), "sr1_password"));
    }

    public static String getTerritoryForFirstSalesRep() {
        return readFromPropertyFile(getEnvFile(), "territory_1_sr1");
    }

    public static SfUserModel getSecondSalesRepUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "sr2_username"),
                readFromPropertyFile(getEnvFile(), "sr2_password"));
    }

    public static String getTerritoryForSecondSalesRep() {
        return readFromPropertyFile(getEnvFile(), "territory_1_sr2");
    }

    public static String getSecondTerritoryForSecondSalesRep() {
        return readFromPropertyFile(getEnvFile(), "territory_2_sr2");
    }

    public static SfUserModel getThirdSalesRepUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "sr3_username"),
                readFromPropertyFile(getEnvFile(), "sr3_password"));
    }

    public static String getTerritoryForThirdSalesRep() {
        return readFromPropertyFile(getEnvFile(), "territory_1_sr3");
    }

    public static String getSecondTerritoryForThirdSalesRep() {
        return readFromPropertyFile(getEnvFile(), "territory_2_sr3");
    }

    public static SfUserModel getFourthSalesRepUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "sr4_username"),
                readFromPropertyFile(getEnvFile(), "sr4_password"));
    }

    public static String getTerritoryForFourthSalesRep() {
        return readFromPropertyFile(getEnvFile(), "territory_1_sr4");
    }

    public static SfUserModel getPlannerUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "planner_username"),
                readFromPropertyFile(getEnvFile(), "planner_password"));
    }

    public static String getFirstTerritoryForPlanner() {
        return readFromPropertyFile(getEnvFile(), "territory_1_planner");
    }

    public static String getSecondTerritoryForPlanner() {
        return readFromPropertyFile(getEnvFile(), "territory_2_planner");
    }

    public static String getMirrorTerritoryForPlanner() {
        return readFromPropertyFile(getEnvFile(), "territory_mirror_planner");
    }

    public static SfUserModel getMslUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "msl_username"),
                readFromPropertyFile(getEnvFile(), "msl_password"));
    }

    public static String getTerritoryForMsl() {
        return readFromPropertyFile(getEnvFile(), "territory_1_msl");
    }

    public static SfUserModel getKamUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "kam_username"),
                readFromPropertyFile(getEnvFile(), "kan_password"));
    }

    public static String getTerritoryForKam() {
        return readFromPropertyFile(getEnvFile(), "territory_1_kam");
    }

    public static SfUserModel getAdaSalesRepUser() {
        return new SfUserModel(readFromPropertyFile(getEnvFile(), "ada_sr_username"),
                readFromPropertyFile(getEnvFile(), "ada_sr_password"));
    }

    public static String getTerritoryForAdaSalesRep() {
        return readFromPropertyFile(getEnvFile(), "territory_1_ada_sr");
    }
}
