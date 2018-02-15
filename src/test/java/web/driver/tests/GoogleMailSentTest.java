package web.driver.tests;

import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import web.driver.helpers.*;
import web.driver.pageFactory.*;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class GoogleMailSentTest
{


	public static WebDriver driver;
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
	PageGenerator page = new PageGenerator();

	@BeforeMethod
	@Parameters({ "CHROME" })
	public void precondition(@Optional(value = "CHROME") BrowserType browserType) throws MalformedURLException
	{
		driver = Browser.getBrowser(browserType);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		startPage = (StartPage) page.createPage(Page.START_PAGE, driver);
		loginPage = (LoginPage) page.createPage(Page.LOGIN_PAGE, driver);
		draftPage = (DraftPage) page.createPage(Page.DRAFT_PAGE, driver);
		sentPage = (SentPage) page.createPage(Page.SENT_PAGE, driver);
		inboxPage = (InboxPage) page.createPage(Page.INBOX_PAGE, driver);
		accountInformation = (AccountInformationPopUp) page.createPage(Page.ACCOUNT_POP_UP, driver);
	}

	@Test
	@Description("Login to the MailBox ")
	public void openLoginPage() throws InterruptedException
	{
		startPage.openPage();
		loginPage.openPage();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		accountInformation.openPage();
		String actualLoginName = accountInformation.checkUserAccount();
		assertTrue(actualLoginName.contains(Account.USERNAME1.toLowerCase()));
	}

	@Test
	@Description("Cretaed Mail is saved as draft")
	public void cretedDraftMail() throws InterruptedException
	{
		startPage.openPage();
		loginPage.openPage();
		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
		startPage.openInboxMailPageFromStartPage();
        inboxPage.createDraftMail(address, subject, body);
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
		startPage.openInboxMailPageFromStartPage();
		inboxPage.createDraftMail(address, subject, body);
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
		startPage.openInboxMailPageFromStartPage();
		inboxPage.createDraftMail(address, subject, body);
		draftPage.openPage();
		String sendingDraftBody = draftPage.getMail().getText();
		draftPage.sendDraftMail();
		sentPage.openPage();
		sentPage.getMail().click();
		WebElement subjectSendingMailElement = sentPage.getsubjectSendingMail();
		String subjectSendingMail = subjectSendingMailElement.getText();
		assertEquals(sendingDraftBody, subjectSendingMail);
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
		startPage.openInboxMailPageFromStartPage();
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
		startPage.openInboxMailPageFromStartPage();
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

}
