# file: Login.feature
Feature: Login Aplikasi
  Sebagai pengguna, saya ingin dapat login
  Sehingga saya dapat mengakses fitur utama aplikasi

  @ValidLogin
  Scenario: Login dengan kredensial yang valid
    Given User click menu login
    And Aplikasi sudah terbuka di halaman login
    When Saya memasukkan username "testing@gmail.com" dan password "pass_123"
    And Saya menekan tombol Login
    Then Saya seharusnya melihat halaman Beranda
