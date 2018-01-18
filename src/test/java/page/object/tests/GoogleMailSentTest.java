package page.object.tests;

import pageFactory.*;
import com.sun.org.glassfish.gmbal.Description;
import helpers.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class GoogleMailSentTest
{
	public final String BASE_URL = "https://www.google.by";
	private final String USERNAME1 = "OBVTest0";
	private final String USERNAME2 = "OBVTest1";
	private final String PASSWORD = "12345678OB";

	static WebDriver driver;
	StartPage startPage;
	LoginPage loginPage;
	DraftPage mailPage;
	SentPage sentPage;
	AccountInformationPopUp accountInformation;
	HelperMethods randomStringGeneratot = new HelperMethods();

	String address = randomStringGeneratot.generateString() + "@mail.ru";
	String subject = randomStringGeneratot.generateString();
	String body = randomStringGeneratot.generateString();

	@BeforeMethod
	public void precondition()
	{
		System.setProperty("webdriver.chrome.driver", "d:\\Install\\WebDriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		startPage = PageFactory.initElements(driver, StartPage.class);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		mailPage = PageFactory.initElements(driver, DraftPage.class);
		sentPage = PageFactory.initElements(driver, SentPage.class);
		accountInformation = PageFactory.initElements(driver, AccountInformationPopUp.class);

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
		mailPage.createDraftMail(address, subject, body);
		mailPage.openDraftPage();
		String createdMail = mailPage.getNewlyCreatedMailInDraft().getText();
		assertTrue(createdMail.contains(subject));
		mailPage.getNewlyCreatedMailInDraft().click();
		assertEquals(mailPage.actualAddress(), address);
		assertEquals(mailPage.actualSubject(), subject);
		assertEquals(mailPage.actualBody(), body);
	}

	@Test
	@Description("Send the latest draft mail")
	public void sendDraftMail() throws InterruptedException
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(USERNAME1, PASSWORD);
		startPage.openMailPage();
		mailPage.createDraftMail(address, subject, body);
		mailPage.openDraftPage();
		int draftMailsBefore = mailPage.getCountDraftMails();
		mailPage.sendDraftMail();
		int draftMailAfter = mailPage.getCountDraftMails();
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
		mailPage.createDraftMail(address, subject, body);
		mailPage.openDraftPage();
		mailPage.sendDraftMail();
		sentPage.openSentPage();
		sentPage.getFirstSentMail().click();
		String subjectSendingMail = sentPage.getsubjectSendingMail().getText();
		assertEquals(subjectSendingMail, subject);
		//mailPage.deleteSentMessage();
	}

	@Test
	@Description("log out")
	public void logOut()
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(USERNAME1, PASSWORD);
		accountInformation.logOut();
		assertTrue(startPage.getloginButton().isDisplayed());
	}

	@AfterMethod

	public void afterMethod()
	{

		driver.quit();

	}

}
