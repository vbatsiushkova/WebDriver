package pageFactory;

import helpers.HelperMethods;
import helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 1/7/2018.
 */
public class DraftPage implements AbstractMailPage
{
	WebDriver driver;

	public DraftPage(WebDriver driver)
	{
		this.driver = driver;
		//PageFactory.initElements(driver, DraftPage.class);
	}

	@FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
	private WebElement bodyField;

	@FindBy(xpath = "//td[@class='Hm']/img[@class='Ha']")
	private WebElement closePopUpMail;

	@FindBy(xpath = "//div[@class = 'aoD hl']")
	private WebElement draftAddressEmail;

	@FindBy(xpath = "//input[@name='subject']")
	private WebElement draftSubject;

	@FindBy(xpath = "//td[@class='gU Up']")
	private WebElement sendButton;

	@FindBy(xpath = "//div[@class='BltHke nH oy8Mbf']//tr//*[@class='bog']")
	private WebElement getNewlyCreatedMailInDraft;

	public void createDraftMail(String address, String subject, String body)
	{
		Waiter.wait(driver, gmailPageLabel);
		openMailPopUp.click();
		Waiter.wait(driver, addressField);
		addressField.sendKeys(address);
		subjectField.sendKeys(subject);
		bodyField.click();
		bodyField.sendKeys(body);
		closePopUpMail.click();
		Waiter.waitElementIsAbsent(driver, bodyField, body);

	}

	@Override
	public DraftPage openPage() throws InterruptedException
	{
		draftPage.click();
		Waiter.wait(driver, getNewlyCreatedMailInDraft);
		return this;
	}

	public String actualAddress() throws InterruptedException
	{
		return draftAddressEmail.getText();
	}

	public String actualSubject() throws InterruptedException
	{

		return draftSubject.getAttribute("value");
	}

	public String actualBody() throws InterruptedException
	{
		return bodyField.getText();
	}

	public void sendDraftMail() throws InterruptedException
	{
		Waiter.wait(driver, getNewlyCreatedMailInDraft);
		String draftMailText = getNewlyCreatedMailInDraft.getText();
		getNewlyCreatedMailInDraft.click();
		Waiter.wait(driver, sendButton);
		sendButton.click();
		Waiter.waitElementIsAbsent(driver, getNewlyCreatedMailInDraft, draftMailText);
	}

	public int getCountDraftMails()
	{
		HelperMethods helper = new HelperMethods();
		int value;
		String takeInt = helper.parsingString(draftPage.getText());
		if (takeInt == null)
		{
			value = 0;
		}
		else
			value = Integer.valueOf(takeInt);
		return value;
	}

	@Override
	public WebElement getMail()
	{
		return getNewlyCreatedMailInDraft;
	}
}




