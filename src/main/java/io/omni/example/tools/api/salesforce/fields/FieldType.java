package io.omni.example.tools.api.salesforce.fields;

import io.omni.example.tools.api.HasName;

import java.util.Arrays;

import static java.lang.String.format;

public enum FieldType implements HasName {

    STRING("string"),
    PICKLIST("picklist"),
    CHECKBOX("checkbox"),
    BOOLEAN("boolean"),
    URL("url"),
    DATE("date"),
    DATETIME("datetime"),
    EMAIL("email"),
    CURRENCY("currency"),
    PHONE("phone"),
    REFERENCE("reference"),
    TEXTAREA("textarea"),
    TEXT("text"),
    INT("int"),
    ID("id"),
    DOUBLE("double"),
    MULTI_PICKLIST("multipicklist"),
    ADDRESS("address"),
    LOCATION("location"),
    BASE64("base64"),
    PERCENT("percent"),
    ACCOUNT_PHONE("phone"),
    TIME("time"),
    RECORDTYPEID("recordTypeId"),
    NUMBER("number");

    FieldType(String name) {
        this.name = name;
    }

    private final String name;

    public static FieldType getByName(String name) {
        return Arrays.stream(FieldType.values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("\nUnknown field type=%s\n", name)));
    }

    @Override
    public String getName() {
        return name;
    }
}
