package com.epam.run.pageFactory;

import com.epam.run.helpers.HelperMethods;
import com.epam.run.helpers.Page;
import com.epam.run.helpers.Waiter;
import com.epam.run.reporting.Log;
import com.google.common.collect.Iterables;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Volha_Batsiushkova on 1/23/2018.
 */
public class InboxPage extends AbstractMailPage
{

	public InboxPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	Actions action = new Actions(driver);
	HelperMethods helper= new HelperMethods();

	@FindBy(xpath = "//div[@class='BltHke nH oy8Mbf aE3']//span[@class='bog']")
	WebElement inboxmail;
	@FindBy(xpath = "//div[@aria-label='Promotions']//div[contains(text(),'Promotions')]")
	WebElement promotionsTab;
	@FindBy(xpath = "//div[@aria-label='Social']")
	WebElement socialTab;


	public String getBodyInboxMail() throws InterruptedException
	{
		helper.isElementPresent(inboxmail);
		return inboxmail.getText();
	}

	public String getTheLastSocialMail()
	{
		List<WebElement> allSocialMail = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf aE3']//span[@class='bog']"));
		WebElement lastSocialElement = Iterables.getLast(allSocialMail);
		return lastSocialElement.getText();
	}

	public WebElement getMail()
	{
		List<WebElement> allInboxMail = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf aE3']//span[@class='bog']"));
		WebElement firstPrimaryElement = allInboxMail.get(0);
		return firstPrimaryElement;
	}

	public void openContextMenu()
	{
		Log.info("Open context menu");
		action.contextClick(inboxmail).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_RIGHT).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
	}

	@Override
	public AbstractMailPage openPage()
	{
		Log.info("Open inbox page");
		inboxPage.click();
		return page.createPage(Page.INBOX_PAGE, driver);
	}

	public void dragAnddropMailToPromotionsTab() throws InterruptedException
	{
		Log.info("Drag&Drop mail to Promotion tab");
		String mailtext = getMail().getText();
		Waiter.wait(driver, promotionsTab);
		action.dragAndDrop(getMail(), promotionsTab).build().perform();
	}

	public void openPromotionTab()
	{
		Log.info("Open promotion tab");
		action.click(promotionsTab).build().perform();
	}

	public void openSocialTab()
	{
        Log.info("Open social tab");
		action.click(socialTab).build().perform();
	}

	public void createDraftMail(String address, String subject, String body)
	{
		Waiter.wait(driver, gmailPageLabel);
		Log.info("Open mail pop-up");
		openMailPopUp.click();
		Waiter.wait(driver, addressField);
		Log.info("Populate address field by "+address);
		addressField.sendKeys(address);
		Log.info("Populate subject field by "+subject);
		subjectField.sendKeys(subject);
		bodyField.click();
		Log.info("Populate body field by "+body);
		bodyField.sendKeys(body);
		closePopUpMail.click();
		Waiter.waitElementIsAbsent(driver,bodyField, body);
    }

}
