package pageFactory;

import helpers.Page;
import org.openqa.selenium.WebDriver;

/**
 * Created by Volha_Batsiushkova on 2/6/2018.
 */
public class FactoryPages
{

	public static AbstractMailPage getPage(Page namePage, WebDriver driver)
	{
		switch (namePage)
		{
		case START_PAGE:
			return new StartPage(driver);
		case SENT_PAGE:
			return new SentPage(driver);
		case DRAFT_PAGE:
			return new DraftPage(driver);
		case INBOX_PAGE:
			return new InboxPage(driver);
		case LOGIN_PAGE:
			return new LoginPage(driver);
		case ACCOUNT_POP_UP:
			return new AccountInformationPopUp(driver);
		}
		return null;
	}
}
