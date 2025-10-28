package com.latihan.android.automation.pages;
import org.openqa.selenium.By;


public class HomePage extends  BasePage{
    // Locator
    private final By HOME_TEXT = By.xpath("//android.widget.TextView[@text='Home']");
    private final By LOGIN_TEXT = By.xpath("//android.widget.TextView[@text='Login']");

    public void isHomeTextDisplayed() {
        waitForElementToBeVisible(HOME_TEXT);
    }
    public void clickMenuLogin() {
        waitForElementToBeVisible(LOGIN_TEXT);
        clickElement(LOGIN_TEXT);
    }

    public void clickHomeText() {
        clickElement(HOME_TEXT);
    }
}