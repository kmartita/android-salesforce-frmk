package io.omni.example.tools.properties;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Properties;

import static io.omni.example.tools.properties.PropertyManager.*;

public class PropertyFileHelper {

    public static String readFromPropertyFile(String fileName, String key) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = Files.newInputStream(Paths.get(fileName));
            prop.load(input);
            if (prop.containsKey(key)) return prop.getProperty(key);
            else throw new NoSuchElementException("There is no key [" + key + "] in property file");
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getEnvFile() {
        switch (System.getProperty("env")) {
            case "regression":
                return REGRESSION;
            case "regression1":
                return REGRESSION1;
            case "local":
            default:
                return LOCAL;
        }
    }
}
