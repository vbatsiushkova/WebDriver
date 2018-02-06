package pageFactory;

import static page.object.tests.GoogleMailSentTest.driver;

/**
 * Created by Volha_Batsiushkova on 2/6/2018.
 */
public class FactoryPages
{

	public AbstractMailPage getPage(String namePage){
		switch (namePage){
		case "StartPage":
			return new StartPage(driver);

		}
		return null;
	}
}
