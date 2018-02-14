@SmokeTest  @web
Feature: Login to gmail


  Scenario: Login to google gmail

    Given user navigates to www.google.com home page
    When  click signIn button
    And   I am logged in as "OBVTest0@gmail.com"
    Then I am logged in successfully




