package com.latihan.android.automation.config;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;

//
///**
// * Kelas DriverFactory: Bertanggung jawab untuk inisialisasi dan penutupan Appium Driver.
// * Konfigurasi (Capabilities) sekarang dimuat dari file src/test/resources/config.properties.
// */
public class DriverFactory {

    // Lokasi file konfigurasi, diasumsikan berada di bawah src/test/resources
    private static final String CONFIG_FILE = "config.properties";

    // Gunakan ThreadLocal untuk memastikan driver unik per thread (untuk parallel testing)
    private static AndroidDriver driver;
    private static Properties properties;

    /**
     * Memuat file konfigurasi Appium (config.properties) hanya sekali.
     */
    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            // Menggunakan try-with-resources untuk memastikan FileInputStream ditutup
            try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
                properties.load(input);
                System.out.println("Konfigurasi Appium berhasil dimuat dari: " + CONFIG_FILE);
            } catch (IOException ex) {
                System.err.println("ERROR: Gagal memuat file properties: " + CONFIG_FILE);
                ex.printStackTrace();
                throw new RuntimeException("Gagal membaca konfigurasi Appium. Pastikan file ada dan path benar.", ex);
            }
        }
    }

    /**
     * Mengembalikan instance driver saat ini.
     * @return AppiumDriver<MobileElement>
     */
    public static AndroidDriver getDriver() {
//        initializeDriver();
        return driver;
    }

    /**
     * Menginisialisasi driver Appium baru menggunakan Desired Capabilities dari config.properties.
     */
    public static void initializeDriver() {
        loadProperties(); // Panggil method untuk memuat konfigurasi

        String appiumServerUrl = properties.getProperty("appium.url");
        // Mengambil timeout dan mengkonversinya ke integer. Default: 15 detik.
        int timeoutSecs = Integer.parseInt(properties.getProperty("appium.timeout", "15"));

        try {

            String appPath = properties.getProperty("app.path");

// 2. Konversi relative path menjadi absolute path
// Ini penting agar Appium Server dapat menemukan file APK dengan pasti di sistem
            String currentDir = System.getProperty("user.dir");
            String absoluteAppPath = currentDir+appPath;

            // 1. Definisikan Desired Capabilities
            DesiredCapabilities caps = new DesiredCapabilities();

            // Capabilities Utama
            caps.setCapability("platformName", properties.getProperty("platformName"));
            caps.setCapability("appium:platformVersion", properties.getProperty("platformVersion"));
            caps.setCapability("appium:deviceName", properties.getProperty("deviceName"));
            caps.setCapability("appium:automationName", properties.getProperty("automationName"));

            // Konfigurasi Aplikasi
            caps.setCapability("appium:app", properties.getProperty("app.path"));
            caps.setCapability("appium:app", absoluteAppPath);

            caps.setCapability("appium:appPackage", properties.getProperty("appPackage"));
            caps.setCapability("appium:appActivity", properties.getProperty("appActivity"));

            // Konfigurasi Reset dan Izin (Mengambil nilai String dan mengkonversi ke Boolean)
            caps.setCapability("appium:noReset", Boolean.parseBoolean(properties.getProperty("noReset")));
            caps.setCapability("appium:fullReset", Boolean.parseBoolean(properties.getProperty("fullReset")));
            caps.setCapability("appium:autoGrantPermissions", Boolean.parseBoolean(properties.getProperty("autoGrantPermissions")));

            // Konfigurasi Keyboard
            caps.setCapability("appium:unicodeKeyboard", Boolean.parseBoolean(properties.getProperty("unicodeKeyboard")));
            caps.setCapability("appium:resetKeyboard", Boolean.parseBoolean(properties.getProperty("resetKeyboard")));

//            AndroidDriver driver = new AndroidDriver(new URL(appiumServerUrl), caps);

//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


            // 2. Inisialisasi Android Driver dengan URL dan Capabilities yang dimuat
//            AndroidDriver
            driver = new AndroidDriver(new URL(appiumServerUrl), caps);

            // 3. Set Implicit Wait
//            driver.manage().timeouts().implicitlyWait(timeoutSecs, TimeUnit.SECONDS);
            Duration EXPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(timeoutSecs);
            driver.manage().timeouts().implicitlyWait(EXPLICIT_WAIT_TIMEOUT);
            // 4. Simpan driver ke ThreadLocal
//            driverThread = (driver);
            System.out.println("Driver Appium berhasil diinisialisasi.");

        } catch (Exception e) {
            System.err.println("Gagal menginisialisasi Appium Driver: " + e.getMessage());
            // Melemparkan pengecualian runtime agar eksekusi berhenti jika terjadi kegagalan fatal
            throw new RuntimeException("Gagal memulai sesi Appium. Cek koneksi server dan konfigurasi.", e);
        }
    }

    /**
     * Menutup driver dan menghapus dari ThreadLocal.
     */
    public static void quitDriver() {
//        AndroidDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
//            driverThread.remove();
            System.out.println("Driver Appium berhasil dimatikan.");
        }
    }
}


//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import io.appium.java_client.android.AndroidDriver;
//
//public class DriverFactory {
//    public static AndroidDriver getDriver() throws MalformedURLException{
//        AndroidDriver driver;
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("appium:platformVersion", "11.0");
//        capabilities.setCapability("appium:deviceName", "emulator-5554");
//        capabilities.setCapability("appium:app", "D:/zul/android/android.wdio.native.app.v1.0.8.apk");
//        capabilities.setCapability("appium:automationName", "UiAutomator2");
//        capabilities.setCapability("appium:appPackage", "com.wdiodemoapp");
//        capabilities.setCapability("appium:appActivity", "com.wdiodemoapp/.MainActivity");
//        capabilities.setCapability("appium:noReset", false);
//        capabilities.setCapability("appium:fullReset", false);
//        capabilities.setCapability("appium:autoGrantPermissions", true);
//        capabilities.setCapability("appium:unicodeKeyboard", true);
//        capabilities.setCapability("appium:resetKeyboard", true);
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        return driver;
//    }
//}


//
//package com.latihan.android.automation.config;
//
//import com.latihan.android.automation.config.ConfigReader;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.options.UiAutomator2Options;
//import java.net.URL;
//import java.time.Duration;
//
//public class DriverFactory {
//    private static AndroidDriver driver;
//    private static ConfigReader config = new ConfigReader();
//
//    public static AndroidDriver getDriver() {
//        if (driver == null) {
//            try {
//                UiAutomator2Options options = new UiAutomator2Options()
//                        .setPlatformName(config.get("platformName"))
//                        .setPlatformVersion(config.get("platformVersion"))
//                        .setDeviceName(config.get("deviceName"))
//                        .setAutomationName(config.get("automationName"))
//                        .setApp(config.get("app.path"))
//                        .setAppPackage(config.get("appPackage"))
//                        .setAppActivity(config.get("appActivity"))
//                        .setNoReset(Boolean.parseBoolean(config.get("noReset")))
//                        .setFullReset(Boolean.parseBoolean(config.get("fullReset")))
//                        .setAutoGrantPermissions(Boolean.parseBoolean(config.get("autoGrantPermissions")));
////                        .setUnicodeKeyboard(Boolean.parseBoolean(config.get("unicodeKeyboard")))
////                        .setResetKeyboard(Boolean.parseBoolean(config.get("resetKeyboard")));
//
//                URL url = new URL(config.get("appium.url"));
//                driver = new AndroidDriver(url, options);
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//            } catch (Exception e) {
//                throw new RuntimeException("Failed to initialize driver", e);
//            }
//        }
//        return driver;
//    }
//
//    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
//}