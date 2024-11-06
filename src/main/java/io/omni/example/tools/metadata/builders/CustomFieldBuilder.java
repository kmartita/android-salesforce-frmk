package io.omni.example.tools.metadata.builders;

import com.sforce.soap.metadata.CustomField;
import com.sforce.soap.metadata.FieldType;
import io.omni.example.tools.api.HasLabel;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import org.apache.commons.lang3.StringUtils;

public class CustomFieldBuilder {

    private CustomField customField;

    public CustomFieldBuilder() {
        customField = new CustomField();
    }

    public <Field extends Enum<Field> & HasName & HasLabel> CustomFieldBuilder setFullName(SObject object, Field field) {
        customField.setFullName(String.format("%s.%s", object.getName(), field.getName()));
        return this;
    }

    public <Field extends Enum<Field> & HasName & HasLabel> CustomFieldBuilder setLabel(Field field) {
        customField.setLabel(field.getLabel());
        return this;
    }

    public CustomFieldBuilder setType(FieldType type) {
        customField.setType(type);
        return this;
    }

    public CustomFieldBuilder setLength(int length) {
        customField.setLength(length);
        return this;
    }

    public CustomFieldBuilder setReferenceTo(SObject object) {
        customField.setReferenceTo(object.getName());
        return this;
    }

    public <Field extends Enum<Field> & HasName & HasLabel> CustomFieldBuilder setRelationshipName(Field field) {
        customField.setRelationshipName(String.valueOf(field.getName()).replaceAll("__c", StringUtils.EMPTY));
        return this;
    }

    public <Field extends Enum<Field> & HasName & HasLabel> CustomFieldBuilder setRelationshipLabel(Field field) {
        customField.setRelationshipLabel(String.valueOf(field.getName()).replaceAll("__c", StringUtils.EMPTY));
        return this;
    }

    public CustomField build() {
        return customField;
    }
}
