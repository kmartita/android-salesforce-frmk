package io.omni.example.tools.metadata;

import com.sforce.soap.metadata.CustomField;
import com.sforce.soap.metadata.FieldType;
import io.omni.example.tools.api.HasLabel;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.metadata.builders.CustomFieldBuilder;

public class MetadataCustomFieldUtils extends MetadataUtils {

    public <Field extends Enum<Field> & HasName & HasLabel> void createCustomTextField(SObject object, Field field) {
        CustomField customField = new CustomFieldBuilder()
                .setFullName(object, field)
                .setLabel(field)
                .setType(FieldType.Text)
                .setLength(255)
                .build();
        upsertMetadata(customField);
    }
}
