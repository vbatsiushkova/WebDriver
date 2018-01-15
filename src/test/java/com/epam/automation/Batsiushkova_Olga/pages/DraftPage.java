package com.epam.automation.Batsiushkova_Olga.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Volha_Batsiushkova on 1/7/2018.
 */
public class DraftPage extends AbstracMailPage
{
	public DraftPage(WebDriver driver)
	{
		super(driver);
	}

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
	private WebElement bodyField;

	@FindBy(xpath = "//td[@class='Hm']/img[@class='Ha']")
	private WebElement closePopUpMail;

	@FindBy(xpath = "//div[@class = 'aoD hl']")
	private WebElement draftAddressEmail;

	@FindBy(xpath = "//input[@name='subject']")
	private WebElement draftSubject;

	@FindBy(xpath = "//td[@class='gU Up']")
	private WebElement sendButton;

	@FindBy(xpath = "//div[@class='BltHke nH oy8Mbf']//tr//*[@class='bog']")
	private WebElement getNewlyCreatedMailInDraft;

	public void createDraftMail(String address, String subject, String body)
	{
		Waiter.wait(driver, gmailPageLabel);
		Assert.assertTrue(gmailPageLabel.isDisplayed());
		openMailPopUp.click();
		Waiter.wait(driver, addressField);
		addressField.sendKeys(address);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		subjectField.sendKeys(subject);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		bodyField.click();
		bodyField.sendKeys(body);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		closePopUpMail.click();

	}

	public void openDraftPage() throws InterruptedException
	{
		assertTrue(draftPage.isDisplayed());
		draftPage.click();
		Thread.sleep(1000);
	}

	public String actualAddress() throws InterruptedException
	{
		return draftAddressEmail.getText();
	}

	public String actualSubject() throws InterruptedException
	{

		return draftSubject.getAttribute("value");
	}

	public String actualBody() throws InterruptedException
	{
		return bodyField.getText();
	}

	public void sendDraftMail() throws InterruptedException
	{
		Waiter.wait(driver, getNewlyCreatedMailInDraft);
		getNewlyCreatedMailInDraft.click();
		Waiter.wait(driver, sendButton);
		sendButton.click();
		Thread.sleep(1000);
	}



	public int getCountDraftMails()
	{
		int value;
		String takeInt = parsingString(draftPage.getText());
		if (takeInt == null)
		{
			value = 0;
		}
		else
			value = Integer.valueOf(takeInt);
		return value;
	}

	public String parsingString(String stringValue)
	{
		String value = null;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(stringValue);
		while (m.find())
		{
			value = m.group();
		}
		return value;
	}

		public WebElement getNewlyCreatedMailInDraft(){
		return getNewlyCreatedMailInDraft;
	}
}




