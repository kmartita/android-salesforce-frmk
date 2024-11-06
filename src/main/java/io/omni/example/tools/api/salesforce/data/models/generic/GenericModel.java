package io.omni.example.tools.api.salesforce.data.models.generic;

import io.omni.example.tools.api.Generate;
import io.omni.example.tools.api.HasName;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class GenericModel<Field extends Enum<Field> & HasName> {

    public static final String SET_OF_FIELDS_TO_GENERATE_MODEL_MUST_NOT_BE_NULL = "Set of fields to generate Model must not be null";

    private final Map<Field, Object> fields;

    public GenericModel(Map<Field, Object> fields) {
        this.fields = Collections.unmodifiableMap(fields);
    }

    public Map<String, Object> getFieldMap(){
        return fields.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getName(), Map.Entry::getValue));
    }

    public Object get(Field field) {
        return fields.get(field);
    }

    public String getString(Field field) {
        if (fields.get(field) instanceof String) {
            return (String) fields.get(field);
        } else if (Objects.isNull(fields.get(field))) {
            return null;
        }
        throw new RuntimeException(format("Object [%s] is not instance of String class", fields.get(field)));
    }

    public Set<Field> includedFields() {
        return fields.keySet();
    }

    public static <Field extends Enum<Field> & HasName> GenericModelBuilder<Field> builder() {
        return new GenericModelBuilder<>();
    }

    public static <Field extends Enum<Field> & HasName> GenericModelBuilder<Field> builder(Class<Field> clazz) {
        return new GenericModelBuilder<>();
    }

    public GenericModel<Field> edit(Map<Field, Object> fieldsToUpdate) {
        GenericModelBuilder<Field> builder = GenericModel.builder();

        for (Field field : this.includedFields())
            builder.setField(field, this.fields.get(field));

        for (Map.Entry<Field, Object> map : fieldsToUpdate.entrySet())
            builder.setField(map.getKey(), map.getValue());

        return builder.build();
    }

    private GenericModel<Field> removeFields(Set<Field> fieldsToRemove) {
        GenericModelBuilder<Field> builder = GenericModel.builder();

        List<Field> includedFields = new ArrayList<>(this.includedFields());
        includedFields.removeAll(fieldsToRemove);

        for (Field field : includedFields)
            builder.setField(field, this.fields.get(field));

        return builder.build();
    }

    @SafeVarargs
    public final GenericModel<Field> removeFields(Field... fields) {
        return removeFields(new HashSet<>(Arrays.asList(fields)));
    }

    public static <Field extends Enum<Field> & Generate & HasName> GenericModelBuilder<Field> preGenerate(Set<Field> fields) {
        Objects.requireNonNull(fields, SET_OF_FIELDS_TO_GENERATE_MODEL_MUST_NOT_BE_NULL);
        GenericModelBuilder<Field> newModelBuilder = GenericModel.builder();
        fields.forEach(f -> newModelBuilder.setField(f, f.generate()));
        return newModelBuilder;
    }

    @SafeVarargs
    public final <F extends Enum<F> & Generate & HasName> GenericModelBuilder<F> preGenerate(F... fields) {
        return preGenerate(new HashSet<>(Arrays.asList(fields)));
    }

    public <F extends Enum<F> & Generate & HasName> GenericModel<F> generate(Set<F> fields) {
        return preGenerate(fields).build();
    }

    @SafeVarargs
    public final <F extends Enum<F> & Generate & HasName> GenericModel<F> generate(F... fields) {
        return generate(new HashSet<>(Arrays.asList(fields)));
    }

    public GenericModel<Field> edit(Field fieldToUpdate, Object valueToUpdate) {
        GenericModelBuilder<Field> builder = GenericModel.builder();

        for (Field field : this.includedFields())
            builder.setField(field, this.fields.get(field));

        builder.setField(fieldToUpdate, valueToUpdate);

        return builder.build();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fields == null) ? 0 : fields.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GenericModel<?> other = (GenericModel<?>) obj;
        if (fields == null) {
            return other.fields == null;
        } else return fields.equals(other.fields);
    }

    @Override
    public String toString() {
        return format("[fields=%s]", getFieldMap());
    }

}
