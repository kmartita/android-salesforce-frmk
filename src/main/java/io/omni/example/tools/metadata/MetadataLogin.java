package io.omni.example.tools.metadata;

import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import io.omni.example.models.SfUserModel;
import io.omni.example.tools.properties.EnvManager;
import io.omni.example.tools.properties.PropertyManager;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.LoginResult;

public class MetadataLogin {

    public static String URL = EnvManager.BASE_URL + "/services/Soap/c/50.0/";

    public static MetadataConnection login() {
        SfUserModel admin = PropertyManager.getAdminUser();

        final String username = admin.getUsername();
        final String password = admin.getPassword();
        try {
            return createMetadataConnection(loginToSalesforce(username, password, URL));
        } catch (ConnectionException e) {
            throw new RuntimeException("\nMetadata connection issue\n ", e);
        }
    }

    public static LoginResult loginToSalesforce(final String username, final String password, final String loginUrl) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();
        config.setAuthEndpoint(loginUrl);
        config.setServiceEndpoint(loginUrl);
        config.setManualLogin(true);
        return (new EnterpriseConnection(config)).login(username, password);
    }

    private static MetadataConnection createMetadataConnection(final LoginResult loginResult) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();

        config.setServiceEndpoint(loginResult.getMetadataServerUrl());
        config.setConnectionTimeout(90000);
        config.setSessionId(loginResult.getSessionId());

        return new MetadataConnection(config);
    }
}
