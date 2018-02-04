package page.object.tests;

import com.sun.org.glassfish.gmbal.Description;
import helpers.Account;
import helpers.Browser;
import helpers.BrowserType;
import helpers.HelperMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pageFactory.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class GoogleMailSentTest
{
	public final String BASE_URL = "https://www.google.by";

	public static RemoteWebDriver driver;
	//	static WebDriver driver;
	StartPage startPage;
	LoginPage loginPage;
	DraftPage mailPage;
	SentPage sentPage;
	InboxPage inboxPage;
	AccountInformationPopUp accountInformation;
	HelperMethods randomStringGeneratot = new HelperMethods();
	InboxPage promotinPage;

	String address = randomStringGeneratot.generateString() + "@mail.ru";
	String subject = randomStringGeneratot.generateString();
	String body = randomStringGeneratot.generateString();

	@BeforeSuite
	public void runHubAndNodes() throws IOException
	{

	}

		@BeforeMethod
		@Parameters({ "browser" })
		public void precondition (@Optional(value = "CHROME") BrowserType browser) throws MalformedURLException
		{
			driver = Browser.getDriver(browser);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			startPage = PageFactory.initElements(driver, StartPage.class);
			loginPage = PageFactory.initElements(driver, LoginPage.class);
			mailPage = PageFactory.initElements(driver, DraftPage.class);
			sentPage = PageFactory.initElements(driver, SentPage.class);
			inboxPage = PageFactory.initElements(driver, InboxPage.class);
			accountInformation = PageFactory.initElements(driver, AccountInformationPopUp.class);
			promotinPage = PageFactory.initElements(driver, PromotionTabPage.class);

		}

		@Test
		@Description("Login to the MailBox ")
		public void openLoginPage ()
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(Account.USERNAME1,Account.PASSWORD);
			String actualLoginName = accountInformation.checkUserAccount();
			assertTrue(actualLoginName.contains(Account.USERNAME1.toLowerCase()));
		}

		@Test
		@Description("Cretaed Mail is saved as draft")
		public void cretedDraftMail () throws InterruptedException
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(Account.USERNAME1,Account.PASSWORD);
			startPage.openMailPage();
			mailPage.createDraftMail(address, subject, body);
			mailPage.openPage();
			String createdMail = mailPage.getMail().getText();
			assertTrue(createdMail.contains(subject));
			mailPage.getMail().click();
			assertEquals(mailPage.actualAddress(), address);
			assertEquals(mailPage.actualSubject(), subject);
			assertEquals(mailPage.actualBody(), body);
		}

		@Test
		@Description("Send the latest draft mail")
		public void sendDraftMail () throws InterruptedException
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(Account.USERNAME1,Account.PASSWORD);
			startPage.openMailPage();
			mailPage.createDraftMail(address, subject, body);
			mailPage.openPage();
			int draftMailsBefore = mailPage.getCountDraftMails();
			mailPage.sendDraftMail();
			int draftMailAfter = mailPage.getCountDraftMails();
			assertEquals(draftMailAfter, draftMailsBefore - 1);

		}

		@Test
		@Description("Send the latest draft mail")
		public void mailIsSent () throws InterruptedException
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(Account.USERNAME1,Account.PASSWORD);
			startPage.openMailPage();
			mailPage.createDraftMail(address, subject, body);
			mailPage.openPage();
			mailPage.sendDraftMail();
			sentPage.openPage();
			sentPage.getMail().click();
			WebElement subjectSendingMailElement = sentPage.getsubjectSendingMail();
			String subjectSendingMail = subjectSendingMailElement.getText();
			assertEquals(subjectSendingMail, subject);
			sentPage.deleteSentMessage();
			assertTrue(!subjectSendingMailElement.isDisplayed());
		}

		@Test
		@Description("Move mail to social tab")
		public void moveMailToSocialTab ()
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(Account.USERNAME1,Account.PASSWORD);
			startPage.openInboxMailPage();
			String inboxMailPrimary = inboxPage.getBodyInboxMail();
			inboxPage.openContextMenu();

			String inboxMailSocial = inboxPage.getTheLastSocialMail();
			assertEquals(inboxMailPrimary, inboxMailSocial);
		}

		@Test
		@Description("Move mail to promotions tab via drag and drop action")
		public void moveMailToPromotionsTab () throws InterruptedException
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(Account.USERNAME1,Account.PASSWORD);
			startPage.openInboxMailPage();
			String inboxMailPrimary = inboxPage.getBodyInboxMail();
			inboxPage.dragAnddropMailToPromotionsTab();
			promotinPage.openPage();
			String inboxMailPromotions = inboxPage.getTheLastSocialMail();
			assertEquals(inboxMailPromotions, inboxMailPrimary);
		}

		@Test
		@Description("log out")
		public void logOut ()
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(Account.USERNAME1,Account.PASSWORD);
			accountInformation.logOut();
			assertTrue(startPage.getloginButton().isDisplayed());
		}

		@AfterMethod

		public void afterMethod ()
		{
			driver.quit();

		}

	}
