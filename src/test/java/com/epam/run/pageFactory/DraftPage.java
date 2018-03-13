package com.epam.run.pageFactory;

import com.epam.run.helpers.HelperMethods;
import com.epam.run.helpers.Page;
import com.epam.run.helpers.Waiter;
import com.epam.run.reporting.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Volha_Batsiushkova on 1/7/2018.
 */
public class DraftPage extends AbstractMailPage
{
	public DraftPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	HelperMethods helper= new HelperMethods();

	@Override
	public AbstractMailPage openPage() throws InterruptedException
	{
		draftPage.click();
		Waiter.wait(driver, getNewlyCreatedMailInDraft);
		Log.info("Draft Page is opened");
		return page.createPage(Page.DRAFT_PAGE,driver);
	}

	public String actualAddress() throws InterruptedException
	{
		helper.isElementPresent(draftAddressEmail);
		return draftAddressEmail.getText();
	}

	public String actualSubject() throws InterruptedException
	{
		helper.isElementPresent(draftSubject);
		return draftSubject.getAttribute("value");
	}

	public String actualBody() throws InterruptedException
	{
		helper.isElementPresent(bodyField);
		return bodyField.getText();
	}

	public void sendDraftMail() throws InterruptedException
	{
		Waiter.wait(driver, getNewlyCreatedMailInDraft);
		helper.isElementPresent(getNewlyCreatedMailInDraft);
		String draftMailText = getNewlyCreatedMailInDraft.getText();
		getNewlyCreatedMailInDraft.click();
		Waiter.wait(driver, sendButton);
		Log.info("Draft Mail is sent");
		sendButton.click();
		Log.info("Wait until mail is disappeared from draft mails");
		Waiter.waitElementIsAbsent(driver, getNewlyCreatedMailInDraft, draftMailText);
	}

	public int getCountDraftMails()
	{
		HelperMethods helper = new HelperMethods();
		int value;
		String takeInt = helper.parsingString(draftPage.getText());
		if (takeInt == null)
		{
			value = 0;
		}
		else
			value = Integer.valueOf(takeInt);
		return value;
	}

	@Override
	public WebElement getMail() throws InterruptedException
	{
		helper.isElementPresent(getNewlyCreatedMailInDraft);
		return getNewlyCreatedMailInDraft;
	}


}




