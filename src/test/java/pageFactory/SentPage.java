package pageFactory;

import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	@FindBy(xpath = "//div[@class='BltHke nH oy8Mbf' and @role='main']//span[@class='bog']")
	private WebElement subjectSendingMail;

	@FindBy(xpath = "//div[@class='D E G-atb G-pMpUR-SBEKme' and @gh='tm']//div[@aria-label='Delete']")
	private WebElement deleteButton;

	@FindBy(name = "ok")
	private WebElement okButton;

	@Override
	public void openPage() throws InterruptedException
	{
		sentPage.click();
		Thread.sleep(500);

	}

	public WebElement getMail() throws InterruptedException
	{
		List<WebElement> listSentMails = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf' and @role='main']//tr"));
		WebElement listsentemail = listSentMails.get(0);
		return listsentemail;
	}

	public void deleteSentMessage() throws InterruptedException
	{
		Actions action = new Actions(driver);
		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		sentPage.click();
		Thread.sleep(2000);
		checkbox.click();
		jsExec.executeScript("arguments[0].click()", deleteButton);
		Waiter.waitAlert(driver);
		driver.switchTo().alert().accept();
	}

	public WebElement getsubjectSendingMail()
	{
		return subjectSendingMail;
	}
}
