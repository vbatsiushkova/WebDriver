package page.object.tests;

import com.sun.org.glassfish.gmbal.Description;
import helpers.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
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


	public static RemoteWebDriver driver;
	//	static WebDriver driver;
	StartPage startPage;
	LoginPage loginPage;
	DraftPage draftPage;
	SentPage sentPage;
	InboxPage inboxPage;
	AccountInformationPopUp accountInformation;
	HelperMethods randomStringGeneratot = new HelperMethods();

	String address = randomStringGeneratot.generateString() + "@mail.ru";
	String subject = randomStringGeneratot.generateString();
	String body = randomStringGeneratot.generateString();

	@BeforeSuite
	public void runHubAndNodes() throws IOException, InterruptedException
	{
//		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "RunGrid.bat");
//		File dir = new File("D:\\!!!!MY_ Importent\\cdp\\WebDriver\\WebDriver\\src\\test\\java\\resources\\");
//		pb.directory(dir);
//		Process p = pb.start();
//		Thread.sleep(1000);

	}

	@BeforeMethod
	@Parameters({ "browser" })
	public void precondition(@Optional(value = "CHROME") BrowserType browserType) throws MalformedURLException
	{
		driver = Browser.getDriver(browserType);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		startPage = (StartPage) FactoryPages.getPage(Page.START_PAGE, driver);
		loginPage = (LoginPage) FactoryPages.getPage(Page.LOGIN_PAGE, driver);
        draftPage = (DraftPage) FactoryPages.getPage(Page.DRAFT_PAGE, driver);
		sentPage = (SentPage) FactoryPages.getPage(Page.SENT_PAGE, driver);
		inboxPage = (InboxPage) FactoryPages.getPage(Page.INBOX_PAGE, driver);
		accountInformation = (AccountInformationPopUp) FactoryPages.getPage(Page.ACCOUNT_POP_UP, driver);
	}

	@Test
	@Description("Login to the MailBox ")
	public void openLoginPage() throws InterruptedException
	{
		startPage.openPage();
		loginPage.openPage();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		String actualLoginName = accountInformation.checkUserAccount();
		assertTrue(actualLoginName.contains(Account.USERNAME1.toLowerCase()));
	}

	@Test
	@Description("Cretaed Mail is saved as draft")
	public void cretedDraftMail() throws InterruptedException
	{
		startPage.openPage();
		loginPage.openPage();  //нет доступа
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		startPage.openInboxMailPage();
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
		startPage.openPage();
		loginPage.openPage();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		startPage.openInboxMailPage();
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
		startPage.openPage();
		loginPage.openPage();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		startPage.openInboxMailPage();
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
	public void moveMailToSocialTab() throws InterruptedException
	{
		startPage.openPage();
		loginPage.openPage();
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
		startPage.openPage();
		loginPage.openPage();
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
	public void logOut() throws InterruptedException
	{
		startPage.openPage();
		loginPage.openPage();
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
