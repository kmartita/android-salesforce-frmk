package io.omni.example.screens.app.login;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.screens.app.AbstractMainScreen;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class PinCodeScreen extends AbstractMainScreen {

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='1']")
    private WebElement oneButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Log out']")
    private WebElement logoutButton;

    @Step("Check is 'Pincode' screen shown.")
    public boolean isPincodeScreenDisplayed(){
        return mobileElementUtils().isElementShown(oneButton);
    }

    @Step("Click logout.")
    public void clickLogout() {
        click(logoutButton);
    }

    @Step("Enter default pincode.")
    public void enterDefaultPincode() {
        click(oneButton);
        click(oneButton);
        click(oneButton);
        click(oneButton);
    }
}
