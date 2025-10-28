package com.latihan.android.automation.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // --- LOCATORS ---

    private final By EMAIL_INPUT = By.xpath("//android.widget.EditText[@content-desc='input-email']");
    private final By PASSWORD_INPUT = By.xpath("//android.widget.EditText[@content-desc='input-password']");
    private final By BUTTON_LOGIN = By.xpath("//android.view.ViewGroup[@content-desc='button-LOGIN']/android.view.ViewGroup");


    public void verifyLoginPageIsDisplayed() {
        waitForElementToBeVisible(EMAIL_INPUT);
    }

    public void enterCredentials(String username, String password) {
        inputText(EMAIL_INPUT, username);
        inputText(PASSWORD_INPUT, password);
    }

    public void tapLoginButton() {
        clickElement(BUTTON_LOGIN);
    }
}
