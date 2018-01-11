package com.epam.automation.Batsiushkova_Olga.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class StartPage
{


	private WebDriver driver;

	@FindBy(xpath = "//*[@id='gb_70']")
	private WebElement loginButton;

	@FindBy(partialLinkText = "Gmail")
	private WebElement mailPageLink;

	public StartPage(WebDriver driver)
	{
		this.driver = driver;

	}

	public void openSite(String base_url)
	{
		driver.get(base_url);
	}

	public LoginPage invokeSignIn()
	{
			loginButton.click();
		return new LoginPage(driver);
	}

	public MailPage openMailPage()
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		mailPageLink.click();
		return new MailPage(driver);
	}



}