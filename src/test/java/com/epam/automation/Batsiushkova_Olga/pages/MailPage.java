package com.epam.automation.Batsiushkova_Olga.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Volha_Batsiushkova on 1/7/2018.
 */
public class MailPage
{
	WebDriver driver;

	public MailPage(WebDriver driver)
	{
		this.driver = driver;
	}

	List<WebElement> draftEmailList;

	@FindBy(xpath = "//*[@id=':i']/span[text()='Gmail']")
	WebElement gmailPageLabel;

	@FindBy(className = "z0")
	WebElement openMailPopUp;

	@FindBy(name = "to")
	WebElement addressField;

	@FindBy(name = "subjectbox")
	WebElement subjectField;

	@FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
	WebElement bodyField;

	@FindBy(xpath = "//td[@class='Hm']/img[@class='Ha']")
	WebElement closePopUpMail;

	@FindBy(css = "a[href*='mail/#drafts']")
	WebElement draftPage;

	@FindBy(css = "a[href*='mail/#sent']")
	WebElement sentPage;

	@FindBy(xpath = "//div[@class = 'aoD hl']")
	WebElement draftAddressEmail;

	@FindBy(xpath = "//div[@class='aoD az6']/input[@name='subjectbox']")
	WebElement draftSubject;

	@FindBy(xpath = "//td[@class='gU Up']")
	WebElement sendButton;

	@FindBy(xpath = "//div[@class='BltHke nH oy8Mbf']//tr//*[@class='bog']")
	WebElement getNewlyCreatedMailInDraft;

	
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

	public void openDraftPage()
	{
		assertTrue(draftPage.isDisplayed());
		draftPage.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public String actualAddress() throws InterruptedException
	{
		return draftAddressEmail.getText();
	}

	public String actualSubject() throws InterruptedException
	{

		return draftSubject.getText();
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
		Waiter.waitElementAbsent(driver, sendButton);
		driver.navigate().refresh();
		driver.switchTo().alert().accept();
		assertTrue(draftPage.isDisplayed());

	}

	public void openSentPage()
	{
		assertTrue(sentPage.isDisplayed());
		sentPage.click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


	}

	public int getCountDraftMails()
	{

		return parsingString(draftPage.getText());
	}
	

	public int parsingString(String stringValue)
	{
		String value = null;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(stringValue);
		while (m.find())
		{
			value = m.group();
		}
		return Integer.parseInt(value);
	}


	public WebElement getCountSentMail(){
		List<WebElement>  listSentMails = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf']"));
		WebElement listsentemail= listSentMails.get(1);

		return listsentemail.findElement(By.xpath("//tr//*[@class='bog']"));
	}

	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}   // try
		catch (NoAlertPresentException Ex)
		{
			return false;
		}   // catch
	}
}




