package com.epam.automation.Batsiushkova_Olga.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	public void signIn(String username, String password)
	{
    	inputEmail.sendKeys(username);
		nextButton.click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(inputPassword));
		inputPassword.sendKeys(password);
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(nextButton));
		nextButton.click();
    }

}
