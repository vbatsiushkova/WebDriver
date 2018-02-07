package pageFactory;

import helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class LoginPage implements AbstractMailPage
{

	private WebDriver driver;

	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		//PageFactory.initElements(driver, LoginPage.class);
	}
	@FindBy(xpath = "//*[@id='gb_70']")
	private WebElement loginButton;


	@FindBy(id = "identifierId")
	private WebElement inputEmail;

	@FindBy(xpath = "//content[@class='CwaK9']/span[@class='RveJvd snByac']")
	private WebElement nextButton;

	@FindBy(name = "password")
	private WebElement inputPassword;

	public StartPage signIn(String username, String password)
	{
		inputEmail.sendKeys(username);
		nextButton.click();
		Waiter.wait(driver, inputPassword);
		inputPassword.sendKeys(password);
		Waiter.wait(driver, nextButton);
		nextButton.click();
		return new StartPage(driver);
	}

	@Override
	public LoginPage openPage() throws InterruptedException
	{
		loginButton.click();
		return this;
	}


	@Override
	public WebElement getMail() throws InterruptedException
	{
		return null;
	}
}
