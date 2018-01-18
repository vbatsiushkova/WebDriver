package pageFactory;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Volha_Batsiushkova on 1/13/2018.
 */
public class SentPage extends AbstractMailPage
{
	public SentPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//div[@class='BltHke nH oy8Mbf' and @role='main']//tr//*[@role='checkbox']")
	private WebElement checkbox;

	@FindBy(xpath = "//td[@class='Bu']//h2")
	private WebElement subjectSendingMail;

	@FindBy(xpath = "//div[@aria-label='Delete']")
	private WebElement deleteButton;

	public void openSentPage() throws InterruptedException
	{
		sentPage.click();
		Thread.sleep(500);

	}

	public WebElement getFirstSentMail() throws InterruptedException
	{
		List<WebElement> listSentMails = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf' and @role='main']//tr"));
		WebElement listsentemail = listSentMails.get(0);
		return listsentemail;
	}

	public void deleteSentMessage() throws InterruptedException
	{

		sentPage.click();
		Thread.sleep(1000);
		Waiter.wait(driver, checkbox);
		checkbox.click();
		Thread.sleep(1000);
		deleteButton.click();
		driver.switchTo().alert().accept();
	}

	public WebElement getsubjectSendingMail()
	{
		return subjectSendingMail;
	}

}
