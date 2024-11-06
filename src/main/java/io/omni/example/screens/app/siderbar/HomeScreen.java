package io.omni.example.screens.app.siderbar;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.screens.app.AbstractMainScreen;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HomeScreen extends AbstractMainScreen {

    @AndroidFindBy(xpath = "//android.view.View[@content-desc = 'Home Page']")
    private WebElement homeScreenTitle;

    @Step
    public boolean isHomeScreenOpened() {
        return isElementDisplayed(homeScreenTitle);
    }
}
