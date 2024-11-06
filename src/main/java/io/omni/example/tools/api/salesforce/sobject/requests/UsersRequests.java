package io.omni.example.tools.api.salesforce.sobject.requests;

import ca.krasnay.sqlbuilder.SelectBuilder;
import io.omni.example.models.SfUserModel;
import io.omni.example.tools.api.BaseRequests;
import io.omni.example.tools.api.salesforce.sobject.models.UserModel;
import io.omni.example.tools.api.salesforce.fields.DefaultField;
import io.omni.example.tools.api.salesforce.fields.UserField;
import io.omni.example.tools.api.salesforce.sobject.SObject;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.omni.example.tools.properties.PropertyManager.getAdminUser;
import static java.lang.String.format;

public class UsersRequests extends BaseRequests {

    public UsersRequests(SfUserModel user) {
        super(user);
    }

    public UsersRequests() {
        super(getAdminUser());
    }

    private List<UserModel> getUsers() {
        try {
            String sql = new SelectBuilder()
                    .column(DefaultField.ID.getName())
                    .column(DefaultField.NAME.getName())
                    .column(UserField.USER_NAME.getName())
                    .column(UserField.FIRST_NAME.getName())
                    .column(UserField.LAST_NAME.getName())
                    .column(UserField.IS_ACTIVE.getName())
                    .column(UserField.MANAGER_ID.getName())
                    .column(UserField.TIMEZONE_SID_KEY.getName())
                    .from(SObject.USER.getName())
                    .toString();
            return Objects.requireNonNull(Objects.requireNonNull(getSOQLService().userRequest(sql).execute().body())
                    .getRecords());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Get user id by '{0}' user name")
    public String getUserId(String userName) {
        return getUserByUserName(userName).getId();
    }

    @Step("Get user by '{0}' user name")
    public UserModel getUserByUserName(String userName) {
        return getUsers().stream()
                .filter(user -> userName.equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(format("The '%s' record where <%s = '%s'> not found.",
                        SObject.USER.getName(), UserField.USER_NAME.getName(), userName)));
    }

    @Step("Get all active users.")
    public List<UserModel> getActiveUsers() {
        return getUsers().stream().filter(UserModel::getIsActive).collect(Collectors.toList());
    }

}
