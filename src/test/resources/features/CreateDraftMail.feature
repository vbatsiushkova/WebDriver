@SmokeTest  @web
Feature: Create Draft Mail  with google account

  Scenario: Create Draft Mail
    Given user navigates to www.google.com home page
    When  click signIn button
    And   I am logged in as "OBVTest0@gmail.com"
    And  I open Inbox Mail Page from Start Page
    And I create a Draft Mail
    And Open Draft Mail Page
    Then Draft Mail is created successfully