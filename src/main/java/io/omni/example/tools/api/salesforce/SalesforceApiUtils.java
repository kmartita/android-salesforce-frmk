package io.omni.example.tools.api.salesforce;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.HasName;
import io.omni.example.tools.api.salesforce.data.SalesforceData;
import io.omni.example.tools.api.salesforce.data.models.generic.GenericModel;
import io.omni.example.tools.api.salesforce.data.models.generic.ResponseModel;
import io.omni.example.tools.api.salesforce.data.services.SalesforceService;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.databind.DeserializationFeature;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static io.omni.example.tools.properties.PropertyManager.getAdminUser;
import static java.lang.String.format;

public class SalesforceApiUtils extends BaseRequests {

    public static final String OCE_PREFIX = "OCE__";
    public static final String QIDC_PREFIX = "QIDC__";

    public SalesforceApiUtils(SfUserModel user) {
        super(user);
    }

    public SalesforceApiUtils() {
        super(getAdminUser());
    }

    /**
     * Generate List with Objects From Json using Object Mapper
     * Object's properties have to be set with proper annotations
     *
     * @return List with <T> objects
     */
    @Step
    public static <T> Object generateListWithObjectsFromJson(Class<T> tClass, JSONObject json) {

        try {
            return new ObjectMapper().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                    .readerFor(tClass)
                    .readValue(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeToURL(String inputValue) {
        String outputValue;
        outputValue = URLEncoder.encode(inputValue, StandardCharsets.UTF_8);
        return outputValue;
    }

    public <Field extends Enum<Field> & HasName> SalesforceData<Field> createSObject(SObject sObject, GenericModel<Field> model) {
        SalesforceService service = getSalesforceService();
        RequestBody requestBody = RequestBody
                .create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        (new JSONObject(model.getFieldMap())).toString());

        String id;
        Call<ResponseModel> object = service.create(sObject.getName(), requestBody);

        try {
            Response<ResponseModel> response = object.execute();
            if (response.isSuccessful()) {
                id = Objects.requireNonNull(response.body()).getId();
            } else {
                throw new RuntimeException(format("Error occurred during creating [object=%s]. " +
                        "Error is [%s]", sObject, Objects.requireNonNull(response.errorBody()).string()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new SalesforceData<>(sObject, id, model);
    }

    public <Field extends Enum<Field> & HasName> void updateSObject(SObject sObject,
                                                                    String id,
                                                                    GenericModel<Field> model) {
        SalesforceService service = getSalesforceService();
        RequestBody responseBody = RequestBody
                .create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        (new JSONObject(model.getFieldMap())).toString());
        Call<ResponseModel> object = service.update(sObject.getName(), id, responseBody);

        try {
            Response<ResponseModel> response = object.execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException(format("Error occurred during updating [object=%s, id=%s]. " +
                        "Error is [%s]", sObject, id, Objects.requireNonNull(response.errorBody()).string()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSObject(SObject sObject, String id) {
        SalesforceService service = getSalesforceService();
        Call<ResponseBody> request = service.delete(sObject.getName(), id);
        Response<ResponseBody> response;

        try {
            response = request.execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException(format("Error occurred during deleting [object=%s, id=%s]. " +
                        "Error is [%s]", sObject, id, Objects.requireNonNull(response.errorBody()).string()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSObject(SObject sObject, List<String> ids) {
        ids.forEach(id -> deleteSObject(sObject, id));
    }

    public void executeAnonymousApexCode(String anonymousBody) {
        try {
            Response<ResponseBody> response = getSalesforceService().executeAnonymousApexCode(anonymousBody).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException(String.format("Execution of anonymous apex code failed with response: %s", response));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
