package page.object.tests;

import com.sun.org.glassfish.gmbal.Description;
import helpers.Account;
import helpers.Browser;
import helpers.HelperMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pageFactory.*;

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
	Account account = new Account();

	public static RemoteWebDriver driver;
	//	static WebDriver driver;
	StartPage startPage;
	LoginPage loginPage;
	DraftPage mailPage;
	SentPage sentPage;
	InboxPage inboxPage;
	AccountInformationPopUp accountInformation;
	HelperMethods randomStringGeneratot = new HelperMethods();

	String address = randomStringGeneratot.generateString() + "@mail.ru";
	String subject = randomStringGeneratot.generateString();
	String body = randomStringGeneratot.generateString();

//	@BeforeSuite
//	public void runHubAndNodes() throws IOException
//	{
//		List<String> path = Browser.pathBatFileList();
//		path.forEach(c->{
//			try {
//				String[] command = {c};
//				Process p =  Runtime.getRuntime().exec(command);
//			} catch (IOException ex) {
//			}
//		});
//	}

		@BeforeMethod
		@Parameters({ "browser" })
		public void precondition (@Optional(value = "chrome") String browser) throws MalformedURLException
		{
//		System.setProperty("webdriver.chrome.driver", "d:\\Install\\WebDriver\\chromedriver.exe");
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		MutableCapabilities chromeOptions = new ChromeOptions();
//		MutableCapabilities firefoxOptions=new FirefoxOptions();
//		chromeOptions.setCapability("platformName",Platform.WINDOWS);
//
//		try{
//			driver = new RemoteWebDriver(new URL("http://localhost:4443/wd/hub"), chromeOptions);
//		}catch(MalformedURLException e){
//			e.printStackTrace();
//		}
//
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("start-maximized");
			//driver = new ChromeDriver();

			driver = Browser.getDriver(browser);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			startPage = PageFactory.initElements(driver, StartPage.class);
			loginPage = PageFactory.initElements(driver, LoginPage.class);
			mailPage = PageFactory.initElements(driver, DraftPage.class);
			sentPage = PageFactory.initElements(driver, SentPage.class);
			inboxPage = PageFactory.initElements(driver, InboxPage.class);
			accountInformation = PageFactory.initElements(driver, AccountInformationPopUp.class);

		}

		@Test
		@Description("Login to the MailBox ")
		public void openLoginPage ()
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(account.getUSERNAME1(),account.getPASSWORD());
			String actualLoginName = accountInformation.checkUserAccount();
			assertTrue(actualLoginName.contains(account.getUSERNAME1().toLowerCase()));
		}

		@Test
		@Description("Cretaed Mail is saved as draft")
		public void cretedDraftMail () throws InterruptedException
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(account.getUSERNAME1(), account.getPASSWORD());
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
		public void sendDraftMail () throws InterruptedException
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(account.getUSERNAME1(),account.getPASSWORD());
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
		public void mailIsSent () throws InterruptedException
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(account.getUSERNAME1(), account.getPASSWORD());
			startPage.openMailPage();
			mailPage.createDraftMail(address, subject, body);
			mailPage.openDraftPage();
			mailPage.sendDraftMail();
			sentPage.openSentPage();
			sentPage.getFirstSentMail().click();
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
			loginPage.signIn(account.getUSERNAME1(), account.getPASSWORD());
			startPage.openInboxMailPage();
			String inboxMailPrimary = inboxPage.getBodyInboxMail();
			inboxPage.openContextMenu();
			inboxPage.openSocialTab();
			String inboxMailSocial = inboxPage.getTheLastSocialMail();
			assertEquals(inboxMailPrimary, inboxMailSocial);
		}

		@Test
		@Description("Move mail to promotions tab via drag and drop action")
		public void moveMailToPromotionsTab () throws InterruptedException
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(account.getUSERNAME1(), account.getPASSWORD());
			startPage.openInboxMailPage();
			String inboxMailPrimary = inboxPage.getBodyInboxMail();
			inboxPage.dragAnddropMailToPromotionsTab();
			inboxPage.openPromotionsTab();
			String inboxMailPromotions = inboxPage.getTheLastSocialMail();
			assertEquals(inboxMailPromotions, inboxMailPrimary);
		}

		@Test
		@Description("log out")
		public void logOut ()
		{
			startPage.openSite(BASE_URL);
			startPage.invokeSignIn();
			loginPage.signIn(account.getUSERNAME1(), account.getPASSWORD());
			accountInformation.logOut();
			assertTrue(startPage.getloginButton().isDisplayed());
		}

		@AfterMethod

		public void afterMethod ()
		{
			driver.quit();

		}

	}
