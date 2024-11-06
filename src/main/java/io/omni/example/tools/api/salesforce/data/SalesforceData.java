package io.omni.example.tools.api.salesforce.data;

import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.sobject.SObject;

public class SalesforceData<Field extends Enum<Field> & HasName> {

    private final SObject sobject;
    private final String id;
    private final GenericModel<Field> model;

    public SalesforceData(SObject sobject, String id, GenericModel<Field> model){
        this.sobject = sobject;
        this.id = id;
        this.model = model;
    }

    public SObject getSObject(){
        return sobject;
    }

    public String getId(){
        return id;
    }

    public GenericModel<Field> getGenericModel(){
        return model;
    }

    public Object get(Field field){
        return getGenericModel().get(field);
    }

    @Override
    public String toString() {
        return String.format("SObject [%s] is created by data [id=%s, model=%s]", sobject, id, model);
    }
}
