package pageFactory;

import helpers.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class StartPage  implements AbstractMailPage
{
	public static final String BASE_URL = "https://www.google.by";
	WebDriver driver;

	public StartPage(WebDriver driver)
	{
		this.driver = driver;
		//PageFactory.initElements(driver, StartPage.class);
	}

	@FindBy(xpath = "//*[@id='gb_70']")
	private WebElement loginButton;

	@FindBy(partialLinkText = "Gmail")
	private WebElement mailPageLink;


//	public LoginPage invokeSignIn()
//	{
//		loginButton.click();
//		return new LoginPage(driver);
//	}

//	public DraftPage openMailPage()
//	{
//
//		mailPageLink.click();
//		return new DraftPage(driver);
//	}

	public InboxPage openInboxMailPage()
	{
        mailPageLink.click();
		return (InboxPage) FactoryPages.getPage(Page.INBOX_PAGE, driver);
	}

	public WebElement getloginButton(){
		return loginButton;
	}

	@Override
	public StartPage openPage() throws InterruptedException
	{
		driver.get(BASE_URL);
		return this;
	}

	@Override
	public WebElement getMail() throws InterruptedException
	{
		return null;
	}

}
