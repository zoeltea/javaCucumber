package com.latihan.android.automation.pages;

import com.latihan.android.automation.config.DriverFactory;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected AndroidDriver driver;

    private final Duration EXPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(20);

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
        try {
            return (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Elemen tidak ditemukan setelah " + EXPLICIT_WAIT_TIMEOUT.getSeconds() + " detik: " + locator.toString());
            return null; 
        }
    }

    protected void inputText(By locator, String text) {
        WebElement targetElement = waitForElementToBeVisible(locator);
        targetElement.sendKeys(text);
    }

    protected void clickElement(By locator) {
        WebElement targetElement = waitForElementToBeVisible(locator);
        targetElement.click();
    }

    protected String getElementText(By locator) {
        WebElement targetElement = waitForElementToBeVisible(locator);
        return targetElement.getText();
    }

}
