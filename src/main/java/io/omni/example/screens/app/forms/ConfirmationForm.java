package io.omni.example.screens.app.forms;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.omni.example.screens.app.AbstractForm;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import java.util.function.Supplier;

public class ConfirmationForm extends AbstractForm {

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.view.View[contains(@content-desc,'?')]"),
            @AndroidBy(xpath = "//android.widget.ImageView[contains(@content-desc,'?')]")
    })
    private WebElement label;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Yes']"),
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Confirm']"),
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Ok']")
    })
    private WebElement confirmButton;

    @Step
    public <Screen> Screen confirmAction(Supplier<Screen> screen) {
        click(confirmButton);
        return screen.get();
    }

    @Step
    public String getConfirmationLabel() {
        return getValueFromElement(label);
    }

}
