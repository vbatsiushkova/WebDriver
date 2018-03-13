package com.epam.run.pageFactory;

import com.epam.run.helpers.Page;
import com.epam.run.reporting.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class StartPage extends AbstractMailPage
{
	public final String BASE_URL = "https://www.google.by";
	@FindBy(xpath = "//*[@id='gb_70']")
	private WebElement loginButton;

	@FindBy(partialLinkText = "Gmail")
	private WebElement mailPageLink;

	public StartPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public AbstractMailPage openPage() throws InterruptedException
	{
		driver.get(BASE_URL);
		Log.info("Start Page is opened");
		return new StartPage(driver);
	}

	@Override
	public WebElement getMail() throws InterruptedException
	{
		return null;
	}

	public AbstractMailPage openInboxMailPageFromStartPage()
	{
		mailPageLink.click();
		Log.info("MailPage is opened");
		return page.createPage(Page.INBOX_PAGE, driver);
	}

	public WebElement getloginButton()
	{
		return loginButton;
	}

}
