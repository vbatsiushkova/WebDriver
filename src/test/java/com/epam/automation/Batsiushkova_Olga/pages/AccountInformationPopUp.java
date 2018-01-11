package com.epam.automation.Batsiushkova_Olga.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	@FindBy(xpath = "//div[@class='gb_hb gb_5c gb_Eg gb_R']//div[@class='gb_Mc gb_jb gb_Eg gb_R']/a[@class='gb_b gb_fb gb_R']")
	private WebElement popUpAccount;

	public String checkUserAccount()
	{
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(popUpAccount));
		popUpAccount.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(findEmailInPopUpWindow));
		return findEmailInPopUpWindow.getText();
	}

}

