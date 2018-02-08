package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 1/13/2018.
 */
public abstract class AbstractMailPage
{
	protected WebDriver driver;

	public AbstractMailPage(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(xpath = "//*[@id=':i']/span[text()='Gmail']")
	protected WebElement gmailPageLabel;

	@FindBy(className = "z0")
	protected WebElement openMailPopUp;

	@FindBy(name = "to")
	protected WebElement addressField;

	@FindBy(name = "subjectbox")
	protected WebElement subjectField;

	@FindBy(css = "a[href*='mail/#drafts']")
	protected WebElement draftPage;

	@FindBy(css = "a[href*='mail/#sent']")
	protected WebElement sentPage;

	@FindBy(css = "a[href*='mail/#inbox']")
	protected WebElement inboxPage;

	@FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
	protected WebElement bodyField;

	@FindBy(xpath = "//td[@class='Hm']/img[@class='Ha']")
	protected WebElement closePopUpMail;

	@FindBy(xpath = "//div[@class = 'aoD hl']")
	protected WebElement draftAddressEmail;

	@FindBy(xpath = "//input[@name='subject']")
	protected WebElement draftSubject;

	@FindBy(xpath = "//td[@class='gU Up']")
	protected WebElement sendButton;

	@FindBy(xpath = "//div[@class='BltHke nH oy8Mbf']//tr//*[@class='bog']")
	protected WebElement getNewlyCreatedMailInDraft;

	PageGenerator page = new PageGenerator();

	public abstract AbstractMailPage openPage() throws InterruptedException;

	public abstract WebElement getMail() throws InterruptedException;

 }
