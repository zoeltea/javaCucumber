package com.latihan.android.automation.steps;

import com.latihan.android.automation.config.DriverFactory;
import com.latihan.android.automation.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomeSteps {

    private HomePage homePage = new HomePage();
    @Given("User on home page")
    public void userOnHomePage() {
        homePage.isHomeTextDisplayed();
        // Tidak perlu aksi khusus karena aplikasi sudah terbuka
    }

    @And("User click menu login")
    public void userClickMenuLogin() {
        homePage.clickMenuLogin();
//        try {
//            // Baris 22:
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // Mengatur ulang status interrupt
//        }
//        // Tidak perlu aksi khusus karena aplikasi sudah terbuka
    }
    @And("User click menu home")
    public void userClickMenuHome() {
        homePage.clickHomeText();
//        try {
//            // Baris 22:
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // Mengatur ulang status interrupt
//        }
//        // Tidak perlu aksi khusus karena aplikasi sudah terbuka
    }

    @Then("User should see home text")
    public void userShouldSeeHomeText() {
        homePage.isHomeTextDisplayed();
//        Assertions.assertTrue(homePage.isHomeTextDisplayed(), "Home text is not visible");
    }
}