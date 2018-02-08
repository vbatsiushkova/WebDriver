package pageFactory;

import helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Volha_Batsiushkova on 1/5/2018.
 */
public class AccountInformationPopUp  extends AbstractMailPage
{
	public AccountInformationPopUp(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}


	@Override
	public WebElement getMail() throws InterruptedException
	{
		return null;
	}

	@FindBy(xpath = "//div[@aria-label='Account Information']//div[contains(text(),'@gmail.com')]")
	private WebElement findEmailInPopUpWindow;

	@FindBy(xpath = "//span[@class='gb_ab gbii']")
	private WebElement popUpAccount;

	@FindBy(css = "a[href*='Logout']")
	private WebElement logOutButton;

	@FindBy(name = "q")
	private WebElement searchField;

	public String checkUserAccount()
	{
		Waiter.wait(driver, findEmailInPopUpWindow);
		return findEmailInPopUpWindow.getText();
	}

	public void logOut()
	{
		Waiter.wait(driver,searchField);
		popUpAccount.click();
		Waiter.wait(driver, logOutButton);
		logOutButton.click();
	}

	@Override
	public AbstractMailPage openPage() throws InterruptedException
	{
		Waiter.wait(driver, searchField);
		popUpAccount.click();
		return this;
	}


}

