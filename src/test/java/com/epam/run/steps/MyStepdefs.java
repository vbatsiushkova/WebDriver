package com.epam.run.steps;

import com.epam.run.cucumber.CucumberTestNGTest;
import com.epam.run.helpers.Account;
import com.epam.run.helpers.HelperMethods;
import com.epam.run.pageFactory.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Volha_Batsiushkova on 2/14/2018.
 */
public class MyStepdefs
{
	//private static WebDriver driver = Browser.getBrowser(BrowserType.CHROME);
	WebDriver driver = CucumberTestNGTest.beforeScenario();
	HelperMethods randomStringGeneratot = new HelperMethods();

	String address = randomStringGeneratot.generateString() + "@mail.ru";
	String subject = randomStringGeneratot.generateString();
	String body = randomStringGeneratot.generateString();

	StartPage startPage = new StartPage(driver);
	LoginPage loginPage = new LoginPage(driver);
	AccountInformationPopUp accountInformationPopUp = new AccountInformationPopUp(driver);
	InboxPage inboxPage = new InboxPage(driver);
	DraftPage draftPage =new DraftPage(driver);
	int previousValue;



	@Given("^user navigates to www.google.com home page$")
	public void userNavigatesToWwwGoogleComHomePage() throws Throwable
	{
		startPage.openPage();
	}

	@When("^click signIn button$")
	public void clickSignInButton() throws Throwable
	{
		 loginPage.openPage();
	}

	@And("^I am logged in as \"([^\"]*)\"$")
	public void iAmLoggedInAs(String user) throws Throwable
	{
		loginPage.signIn(user, Account.PASSWORD);
	}

	@Then("^I am logged in successfully$")
	public void iAmLoggedInSuccessfully() throws Throwable
	{
		accountInformationPopUp.openPage();
		String actualLoginName = accountInformationPopUp.checkUserAccount();
		assertTrue(actualLoginName.contains(Account.USERNAME1.toLowerCase()));
	}

	@And("^I open Inbox Mail Page from Start Page$")
	public void iOpenInboxMailPageFromStartPage() throws Throwable
	{
		startPage.openInboxMailPageFromStartPage();
	}

	@And("^I create a Draft Mail$")
	public void iCreateADraftMail() throws Throwable
	{
		inboxPage.createDraftMail(address, subject, body);
	}

	@Then("^Draft Mail is created successfully$")
	public void draftMailIsCreatedSuccessfully() throws Throwable
	{
		String createdMail = draftPage.getMail().getText();
		assertTrue(createdMail.contains(subject));
		draftPage.getMail().click();
		draftPage.getMail().click();
		assertEquals(draftPage.actualAddress(), address);
		assertEquals(draftPage.actualSubject(), subject);
		assertEquals(draftPage.actualBody(), body);
	}

	@And("^Open Draft Mail Page$")
	public void openDraftMailPage() throws Throwable
	{
		draftPage.openPage();
	}

	@And("^I send Draft Mail$")
	public void iSendDraftMail() throws Throwable
	{
		draftPage.sendDraftMail();
	}

	@And("^I check count of the Draft Mails$")
	public void iCheckCountOfTheDraftMails() throws Throwable
	{
		previousValue = draftPage.getCountDraftMails();
	}

	@Then("^The Draft Mail is sent successfully and Draft mail count is changed$")
	public void theDraftMailIsSentSuccessfullyAndDraftMailCountIsChanged() throws Throwable
	{
		int draftMailAfter = draftPage.getCountDraftMails();
		assertEquals(draftMailAfter, previousValue - 1);
	}
}
