package io.omni.example.tools.api.salesforce.data.services;

import io.omni.example.tools.api.salesforce.data.models.ResponseContentApiModel;
import io.omni.example.tools.api.salesforce.data.models.SalesforceObjectModel;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ContentApiService {

    @POST("/services/content/v1.0/{feature}/{format}/insert")
    Call<ResponseContentApiModel<SalesforceObjectModel>> insert(@Path("feature") String feature,
                                                                @Path("format") String format,
                                                                @Body RequestBody body);

    @POST("/services/content/v1.0/{feature}/{format}/update")
    Call<ResponseContentApiModel<SalesforceObjectModel>> update(@Path("feature") String feature,
                                                                @Path("format") String format,
                                                                @Body RequestBody body);

}
