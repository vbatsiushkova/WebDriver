package com.epam.automation.Batsiushkova_Olga.pages;

import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class TestExample
{
	public  final String BASE_URL = "https://www.google.by";
	private final String USERNAME1 = "OBVTest0";
	private final String USERNAME2 = "OBVTest1";
	private final String PASSWORD = "12345678OB";
	private final String ADDRESS="OBVTest0@gmail.ru" ;
	private final String SUBJECT ="Test" ;
	private final String BODY="BODY";

	static WebDriver driver;
	StartPage startPage;
	LoginPage loginPage;
	MailPage mailPage;
	AccountInformationPopUp accountInformation;





	

	@BeforeMethod
	public void precondition()
	{
		System.setProperty("webdriver.chrome.driver", "d:\\Install\\WebDriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver();
		startPage = PageFactory.initElements(driver, StartPage.class);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		mailPage = PageFactory.initElements(driver, MailPage.class);
		accountInformation=PageFactory.initElements(driver, AccountInformationPopUp.class);

	}

	@Test
	@Description("Login to the MailBox ")
	public void openLoginPage()
	{
		startPage.openSite(BASE_URL);
        startPage.invokeSignIn();
		loginPage.signIn(USERNAME1, PASSWORD);
        String actualLoginName = accountInformation.checkUserAccount();
		assertTrue(actualLoginName.contains(USERNAME1.toLowerCase()));

	}

	@Test
	@Description("Cretaed Mail is saved as draft")
	public void cretedDraftMail() throws InterruptedException
	{

		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(USERNAME1, PASSWORD);
		startPage.openMailPage();
      	mailPage.createDraftMail(ADDRESS, SUBJECT, BODY);
		mailPage.openDraftPage();
		Thread.sleep(2000);
		String createdMail = mailPage.getBodyCreatedAttribute().getText();
       assertTrue(createdMail.contains(SUBJECT));
		assertEquals(mailPage.actualAddress(), ADDRESS);
		//assertEquals(mailPage.actualSubject(), SUBJECT);
		assertEquals(mailPage.actualBody(), BODY);
	}

	@Test
	@Description("Send the latest draft mail")
	public void sendDraftMail() throws InterruptedException
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(USERNAME1, PASSWORD);
		startPage.openMailPage();
		mailPage.openDraftPage();
		int draftMailsBefore = mailPage.getListSizeMails();
		mailPage.sendDraftMail();
		int draftMailAfter = mailPage.getListSizeMails();
		assertEquals(draftMailAfter, draftMailsBefore - 1);

	}

	@Test
	@Description("Send the latest draft mail")
	public void mailIsSent() throws InterruptedException
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(USERNAME1, PASSWORD);
		startPage.openMailPage();
		mailPage.openSentPage();
		int sentMailsBefore = mailPage.getListSizeMails();
		mailPage.openDraftPage();
		mailPage.sendDraftMail();
		mailPage.openSentPage();
		int sentMailsAfter = mailPage.getListSizeMails();
		assertEquals(sentMailsAfter,sentMailsBefore);
	}


	@AfterMethod

	public void afterMethod() {

		driver.quit();

	}

}
