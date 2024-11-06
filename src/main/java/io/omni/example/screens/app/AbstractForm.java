package io.omni.example.screens.app;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.omni.example.tools.api.HasLabel;
import io.omni.example.tools.api.HasName;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

import static java.lang.String.format;

public abstract class AbstractForm extends AbstractAppUI {

    protected static final String FIELD = "Field";

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Save']"),
            @AndroidBy(xpath = "//android.view.View[@content-desc='Save']"),
            @AndroidBy(xpath = "//android.view.View[@content-desc='Save Button']")
    })
    private WebElement saveButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Next']")
    private WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Create']")
    private WebElement createButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Cancel']"),
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Cancel Button']")
    })
    private WebElement cancelButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Done']")
    private WebElement doneButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Close']"),
            @AndroidBy(xpath = "//android.view.View[@content-desc='Close Button']"),
            @AndroidBy(xpath = "//android.view.View[ends-with(@content-desc,'Details')]/following-sibling::android.widget.Button[not(@content-desc)]")
    })
    private WebElement closeButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Submit']")
    private WebElement submitButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Confirm']")
    private WebElement confirmButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Save as...']")
    private WebElement saveAsButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Delete']"),
            @AndroidBy(xpath = "//android.widget.Button[ends-with(@content-desc,'Delete')]")
    })
    protected WebElement deleteButton;

    protected <Field extends Enum<Field> & HasName & HasLabel> String getFieldLocator(Field field) {
        return format("//*[contains(@content-desc,'%s')]", field.getName() + format("%s%s", StringUtils.SPACE, FIELD));
    }

    protected <Field extends Enum<Field> & HasName & HasLabel> WebElement getFieldElement(Field field) {
        return driver.findElement(By.xpath(getFieldLocator(field)));
    }

    @Step("Click 'Save as...' button.")
    public void clickSaveAs() { click(saveAsButton); }

    @Step("Click 'Save' button.")
    public void clickSave() { click(saveButton); }

    @Step("Click 'Next' button.")
    public void clickNext() { click(nextButton); }

    @Step("Click 'Create' button.")
    public void clickCreate() { click(createButton); }

    @Step("Click 'Cancel' button.")
    public void clickCancel() { click(cancelButton); }

    @Step("Click 'Done' button.")
    public void clickDone() { click(doneButton); }

    @Step("Click 'Close' button.")
    public void clickClose() { click(closeButton); }

    @Step("Click 'Submit' button.")
    public void clickSubmit() { click(submitButton); }

    @Step("Click 'Confirm' button.")
    public void clickConfirm() { click(confirmButton); }

    @Step("Click not enabled 'Submit' button.")
    public void clickNotEnabledSubmitButton() {
        if (!isSubmitButtonActive()) {
            tap(submitButton.getLocation());
        }
    }

    @Step("Click 'Delete' button.")
    public void clickDelete() { click(deleteButton); }

    @Step("Try to click 'Delete' button.")
    public void tryToClickDelete() {
        deleteButton.click();
    }

    @Step("Check if 'Save' button is active.")
    public boolean isSaveButtonActive(){ return mobileElementUtils().isElementEnabled(saveButton); }

    @Step("Check if 'Submit' button is active.")
    public boolean isSubmitButtonActive(){ return mobileElementUtils().isElementEnabled(submitButton); }

    @Step("Check if 'Delete' button is active.")
    public boolean isDeleteButtonActive() { return mobileElementUtils().isElementEnabled(deleteButton); }


    @Step("Check if 'Cancel' button is clickable.")
    public boolean isCancelButtonAllowedToClick(){ return mobileElementUtils().isElementClickableByAttribute(cancelButton); }

    @Step("Check if 'Cancel' button is clickable.")
    public boolean isCancelButtonClickable(){ return mobileElementUtils().isElementClickable(cancelButton); }

    @Step("Check if 'Create' button is displayed.")
    public boolean isCreateButtonDisplayed(){ return mobileElementUtils().isElementShown(createButton); }

    @Step("Check if 'Next' button is displayed.")
    public boolean isNextButtonDisplayed(){ return mobileElementUtils().isElementShown(nextButton); }

    @Step("Check if 'Cancel' button is displayed.")
    public boolean isCancelButtonDisplayed(){ return mobileElementUtils().isElementShown(cancelButton); }

    @Step("Check if 'Save' button is displayed.")
    public boolean isSaveButtonDisplayed(){ return mobileElementUtils().isElementShown(saveButton); }

    @Step("Check if 'Delete' button is displayed.")
    public boolean isDeleteButtonDisplayed(){ return mobileElementUtils().isElementShown(deleteButton); }

    @Step("Check if 'Submit' button is displayed.")
    public boolean isSubmitButtonDisplayed(){ return mobileElementUtils().isElementShown(submitButton); }

    @Step("Check is field {field} displayed.")
    public <Field extends Enum<Field> & HasName & HasLabel> boolean isFieldDisplayed(Field field){
        return mobileElementUtils().isElementShown(getFieldElement(field));
    }

    @Step("Get the 'Cancel' button position and size.")
    public Rectangle getRectForCancelButton(){ return cancelButton.getRect(); }

    @Step("Perform cancel action.")
    public <Screen> Screen cancel(Supplier<Screen> pageSupplier) {
        clickCancel();
        return pageSupplier.get();
    }

    @Step("Perform confirm action.")
    public <Screen> Screen confirm(Supplier<Screen> pageSupplier) {
        clickConfirm();
        return pageSupplier.get();
    }

    @Step("Perform save action.")
    public <Screen> Screen save(Supplier<Screen> pageSupplier) {
        clickSave();
        return pageSupplier.get();
    }

    @Step("Perform close action.")
    public <Screen> Screen close(Supplier<Screen> pageSupplier) {
        clickClose();
        return pageSupplier.get();
    }
}
