package io.omni.example.tools.metadata;

import com.sforce.soap.metadata.*;
import com.sforce.ws.ConnectionException;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import org.awaitility.Awaitility;

import java.time.Duration;
import java.util.Objects;

public class MetadataUtils {

    private static final String APPEARS_MORE_THAN_ONCE_MESSAGE = "appears more than once";

    protected MetadataConnection getConnection() {
        return Awaitility.await()
                .atMost(Duration.ofSeconds(120))
                .ignoreExceptions()
                .until(MetadataLogin::login, Objects::nonNull);
    }

    public Metadata[] readMetadataRecords(MetadataTypes metadataType, String metadataName) {
        try {
            ReadResult readResult = getConnection().readMetadata(metadataType.getName(), new String[]{metadataName});
            return readResult.getRecords();
        } catch (ConnectionException connectionException) {
            throw new RuntimeException(connectionException);
        }
    }

    private void updateMetadata(Metadata[] metadata) {
        try {
            SaveResult result = getConnection().updateMetadata(metadata)[0];
            if (result.isSuccess())
                System.out.printf("Updated component: %s%n", result.getFullName());
            else {
                com.sforce.soap.metadata.Error error = result.getErrors()[0];
                if (error.getMessage().contains(APPEARS_MORE_THAN_ONCE_MESSAGE))
                    System.out.println("Field already added to layout.");
                else {
                    System.out.printf("\nError message: %s%n", error.getMessage());
                    System.out.printf("Status code: %s%n", error.getStatusCode());
                    throw new RuntimeException(String.format("Errors were encountered while updating %s\nError message: %s", result.getFullName(), error.getMessage()));
                }
            }
        } catch (ConnectionException connectionException) {
            throw new RuntimeException(connectionException);
        }
    }

    private void createMetadata(Metadata[] metadata) {
        try {
            SaveResult result = getConnection().createMetadata(metadata)[0];
            if (result.isSuccess())
                System.out.printf("Updated component: %s%n", result.getFullName());
            else {
                com.sforce.soap.metadata.Error error = result.getErrors()[0];
                if (error.getMessage().contains(APPEARS_MORE_THAN_ONCE_MESSAGE))
                    System.out.println("Field already added to layout.");
                else {
                    System.out.printf("\nError message: %s%n", error.getMessage());
                    System.out.printf("Status code: %s%n", error.getStatusCode());
                    throw new RuntimeException(String.format("Errors were encountered while updating %s", result.getFullName()));
                }
            }
        } catch (ConnectionException connectionException) {
            throw new RuntimeException(connectionException);
        }
    }

    private void upsertMetadata(Metadata[] metadata) {
        UpsertResult[] upsertResults;
        try {
            upsertResults = getConnection().upsertMetadata(metadata);

            for (UpsertResult upsertResult : upsertResults) {
                if (upsertResult.isSuccess()) {
                    System.out.println("Success!");
                    if (upsertResult.isCreated()) {
                        System.out.printf("Created component: %s%n", upsertResult.getFullName());
                    } else {
                        System.out.printf("Upserted component: %s%n", upsertResult.getFullName());
                    }
                } else {
                    StringBuilder errorMsgs = new StringBuilder("Errors were encountered while upserting ").append(upsertResult.getFullName());
                    for (com.sforce.soap.metadata.Error e : upsertResult.getErrors()) {
                        errorMsgs
                                .append("\nError message: ")
                                .append(e.getMessage())
                                .append("\nStatus code: ")
                                .append(e.getStatusCode());
                    }
                    throw new RuntimeException(errorMsgs.toString());
                }
            }
        } catch (ConnectionException e1) {
            e1.printStackTrace();
        }
    }

    public void updateMetadata(Metadata metadata) {
        updateMetadata(new Metadata[]{metadata});
    }

    protected void createMetadata(Metadata metadata) {
        createMetadata(new Metadata[]{metadata});
    }

    public void upsertMetadata(Metadata metadata) {
        upsertMetadata(new Metadata[]{metadata});
    }


    protected CustomObject getCustomObject(SObject object) {
        return (CustomObject) readMetadataRecords(MetadataTypes.CUSTOM_OBJECT, object.getName())[0];
    }

    public enum MetadataTypes {
        CUSTOM_OBJECT("CustomObject"),
        FIELD_SET("FieldSet"),
        PROFILE("Profile"),
        CUSTOM_METADATA("CustomMetadata"),
        LAYOUT("Layout"),
        CUSTOM_TAB("CustomTab"),
        CUSTOM_APPLICATION("CustomApplication"),
        FLEXI_PAGE("FlexiPage"),
        CUSTOM_FIELD("CustomField"),
        TOPICS_FOR_OBJECTS("TopicsForObjects"),
        CUSTOM_LABEL("CustomLabel"),
        PERMISSION_SET("PermissionSet"),
        LIST_VIEW("ListView"),
        DUPLICATE_RULE("DuplicateRule"),
        MATCHING_RULE("MatchingRule");

        private final String name;

        MetadataTypes(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
