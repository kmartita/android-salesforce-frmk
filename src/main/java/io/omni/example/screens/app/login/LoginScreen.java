package io.omni.example.screens.app.login;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.screens.app.AbstractMainScreen;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import static io.omni.example.tools.ExpectedConditionTool.waitUntilVisible;
import static io.omni.example.tools.utilities.WaitUtils.waitForSeconds;

public class LoginScreen extends AbstractMainScreen {

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Click here for Sandbox login']")
    private WebElement sandboxButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Agree']")
    private WebElement agreePolicyButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username']")
    private WebElement usernameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='password']")
    private WebElement passField;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='Login']")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.view.View[@text='Allow Access?']")
    private WebElement allowAccessLabel;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='oaapprove']")
    private WebElement allowAccessButton;

    @Step("Check is 'Login' screen shown.")
    public boolean isLoginScreenDisplayed(){
        return mobileElementUtils().isElementShown(usernameField);
    }

    @Step("Login: username {username}; password {pass}.")
    public void loginAs(String username, String pass) {
        acceptPolicy();
        switchToSandbox();
        waitUntilVisible(usernameField).sendKeys(username);
        waitUntilVisible(passField).sendKeys(pass);
        loginButton.click();
        if (mobileElementUtils().isElementShown(allowAccessLabel)) {
            int counter = 0;
            while (!mobileElementUtils().isElementShown(allowAccessButton, 5) && counter < 3) {
                mobileElementUtils().defaultMidSwipeDown();
                counter++;
            }
            allowAccessButton.click();
        }
    }

    @Step("Accept policy.")
    public void acceptPolicy() {
        if (mobileElementUtils().isElementShown(agreePolicyButton)) {
            click(agreePolicyButton);
        }
    }

    @Step("Switch to sandbox.")
    public void switchToSandbox() {
        click(sandboxButton);
        waitForSeconds(1);
    }
}
