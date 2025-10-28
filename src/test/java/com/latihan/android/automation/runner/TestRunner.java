package com.latihan.android.automation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Kelas ini adalah entry point untuk menjalankan test Cucumber menggunakan JUnit.
 * Lokasi 'glue' telah disesuaikan dengan package config dan steps.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        // 1. Lokasi Feature Files
        features = "src/test/resources/features",

        // 2. Lokasi Step Definitions (Glue Path)
        glue = {"com.latihan.android.automation.steps", "com.latihan.android.automation.config"},

        // 3. Konfigurasi Laporan
        plugin = {"pretty", "html:target/cucumber-reports/report.html", "json:target/cucumber-reports/report.json"},

        // 4. Konfigurasi Tag
        // tags = "@ValidLogin or @InvalidLogin",

        // 5. Opsi Tambahan
        monochrome = true,
        dryRun = false
)
public class TestRunner {
}
