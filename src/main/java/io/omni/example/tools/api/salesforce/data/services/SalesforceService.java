package io.omni.example.tools.api.salesforce.data.services;

import io.omni.example.tools.api.salesforce.data.models.LoginModel;
import io.omni.example.tools.api.salesforce.data.models.ObjectDescribeModel;
import io.omni.example.tools.api.salesforce.data.models.ObjectPicklistFieldValuesModel;
import io.omni.example.tools.api.salesforce.data.models.generic.ResponseModel;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface SalesforceService {

    @GET("/services/data/v56.0/sobjects/{sObject}/describe")
    Call<ObjectDescribeModel> objectDescribe(@Path("sObject") String sObject);

    @GET("/services/data/v56.0/sobjects/{sObject}/describe/layouts/{recordTypeId}")
    Call<ObjectDescribeModel> objectDescribeLayout(@Path("sObject") String sObject, @Path("recordTypeId") String recordTypeId);

    @GET("/services/data/v56.0/ui-api/object-info/{sObject}/picklist-values/{id}/{field}")
    Call<ObjectPicklistFieldValuesModel> objectPicklistFieldByRecordType(@Path("sObject") String sObject,
                                                                         @Path("id") String recordTypeId,
                                                                         @Path("field") String field);

    @POST("/services/oauth2/token")
    Call<LoginModel> login(@Query("grant_type") String grandType,
                           @Query("client_id") String clientId,
                           @Query("client_secret") String clientSecret,
                           @Query("redirect_url") String redirectedUrl,
                           @Query("password") String password,
                           @Query("username") String username
    );

    @POST("/services/data/v56.0/sobjects/{sObject}")
    Call<ResponseModel> create(@Path("sObject") String sObject, @Body RequestBody body);

    @PATCH("/services/data/v56.0/sobjects/{sObject}/{id}")
    Call<ResponseModel> update(@Path("sObject") String sObject, @Path("id") String id, @Body RequestBody body/*@Body Map<String, Object> updateFields*/);

    @DELETE("/services/data/v56.0/sobjects/{sObject}/{id}")
    Call<ResponseBody> delete(@Path("sObject") String sObject, @Path("id") String id);

    @GET("/services/data/v56.0/tooling/executeAnonymous")
    Call<ResponseBody> executeAnonymousApexCode(@Query("anonymousBody") String anonymousBody);
}
