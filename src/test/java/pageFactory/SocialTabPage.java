package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 2/4/2018.
 */
public class SocialTabPage extends InboxPage
{
	@FindBy(xpath = "//div[@aria-label='Social']")
	WebElement socialTab;

	public SocialTabPage(WebDriver driver)
	{
		super(driver);
	}

	public void openPage(){
		super.openPage();
		openSocialTab();
	}

	public void openSocialTab(){
		action.click(socialTab).build().perform();
	}

}
