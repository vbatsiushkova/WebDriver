package com.epam.run.helpers;

/**
 * Created by Volha_Batsiushkova on 2/7/2018.
 */
public enum Page
{
	START_PAGE("StartPage"),
	SENT_PAGE("SentPage"),
	LOGIN_PAGE("LoginPage"),
	INBOX_PAGE("InboxPage"),
	DRAFT_PAGE("DraftPage"),
	ACCOUNT_POP_UP("AccountInformationPopUp");

	String name;
	String className;

	Page(String name)
	{
		this.name = name;

	}


	public String getName(){
		return name;
	}


}
