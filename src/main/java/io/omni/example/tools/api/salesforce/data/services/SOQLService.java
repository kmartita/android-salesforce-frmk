package io.omni.example.tools.api.salesforce.data.services;

import io.omni.example.tools.api.salesforce.data.models.*;
import io.omni.example.tools.api.salesforce.sobject.models.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOQLService {

    String ENDPOINT = "/services/data/v56.0/query/";
    String TOOLING_ENDPOINT = "/services/data/v56.0/tooling/query/";

    @GET(TOOLING_ENDPOINT)
    Call<SOQLBody<LayoutModel>> performLayoutRequest(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<AccountModel>> accountQuery(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<RecordTypeModel>> recordTypeQuery(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<UserTerritory2AssociationModel>> userTerritory2AssociationRequest(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<Territory2Model>> territory2Request(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<UserModel>> userRequest(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<MobileAppUpgradeModel>> performMobileAppUpgradeRequest(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<ConfigurationModel>> configurationRequest(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<ProfileModel>> profileRequest(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<ProductModel>> productQuery(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<OrganizationModel>> organizationRequest(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<DBSchemaModel>> performDBSchemaRequest(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<PermissionSetModel>> permissionSetRequest(@Query("q") String query);

    @GET(ENDPOINT)
    Call<SOQLBody<PermissionSetAssignmentModel>> permissionSetAssignmentRequest(@Query("q") String query);
}
