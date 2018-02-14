@SmokeTest  @web
Feature: Created Draft Mail is Sent

  Scenario: I send Draft Mail
    Given user navigates to www.google.com home page
    When  click signIn button
    And   I am logged in as "OBVTest0@gmail.com"
    And  I open Inbox Mail Page from Start Page
    And I create a Draft Mail
    And Open Draft Mail Page
    And I check count of the Draft Mails
    And I send Draft Mail
    Then The Draft Mail is sent successfully and Draft mail count is changed
