package io.omni.example.tools.api.salesforce.data.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.IsRecordType;
import io.omni.example.tools.api.salesforce.data.models.*;
import io.omni.example.tools.api.salesforce.recordtypes.MasterRecordType;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.omni.example.tools.properties.PropertyManager;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class RecordTypeRequests extends BaseRequests {

    //all requests from salesforce are performed by admin user
    public RecordTypeRequests() {
        super(PropertyManager.getAdminUser());
    }

    //TODO - rewrite
    public List<RecordTypeModel> getRecordTypes() {
        try {
            String sql = new SelectBuilder()
                    .column("Id")
                    .column("DeveloperName")
                    .column("SobjectType")
                    .from("RecordType")
                    .toString();

            return Objects.requireNonNull(getSOQLService()
                            .recordTypeQuery(sql)
                            .execute()
                            .body())
                    .getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO - rewrite
    private List<RecordTypeModel> getRecordTypesBySObject(SObject sObject) {
        try {
            String sql = new SelectBuilder()
                    .column("Id")
                    .column("Name")
                    .column("SobjectType")
                    .from("RecordType")
                    .where(format("SobjectType = '%s'", sObject.getName()))
                    .toString();

            return Objects.requireNonNull(getSOQLService()
                            .recordTypeQuery(sql)
                            .execute()
                            .body())
                    .getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RecordTypeModel getFirstRecordType(SObject sObject) {
        return getRecordTypesBySObject(sObject)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(format("First record type for sObject '%s' not found.", sObject)));
    }

    public List<String> getActiveRecordTypeNames(SObject sObject){
        try {
            return Objects.requireNonNull(getSalesforceService()
                            .objectDescribe(sObject.getName())
                            .execute().body())
                    .getRecordTypes()
                    .stream()
                    .filter(RecordTypeInfoModel::isAvailable)
                    .map(RecordTypeInfoModel::getName)
                    .filter(i -> !i.equals(MasterRecordType.MASTER.getName()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <RecordType extends Enum<RecordType> & IsRecordType> String getSObjectRecordTypeId(SObject sObject,
                                                                                              RecordType recordType){
        try {
            return Objects.requireNonNull(getSalesforceService()
                            .objectDescribe(sObject.getName())
                            .execute().body())
                    .getRecordTypes()
                    .stream()
                    .filter(rt -> recordType.getUiName().equals(rt.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(format("Record type '%s' for sObject '%s' not found.", recordType, sObject)))
                    .getRecordTypeId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
