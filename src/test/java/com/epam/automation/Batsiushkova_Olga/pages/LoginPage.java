package com.epam.automation.Batsiushkova_Olga.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class LoginPage
{

	private WebDriver driver;

	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}

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

}
