package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 1/13/2018.
 */
public class AbstractMailPage
{
	WebDriver driver;

	public AbstractMailPage(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(xpath = "//*[@id=':i']/span[text()='Gmail']")
	protected WebElement gmailPageLabel;

	@FindBy(className = "z0")
	protected WebElement openMailPopUp;

	@FindBy(name = "to")
	protected WebElement addressField;

	@FindBy(name = "subjectbox")
	protected WebElement subjectField;

	@FindBy(css = "a[href*='mail/#drafts']")
	protected WebElement draftPage;

	@FindBy(css = "a[href*='mail/#sent']")
	protected WebElement sentPage;
}
