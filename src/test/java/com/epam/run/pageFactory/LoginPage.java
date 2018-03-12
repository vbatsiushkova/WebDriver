package com.epam.run.pageFactory;

import com.epam.run.helpers.Page;
import com.epam.run.helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class LoginPage extends AbstractMailPage
{
	public LoginPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public final String BASE_URL = "https://www.google.by";
	@FindBy(xpath = "//*[@id='gb_70']")
	private WebElement loginButton;

	@FindBy(id = "identifierId")
	private WebElement inputEmail;

	@FindBy(id = "identifierNext")
	private WebElement identifierNextButton;

	@FindBy(id = "passwordNext")
	private WebElement passwordNextButton;

	@FindBy(name = "password")
	private WebElement inputPassword;

	@Override
	public AbstractMailPage openPage() throws InterruptedException
	{
		loginButton.click();
		return page.createPage(Page.LOGIN_PAGE, driver);
	}

	@Override
	public WebElement getMail() throws InterruptedException
	{
		return null;
	}

	public AbstractMailPage signIn(String username, String password)
	{
		inputEmail.sendKeys(username);
		identifierNextButton.click();
		Waiter.wait(driver, inputPassword);
		inputPassword.sendKeys(password);
		Waiter.wait(driver, passwordNextButton);
		passwordNextButton.click();
		return page.createPage(Page.START_PAGE, driver);
	}

}
