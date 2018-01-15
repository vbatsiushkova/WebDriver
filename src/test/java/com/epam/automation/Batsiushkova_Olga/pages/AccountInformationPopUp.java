package com.epam.automation.Batsiushkova_Olga.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 1/5/2018.
 */
public class AccountInformationPopUp
{
	private WebDriver driver;

	public AccountInformationPopUp(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='gb_xb']//div[@class='gb_Cb']")
	private WebElement findEmailInPopUpWindow;

	@FindBy(xpath = "//span[@class='gb_ab gbii']")
	private WebElement popUpAccount;

	@FindBy(css = "a[href*='Logout']")
	private WebElement logOutButton;

	@FindBy(name = "q")
	private WebElement searchField;

	public String checkUserAccount()
	{
		Waiter.wait(driver, searchField);
		popUpAccount.click();
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

}

