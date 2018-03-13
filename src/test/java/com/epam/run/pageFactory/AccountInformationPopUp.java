package com.epam.run.pageFactory;

import com.epam.run.helpers.HelperMethods;
import com.epam.run.helpers.Waiter;
import com.epam.run.reporting.Log;
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

	@FindBy(css = "a[href*='https://accounts.google.com']")
	private WebElement popUpAccount;

	@FindBy(css = "a[href*='Logout']")
	private WebElement logOutButton;

	@FindBy(name = "q")
	private WebElement searchField;

	HelperMethods helper= new HelperMethods();
	public String checkUserAccount() throws InterruptedException
	{
		Waiter.wait(driver, findEmailInPopUpWindow);
		helper.isElementPresent(findEmailInPopUpWindow);
		return findEmailInPopUpWindow.getText();
	}

	public void logOut() throws InterruptedException
	{
		Waiter.wait(driver,searchField);
		helper.isElementPresent(popUpAccount);
		popUpAccount.click();
		Log.info("Pop-up window is displayed");
		Waiter.wait(driver, logOutButton);
		logOutButton.click();
		Log.info("User Logout successfully");
	}

	@Override
	public AbstractMailPage openPage() throws InterruptedException
	{
		Waiter.wait(driver, searchField);
		Log.info("Pop-up window is displayed");
		popUpAccount.click();
		Log.info("Pop-up opens successfully");
		return this;
	}


}

