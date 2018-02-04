package pageFactory;

import org.openqa.selenium.WebDriver;

/**
 * Created by Volha_Batsiushkova on 2/4/2018.
 */
public class PromotionTabPage extends InboxPage
{


	public PromotionTabPage(WebDriver driver)
	{
		super(driver);
	}
	@Override
	public void openPage()
	{
		super.openPage();
		openPromotionTab();
	}

	public void openPromotionTab(){
		action.click(promotionsTab).build().perform();
	}

	}
