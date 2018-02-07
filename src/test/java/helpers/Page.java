package helpers;

/**
 * Created by Volha_Batsiushkova on 2/6/2018.
 */
public enum Page
{
	DRAFT_PAGE("DraftPage"),
	START_PAGE("StartPage"),
	SENT_PAGE("SentPage"),
	LOGIN_PAGE("LoginPage"),
	INBOX_PAGE("InboxPage"),
	ACCOUNT_POP_UP("AccountInformationPopUp");

	private String name;

	Page(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
}

