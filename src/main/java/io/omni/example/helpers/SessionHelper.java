package io.omni.example.helpers;

import io.omni.example.models.SfUserModel;
import io.omni.example.screens.app.login.LoginScreen;
import io.omni.example.screens.app.login.PinCodeScreen;
import io.omni.example.screens.app.login.SelectTerritoryScreen;
import io.omni.example.screens.app.siderbar.HomeScreen;
import io.qameta.allure.Step;

public class SessionHelper {

    private PinCodeScreen pinCodeScreen;
    private LoginScreen loginScreen;
    private SfUserModel activeSessionUser;

    @Step("Login as {user.username} / {user.password}")
    public HomeScreen loginAs(SfUserModel user, String territory) {
        pinCodeScreen = new PinCodeScreen();
        loginScreen = new LoginScreen();

        if (null != activeSessionUser && !loginScreen.isLoginScreenDisplayed()) {
            if (activeSessionUser.getUsername().equals(user.getUsername())) {
                pinCodeScreen.enterDefaultPincode();
            } else {
                pinCodeScreen.clickLogout();
                fullLogin(user, territory);
            }
        } else {
            fullLogin(user, territory);
        }
        activeSessionUser = user;
        return new HomeScreen().waitUntilDeltaSyncFinished();
    }

    private void fullLogin(SfUserModel user, String territory) {
        loginScreen.loginAs(user.getUsername(), user.getPassword());
        if (pinCodeScreen.isPincodeScreenDisplayed()) {
            pinCodeScreen.enterDefaultPincode();
            pinCodeScreen.enterDefaultPincode();
            new SelectTerritoryScreen().selectTerritory(territory);
            pinCodeScreen.waitUntilSyncFinished().waitUntilDeltaSyncFinished();
        }
    }
}
