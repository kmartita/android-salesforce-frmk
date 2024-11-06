package io.omni.example.tools.metadata;

import com.sforce.soap.metadata.CustomObject;
import com.sforce.soap.metadata.FieldSet;
import com.sforce.soap.metadata.FieldSetItem;
import io.omni.example.tools.api.salesforce.Custom;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static io.omni.example.tools.api.salesforce.SalesforceApiUtils.OCE_PREFIX;
import static io.omni.example.tools.api.salesforce.sobject.SObject.*;
import static java.lang.String.format;
import static java.lang.System.arraycopy;
import static java.lang.System.out;

public class MetadataFieldSetUtils extends MetadataUtils{

    private OptionalInt optionalFieldSet(CustomObject customObject, FieldSets fieldSet){
        FieldSet[] fieldSetArray = customObject.getFieldSets();
        return IntStream.range(0, fieldSetArray.length)
                .filter(i -> fieldSetArray[i].getFullName().equalsIgnoreCase(fieldSet.getApiName()))
                .findFirst();
    }

    public boolean isFieldSetFromMetadataPresent(CustomObject customObject, FieldSets fieldSet) {
        return optionalFieldSet(customObject, fieldSet)
                .isPresent();
    }

    public FieldSet readFieldSetFromMetadata(CustomObject customObject, FieldSets fieldSet) {
        FieldSet[] fieldSetArray = customObject.getFieldSets();
        int index = optionalFieldSet(customObject, fieldSet)
                .orElseThrow(() -> new RuntimeException(format("There is no field set by api name '%s' in metadata '%s'",
                        fieldSet.getApiName(), customObject.getFullName())));
        return fieldSetArray[index];
    }

    public void configureFieldSet(FieldSets fieldSet, List<String> list) {
        CustomObject customObject = getCustomObject(fieldSet.getSObject());
        boolean isFieldSetPresent = isFieldSetFromMetadataPresent(customObject, fieldSet);

        FieldSet thisFieldSet;
        String fieldSetName = format("%s.%s", customObject.getFullName(), fieldSet.getApiName());
        FieldSet newFieldSet;

        if(isFieldSetPresent) {
            thisFieldSet = readFieldSetFromMetadata(customObject, fieldSet);
            newFieldSet = updateFieldSet(thisFieldSet, fieldSetName, list);
            updateMetadata(newFieldSet);

        } else {
            newFieldSet = generateFieldSet(fieldSetName, list);
            String fullName = newFieldSet.getFullName();
            String label = StringUtils.join(StringUtils
                    .splitByCharacterTypeCamelCase(fullName.substring(fullName.lastIndexOf('.') + 1).replace(OCE_PREFIX,
                            StringUtils.EMPTY).trim().replaceAll("\\d+", StringUtils.EMPTY)), StringUtils.SPACE);
            newFieldSet.setLabel(label);
            newFieldSet.setDescription("For mobile use");
            out.printf("Create new Field Set: <'%s'> with fields: <'%s'>.%n", fieldSet.getApiName(), list);
            createMetadata(newFieldSet);
        }
    }

    private FieldSet updateFieldSet(FieldSet fieldSet, String fieldSetName, List<String> newList) {
        FieldSetItem[] existedFieldSetItemArray = fieldSet.getDisplayedFields();

        Set<String> existedFieldSet = Arrays.stream(existedFieldSetItemArray).map(FieldSetItem::getField).collect(Collectors.toSet());
        Set<String> newFieldSet = new HashSet<>(newList);

        String[] newFieldSetArray = newFieldSet.toArray(new String[0]);
        String[] updateFieldSetArray = Stream.concat(existedFieldSet.stream(), newFieldSet.stream()).distinct().toArray(String[]::new);

        if (existedFieldSet.isEmpty()) {
            out.printf("Field Set: <'%s'> is empty and should be filled by new set of fields: <'%s'>.%n",
                    fieldSetName, newFieldSet);
            collectFieldSet(fieldSet, newFieldSetArray);

        } else if (!existedFieldSet.containsAll(newFieldSet)) {
            out.printf("Field Set: <'%s'> is filled by set of fields: <'%s'>" +
                    " and should be updated by new set of fields: <'%s'>.%n", fieldSetName, existedFieldSet, newFieldSet);
            nullifyFieldSet(fieldSet);
            collectFieldSet(fieldSet, updateFieldSetArray);

        } else if (!newFieldSet.containsAll(existedFieldSet)) {
            out.printf("Field Set: <'%s'> is filled by set of fields: <'%s'> and should be updated by " +
                    "new set of fields: <'%s'>.%n", fieldSetName, existedFieldSet, newFieldSet);
            nullifyFieldSet(fieldSet);
            collectFieldSet(fieldSet, newFieldSetArray);

        } else out.printf("Field Set: <'%s'> is filled as expected and haven't need to update.%n", fieldSetName);

        fieldSet.setFullName(fieldSetName);
        return fieldSet;
    }

