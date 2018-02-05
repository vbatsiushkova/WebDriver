//package pageFactory;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
///**
// * Created by Volha_Batsiushkova on 2/4/2018.
// * //
// */
//public class SocialTabPage extends InboxPage
//{
//	@FindBy(xpath = "//div[@aria-label='Social']")
//	WebElement socialTab;
//	InboxPage inboxPage;
//
//	public SocialTabPage(WebDriver driver, InboxPage inboxPage)
//	{
//		super(driver);
//		this.inboxPage = inboxPage;
//	}
//
//	public void openPage()
//	{
//		openSocialTab();
//	}
//
//	public void openSocialTab()
//	{
//		action.click(socialTab).build().perform();
//	}
//
//}
