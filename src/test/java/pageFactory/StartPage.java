package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class StartPage
{

	private WebDriver driver;

	public StartPage(WebDriver driver)
	{
		this.driver = driver;

	}

	@FindBy(xpath = "//*[@id='gb_70']")
	private WebElement loginButton;

	@FindBy(partialLinkText = "Gmail")
	private WebElement mailPageLink;

	public void openSite(String base_url)
	{
		driver.get(base_url);
	}

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

	public WebElement getloginButton(){
		return loginButton;
	}

}
