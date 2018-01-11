package com.epam.automation.Batsiushkova_Olga.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
	int tempValue = 1;

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

	@FindBy(css = "a[href*='drafts']")
	WebElement draftPage;

	@FindBy(css = "a[href*='sent']")
	WebElement sentPage;

	@FindBy(xpath = "//tbody//tr[@class='zA yO']")
	WebElement mailTable;

	@FindBy(xpath = "//div[@class = 'aoD hl']")
	WebElement draftAddressEmail;

	@FindBy(xpath = "//div[@class='aoD az6']/input[@name='subjectbox']")
	WebElement draftSubject;

	@FindBy(xpath = "//td[@class='gU Up']")
	WebElement sendButton;

	@FindBy(xpath = "//tbody//tr[@class='zA zE']")
	WebElement sentList;

	public void createDraftMail(String address, String subject, String body)
	{
		Waiter.wait(driver, gmailPageLabel);
		Assert.assertTrue(gmailPageLabel.isDisplayed());
		openMailPopUp.click();
		Waiter.wait(driver, addressField);
		addressField.sendKeys(address);
		subjectField.sendKeys(subject);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		bodyField.click();
		bodyField.sendKeys(body);
		closePopUpMail.click();
	}

	public void openDraftPage()
	{
        draftPage.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	public List<WebElement> getListofCreatedDraftMail()
	{
		draftEmailList = driver.findElements(By.xpath("//div[@class='Cp']//tr[@class = 'zA yO']"));
		return draftEmailList;
	}

	public WebElement getBodyCreatedAttribute() throws InterruptedException
	{

		WebElement bodyofTheMail = getListofCreatedDraftMail().get(0).findElement(By.xpath("//tr//div[@class='xS']"));
        highlightElement(bodyofTheMail);
		return bodyofTheMail;
	}

	public String actualAddress() throws InterruptedException
	{
		highlightElement(draftAddressEmail);
		return draftAddressEmail.getText();
	}

	public String actualSubject() throws InterruptedException
	{

		highlightElement(draftSubject);
		return draftSubject.getText();
	}

	public String actualBody() throws InterruptedException
	{
		highlightElement(bodyField);
		return bodyField.getText();
	}

	public void highlightElement(WebElement element) throws InterruptedException
	{
		String bg = element.getCssValue("backgroundColor");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", element);
		// take screenshot here
		// or just pause/blink
		Thread.sleep(500);
		js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
	}

	public void sendDraftMail() throws InterruptedException
	{
		getBodyCreatedAttribute().click();
		sendButton.click();
	}


	public void openSentPage(){
		sentPage.click();
		Waiter.wait(driver, mailTable );

	}

	public int getListSizeMails() throws InterruptedException
	{
		driver.navigate().refresh();
		Waiter.wait(driver, mailTable);
		List<WebElement> mailList = driver.findElements(By.xpath("//tbody//tr[@class='zA yO']"));
		return mailList.size();

	}
}




