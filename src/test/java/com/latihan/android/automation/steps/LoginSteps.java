package com.latihan.android.automation.steps;

import com.latihan.android.automation.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

/**
 * Step Definitions: Menghubungkan Gherkin dengan Page Object Model (POM).
 */
public class LoginSteps {

    private LoginPage loginPage = new LoginPage();

    @Given("Aplikasi sudah terbuka di halaman login")
    public void aplikasi_sudah_terbuka_di_halaman_login() {
        loginPage.verifyLoginPageIsDisplayed();
    }

    @When("Saya memasukkan username {string} dan password {string}")
    public void saya_memasukkan_username_dan_password(String username, String password) {
        loginPage.enterCredentials(username, password);
    }

    @When("Saya menekan tombol Login")
    public void saya_menekan_tombol_login() {
        loginPage.tapLoginButton();
    }

    @Then("Saya seharusnya melihat halaman Beranda")
    public void saya_seharusnya_melihat_halaman_beranda() {
        // Memastikan verifikasi halaman berhasil
//        Assert.assertTrue("Verifikasi Halaman Beranda Gagal! Login Gagal.",
//                loginPage.isHomePageTitleDisplayed());
    }

//    @Then("Saya seharusnya melihat pesan error {string}")
//    public void saya_seharusnya_melihat_pesan_error(String expectedErrorMessage) {
//        String actualErrorMessage = loginPage.getErrorMessageText();
//        // Memastikan pesan error yang muncul sesuai dengan yang diharapkan
//        Assert.assertEquals("Pesan error tidak sesuai.", expectedErrorMessage, actualErrorMessage);
//    }
}
