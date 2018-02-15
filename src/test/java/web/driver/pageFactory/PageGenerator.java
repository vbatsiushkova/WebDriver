package web.driver.pageFactory;

import web.driver.helpers.Page;
import org.openqa.selenium.WebDriver;

/**
 * Created by Volha_Batsiushkova on 2/7/2018.
 */
public class PageGenerator
{

	public AbstractMailPage createPage(Page page, WebDriver driver)
	{
		switch (page)
		{
		case START_PAGE:
			return new StartPage(driver);
		case SENT_PAGE:
			return new SentPage(driver);
		case LOGIN_PAGE:
			return new LoginPage(driver);
		case INBOX_PAGE:
			return new InboxPage(driver);
		case DRAFT_PAGE:
			return new DraftPage(driver);
		case ACCOUNT_POP_UP:
			return new AccountInformationPopUp(driver);
		}
		return null;
	}
}
