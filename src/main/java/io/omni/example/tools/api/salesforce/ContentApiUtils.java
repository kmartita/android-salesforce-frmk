package io.omni.example.tools.api.salesforce;

import io.omni.example.tools.api.ApiClient;
import io.omni.example.tools.api.salesforce.data.models.ResponseContentApiModel;
import io.omni.example.tools.api.salesforce.data.models.SalesforceObjectModel;
import io.omni.example.tools.api.salesforce.data.services.ContentApiService;
import io.omni.example.tools.properties.EnvManager;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class ContentApiUtils {

    public String insertFile(String feature, String fileFormat, JSONObject body){
        RequestBody requestBody = prepareRequestBody(body);
        return parseResponse(getContentApiService().insert(feature, fileFormat, requestBody));
    }

    public String updateFile(String feature, String fileFormat, JSONObject body) {
        RequestBody requestBody = prepareRequestBody(body);
        return parseResponse(getContentApiService().update(feature, fileFormat, requestBody));
    }

    public String generateBase64(String filePath) {
        byte[] fileContent;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(fileContent);
    }

    private ContentApiService getContentApiService(){
        return getRetrofitClient().create(ContentApiService.class);
    }

    private Retrofit getRetrofitClient() {
        return ApiClient.getApiClient().newBuilder()
                .baseUrl(EnvManager.CONTENT_API_URL)
                .client(new OkHttpClient()
                        .newBuilder()
                        .connectTimeout(100, TimeUnit.SECONDS)
                        .readTimeout(100, TimeUnit.SECONDS)
                        .build())
                .build();
    }

    private RequestBody prepareRequestBody(JSONObject body) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), body.toString());
    }

    private String parseResponse(Call<ResponseContentApiModel<SalesforceObjectModel>> object) {
        String id;
        try {
            Response<ResponseContentApiModel<SalesforceObjectModel>> response = object.execute();
            if(response.isSuccessful()) {
                id = Objects.requireNonNull(response.body()).getFirstObject().getId();
            } else {
                throw new RuntimeException(format("Error occurred during [%s] file. Error is [%s]", object.request().method(),
                        Objects.requireNonNull(response.errorBody()).string()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
