package io.omni.example.tools.api.salesforce.data.models.generic;

import io.omni.example.tools.api.HasName;

import java.util.HashMap;
import java.util.Map;

public class GenericModelBuilder<Field extends Enum<Field> & HasName> {

    private final Map<Field, Object> fields = new HashMap<>();

    public GenericModel<Field> build() {
        return new GenericModel<>(fields);
    }

    public GenericModelBuilder<Field> setField(Field field, Object value) {
        this.fields.put(field, value);
        return this;
    }
}
