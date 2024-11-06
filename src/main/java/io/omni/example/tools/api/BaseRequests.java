package io.omni.example.tools.api;

import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.salesforce.data.models.LoginModel;
import io.omni.example.tools.api.salesforce.data.services.SOQLService;
import io.omni.example.tools.api.salesforce.data.services.SalesforceService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.omni.example.tools.properties.EnvManager.*;

public class BaseRequests {

    protected final String BEARER_TOKEN;
    protected static final String FIELDS_ALL = "FIELDS(ALL)";
    protected static final String LIMIT_200 = "LIMIT 200";

    public BaseRequests(SfUserModel user){
        try {
            Response<LoginModel> response = ApiClient.getApiClient()
                    .create(SalesforceService.class).login("password",
                            CLIENT_ID,
                            CLIENT_SECRET,
                            REDIRECTED_URL,
                            user.getPassword(),
                            user.getUsername()).execute();
            if (response.isSuccessful()) {
                assert response.body() != null;
                BEARER_TOKEN = response.body().getAccessToken();
                ApiClient.setBaseUrl(response.body().getInstanceUrl());
            } else {
                throw new RuntimeException("Can't login to SF with user " + user.getUsername() + "/" + user.getPassword());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SOQLService getSOQLService(){
        return getRetrofitClient().create(SOQLService.class);
    }

    public SalesforceService getSalesforceService(){
        return getRetrofitClient().create(SalesforceService.class);
    }

    private Retrofit getRetrofitClient() {
        return ApiClient.getApiClient().newBuilder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient()
                        .newBuilder()
                        .connectTimeout(100, TimeUnit.SECONDS)
                        .readTimeout(100, TimeUnit.SECONDS)
                        .addInterceptor(chain -> {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                                    .build();
                            return chain.proceed(request);
                        }).build())
                .build();
    }
}
