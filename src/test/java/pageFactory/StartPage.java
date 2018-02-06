package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class StartPage  extends AbstractMailPage
{
	public final String BASE_URL = "https://www.google.by";

	public StartPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, StartPage.class);
	}

	@FindBy(xpath = "//*[@id='gb_70']")
	private WebElement loginButton;

	@FindBy(partialLinkText = "Gmail")
	private WebElement mailPageLink;


	public LoginPage invokeSignIn()
	{
		loginButton.click();
		return new LoginPage(driver);
	}

	public DraftPage openMailPage()
	{

		mailPageLink.click();
		return new DraftPage(driver);
	}

	public InboxPage openInboxMailPage()
	{
        mailPageLink.click();
		return new InboxPage(driver);
	}

	public WebElement getloginButton(){
		return loginButton;
	}

	@Override
	public void openPage() throws InterruptedException
	{
		driver.get(BASE_URL);
	}

	@Override
	public WebElement getMail() throws InterruptedException
	{
		return null;
	}

}