    private void collectFieldSet(FieldSet fieldSet, String[] newFieldSetArray) {
        FieldSetItem[] newFieldSetItems = new FieldSetItem[newFieldSetArray.length];
        for (int i = 0; i < newFieldSetArray.length; i++)
            newFieldSetItems[i] = new FieldSetItem();

        arraycopy(newFieldSetItems, 0, newFieldSetItems, 0, newFieldSetItems.length);
        IntStream.range(0, newFieldSetArray.length).forEach(i -> newFieldSetItems[i].setField(newFieldSetArray[i]));
        fieldSet.setDisplayedFields(newFieldSetItems);
    }

    private void nullifyFieldSet(FieldSet fieldSet) {
        String[] emptyFieldSetArray = new HashSet<>().toArray(new String[0]);
        collectFieldSet(fieldSet, emptyFieldSetArray);
    }

    private FieldSet generateFieldSet(String fieldSetName, List<String> list) {
        FieldSet fieldSet = new FieldSet();
        FieldSetItem[] items = new FieldSetItem[list.size()];
        IntStream.range(0, items.length).forEach(i -> items[i] = new FieldSetItem());
        IntStream.range(0, items.length).forEach(i -> items[i].setField(list.get(i)));
        fieldSet.setDisplayedFields(items);
        String fullName =
                fieldSetName.substring(0, fieldSetName.lastIndexOf('.') + 1)
                        + fieldSetName.substring(fieldSetName.lastIndexOf('.') + 1)
                        .replace(OCE_PREFIX, StringUtils.EMPTY).trim();
        fieldSet.setFullName(fullName);
        return fieldSet;
    }

    public enum FieldSets {

        @Custom
        ACCOUNT_FILTER_FIELD_SET("Account_Filters_FieldSet", ACCOUNT),
        SBC_DEFAULT_BUSINESS_ACCOUNT("SBC_DEFAULT_BUSINESS_ACCOUNT", ACCOUNT),
        SBC_DEFAULT_PERSON_ACCOUNT("SBC_DEFAULT_PERSON_ACCOUNT", ACCOUNT),
        SBC_DEFAULT_PERSON_ACCOUNTADDRESS("SBC_DEFAULT_PERSON_ACCOUNTADDRESS", ACCOUNT_ADDRESS),
        @Custom
        ACCOUNT_ADDRESS_FILTER_FIELD_SET("AccountAddress_Filters_FieldSet", ACCOUNT_ADDRESS),
        SBC_DEFAULT_BUSINESS_ACCOUNT_ADDRESS("SBC_DEFAULT_BUSINESS_ACCOUNTADDRESS", ACCOUNT_ADDRESS),
        @Custom
        ATF_FILTER_FIELD_SET("ATF_Filters_FieldSet", ACCOUNT_TERRITORY_FIELDS),
        @Custom
        SORT_BY("Sort_By", ACCOUNT_TERRITORY_FIELDS),
        @Custom
        ATF_BULK_UPDATE("ATF_BulkUpdate", ACCOUNT_TERRITORY_FIELDS),
        @Custom
        ATP_FILTER_FIELD_SET("ATP_Filters_FieldSet", ACCOUNT_TERRITORY_PRODUCT),
        @Custom
        ATP_BULK_UPDATE("ATP_BulkUpdate", ACCOUNT_TERRITORY_PRODUCT),
        @Custom
        WRITE_IN_FIELD_SET("Write_In_FieldSet", MEETING_MEMBER),
        @Custom
        SURVEY_RESPONSE_FIELD_SET("Survey_Response_FieldSet", SURVEY_RESPONSE);

        private final String apiName;
        private final SObject sObject;

        FieldSets(String name, SObject sObject) {
            this.apiName = name;
            this.sObject = sObject;
        }

        public String getApiName() {
            boolean isCustom = FieldUtils.getField(this.getClass(), this.name()).isAnnotationPresent(Custom.class);
            return isCustom ? this.apiName : format("%s%s", OCE_PREFIX, this.apiName);
        }

        public SObject getSObject() {return this.sObject;}
    }
}
