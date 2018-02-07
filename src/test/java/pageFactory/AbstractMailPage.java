package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 1/13/2018.
 */
public interface AbstractMailPage
{
	//WebDriver driver = null;

	@FindBy(xpath = "//*[@id=':i']/span[text()='Gmail']")
	WebElement gmailPageLabel = null;

	@FindBy(className = "z0")
	 WebElement openMailPopUp = null;

	@FindBy(name = "to")
	 WebElement addressField= null;

	@FindBy(name = "subjectbox")
	 WebElement subjectField= null;

	@FindBy(css = "a[href*='mail/#drafts']")
	 WebElement draftPage= null;

	@FindBy(css = "a[href*='mail/#sent']")
	 WebElement sentPage= null;

	@FindBy(css = "a[href*='mail/#inbox']")
	 WebElement inboxPage= null;

	AbstractMailPage openPage() throws InterruptedException;

	WebElement getMail() throws InterruptedException;


}
