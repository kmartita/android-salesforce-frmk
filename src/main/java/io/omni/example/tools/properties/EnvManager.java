package io.omni.example.tools.properties;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvManager {

    private static final Dotenv ENV = Dotenv.configure().ignoreIfMissing().load();

    public static String BASE_URL = getEnvOption("BASE_URL");
    public static final String CLIENT_ID = getEnvOption("CLIENT_ID");
    public static final String CLIENT_SECRET = getEnvOption("CLIENT_SECRET");
    public static final String REDIRECTED_URL = getEnvOption("REDIRECTED_URL");
    public static final String CONTENT_API_URL = getEnvOption("CONTENT_API_URL");
    public static final String APP_BUNDLE_ID = getEnvOption("APP_BUNDLE_ID");
    public static final String APP_NAME = getEnvOption("APP_NAME");
    public static final String ANDROID_URL = getEnvOption("ANDROID_URL");

    private EnvManager() {

    }

    private static String getEnvOption(String option) {
        String value = ENV.get(option);
        return (value != null) ? value : System.getenv(option);
    }
}
