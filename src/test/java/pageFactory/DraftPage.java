package pageFactory;

import helpers.HelperMethods;
import helpers.Page;
import helpers.Waiter;
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

	@Override
	public AbstractMailPage openPage() throws InterruptedException
	{
		draftPage.click();
		Waiter.wait(driver, getNewlyCreatedMailInDraft);
		return page.createPage(Page.DRAFT_PAGE,driver);
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
		String draftMailText = getNewlyCreatedMailInDraft.getText();
		getNewlyCreatedMailInDraft.click();
		Waiter.wait(driver, sendButton);
		sendButton.click();
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
	public WebElement getMail()
	{
		
		return getNewlyCreatedMailInDraft;
	}


}




