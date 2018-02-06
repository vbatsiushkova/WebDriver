package page.object.tests;

import com.sun.org.glassfish.gmbal.Description;
import helpers.Account;
import helpers.Browser;
import helpers.BrowserType;
import helpers.HelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pageFactory.*;

import java.io.File;
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

	public static WebDriver driver;
	//	static WebDriver driver;
	StartPage startPage;
	LoginPage loginPage;
	DraftPage draftPage;
	SentPage sentPage;
	InboxPage inboxPage;
	AccountInformationPopUp accountInformation;
	HelperMethods randomStringGeneratot = new HelperMethods();
	InboxPage promotionPage;

	String address = randomStringGeneratot.generateString() + "@mail.ru";
	String subject = randomStringGeneratot.generateString();
	String body = randomStringGeneratot.generateString();

	@BeforeSuite
	public void runHubAndNodes() throws IOException, InterruptedException
	{
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "RunGrid.bat");
		File dir = new File("D:\\!!!!MY_ Importent\\cdp\\WebDriver\\WebDriver\\src\\test\\java\\resources\\");
		pb.directory(dir);
		Process p = pb.start();
		Thread.sleep(1000);

	}

	@BeforeMethod
	@Parameters({ "browser" })
	public void precondition(@Optional(value = "CHROME") BrowserType browserType) throws MalformedURLException
	{
		driver = Browser.getDriver(browserType);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		startPage = PageFactory.initElements(driver, StartPage.class);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		draftPage = PageFactory.initElements(driver, DraftPage.class);
		sentPage = PageFactory.initElements(driver, SentPage.class);
		inboxPage = PageFactory.initElements(driver, InboxPage.class);
		accountInformation = PageFactory.initElements(driver, AccountInformationPopUp.class);
	}

	@Test
	@Description("Login to the MailBox ")
	public void openLoginPage()
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		String actualLoginName = accountInformation.checkUserAccount();
		assertTrue(actualLoginName.contains(Account.USERNAME1.toLowerCase()));
	}

	@Test
	@Description("Cretaed Mail is saved as draft")
	public void cretedDraftMail() throws InterruptedException
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		startPage.openMailPage();
		draftPage.createDraftMail(address, subject, body);
		draftPage.openPage();
		String createdMail = draftPage.getMail().getText();
		assertTrue(createdMail.contains(subject));
		draftPage.getMail().click();
		assertEquals(draftPage.actualAddress(), address);
		assertEquals(draftPage.actualSubject(), subject);
		assertEquals(draftPage.actualBody(), body);
	}

	@Test
	@Description("Send the latest draft mail")
	public void sendDraftMail() throws InterruptedException
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		startPage.openMailPage();
		draftPage.createDraftMail(address, subject, body);
		draftPage.openPage();
		int draftMailsBefore = draftPage.getCountDraftMails();
		draftPage.sendDraftMail();
		int draftMailAfter = draftPage.getCountDraftMails();
		assertEquals(draftMailAfter, draftMailsBefore - 1);

	}

	@Test
	@Description("Send the latest draft mail")
	public void mailIsSent() throws InterruptedException
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		startPage.openMailPage();
		draftPage.createDraftMail(address, subject, body);
		draftPage.openPage();
		draftPage.sendDraftMail();
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
	public void moveMailToSocialTab()
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		startPage.openInboxMailPage();
		String inboxMailPrimary = inboxPage.getBodyInboxMail();
		inboxPage.openContextMenu();
		inboxPage.openSocialTab();
		String inboxMailSocial = inboxPage.getTheLastSocialMail();
		assertEquals(inboxMailPrimary, inboxMailSocial);
	}

	@Test
	@Description("Move mail to promotions tab via drag and drop action")
	public void moveMailToPromotionsTab() throws InterruptedException
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		startPage.openInboxMailPage();
		String inboxMailPrimary = inboxPage.getBodyInboxMail();
		inboxPage.dragAnddropMailToPromotionsTab();
		inboxPage.openPromotionTab();
		String inboxMailPromotions = inboxPage.getTheLastSocialMail();
		assertEquals(inboxMailPromotions, inboxMailPrimary);
	}

	@Test
	@Description("log out")
	public void logOut()
	{
		startPage.openSite(BASE_URL);
		startPage.invokeSignIn();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		accountInformation.logOut();
		assertTrue(startPage.getloginButton().isDisplayed());
	}

	@AfterMethod

	public void afterMethod()
	{
		driver.quit();

	}

	@AfterSuite
	public void afterSuite() throws IOException
	{

	}

}
