# file: Login.feature
Feature: Login Aplikasi
  Sebagai pengguna, saya ingin dapat login
  Sehingga saya dapat mengakses fitur utama aplikasi

  @Home
  Scenario: User melihat homepage
    Given User on home page
    Then User click menu login
    And User click menu home
    Then User click menu login
    And User click menu home
