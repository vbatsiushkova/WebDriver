package page.object.tests;

import com.sun.org.glassfish.gmbal.Description;

import org.testng.annotations.*;
import page.LoginPageTest;

import static helpers.ConfigurationConstants.PASSWORD;
import static helpers.ConfigurationConstants.USERNAME1;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class GoogleMailSentTest {
private LoginPageTest loginPageTest;
	public GoogleMailSentTest() {
loginPageTest = new LoginPageTest();
	}

	@BeforeMethod
	@Description("Login to the http://staging.remedly.com/user-login")
	public void precondition() {

	}

	@Test
	@Description("Login to the http://staging.remedly.com/user-login ")
	public void openLoginPage() throws InterruptedException {

	}
//
//	@Test
//	@Description("Cretaed Mail is saved as draft")
//	public void cretedDraftMail() throws InterruptedException {
//		startPage.openPage();
//		loginPage.openPage();
//		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
//		startPage.openInboxMailPageFromStartPage();
//		inboxPage.createDraftMail(address, subject, body);
//		draftPage.openPage();
//		String createdMail = draftPage.getMail().getText();
//		assertTrue(createdMail.contains(subject));
//		draftPage.getMail().click();
//		assertEquals(draftPage.actualAddress(), address);
//		assertEquals(draftPage.actualSubject(), subject);
//		assertEquals(draftPage.actualBody(), body);
//	}
//
//	@Test
//	@Description("Send the latest draft mail")
//	public void sendDraftMail() throws InterruptedException {
//		startPage.openPage();
//		loginPage.openPage();
//		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
//		startPage.openInboxMailPageFromStartPage();
//		inboxPage.createDraftMail(address, subject, body);
//		draftPage.openPage();
//		int draftMailsBefore = draftPage.getCountDraftMails();
//		draftPage.sendDraftMail();
//		int draftMailAfter = draftPage.getCountDraftMails();
//		assertEquals(draftMailAfter, draftMailsBefore - 1);
//
//	}
//
//	@Test
//	@Description("Send the latest draft mail")
//	public void mailIsSent() throws InterruptedException {
//		startPage.openPage();
//		loginPage.openPage();
//		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
//		startPage.openInboxMailPageFromStartPage();
//		inboxPage.createDraftMail(address, subject, body);
//		draftPage.openPage();
//		String sendingDraftBody = draftPage.getMail().getText();
//		draftPage.sendDraftMail();
//		sentPage.openPage();
//		sentPage.getMail().click();
//		WebElement subjectSendingMailElement = sentPage.getsubjectSendingMail();
//		String subjectSendingMail = subjectSendingMailElement.getText();
//		assertEquals(sendingDraftBody, subjectSendingMail);
//		sentPage.deleteSentMessage();
//		assertTrue(!subjectSendingMailElement.isDisplayed());
//	}
//
//	@Test
//	@Description("Move mail to social tab")
//	public void moveMailToSocialTab() throws InterruptedException {
//		startPage.openPage();
//		loginPage.openPage();
//		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
//		startPage.openInboxMailPageFromStartPage();
//		String inboxMailPrimary = inboxPage.getBodyInboxMail();
//		inboxPage.openContextMenu();
//		inboxPage.openSocialTab();
//		String inboxMailSocial = inboxPage.getTheLastSocialMail();
//		assertEquals(inboxMailPrimary, inboxMailSocial);
//	}
//
//	@Test
//	@Description("Move mail to promotions tab via drag and drop action")
//	public void moveMailToPromotionsTab() throws InterruptedException {
//		startPage.openPage();
//		loginPage.openPage();
//		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
//		startPage.openInboxMailPageFromStartPage();
//		String inboxMailPrimary = inboxPage.getBodyInboxMail();
//		inboxPage.dragAnddropMailToPromotionsTab();
//		inboxPage.openPromotionTab();
//		String inboxMailPromotions = inboxPage.getTheLastSocialMail();
//		assertEquals(inboxMailPromotions, inboxMailPrimary);
//	}
//
//	@Test
//	@Description("log out")
//	public void logOut() throws InterruptedException {
//		startPage.openPage();
//		loginPage.openPage();
//		loginPage.signIn(Account.USERNAME1, Account.PASSWORD);
//		accountInformation.logOut();
//		assertTrue(startPage.getloginButton().isDisplayed());
//	}

}
