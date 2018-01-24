package pageFactory;

import com.google.common.collect.Iterables;
import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Volha_Batsiushkova on 1/23/2018.
 */
public class InboxPage extends AbstractMailPage
{
	public InboxPage(WebDriver driver)
	{
		super(driver);
	}
	Actions action = new Actions(driver);

	@FindBy(xpath = "//div[@class='BltHke nH oy8Mbf aE3']//span[@class='bog']")
	WebElement inboxmail;

	@FindBy(xpath = "//div[@aria-label='Social']")
	WebElement socialTab;

	@FindBy(xpath = "//div[@aria-label='Promotions']//div[contains(text(),'Promotions')]")
	WebElement promotionsTab;

	public String getBodyInboxMail(){
		return inboxmail.getText();
	}

	public String getTheLastSocialMail(){
		List<WebElement> allSocialMail = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf aE3']//span[@class='bog']"));
		WebElement lastSocialElement = Iterables.getLast(allSocialMail);
		return lastSocialElement.getText();
	}

	public WebElement getTheFirstPrimaryMail(){
		List<WebElement> allInboxMail = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf aE3']//span[@class='bog']"));
		WebElement firstPrimaryElement = allInboxMail.get(0);
		return firstPrimaryElement;
	}

	public void openContextMenu()
	{
		action.contextClick(inboxmail).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_RIGHT).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public void openSocialTab(){
		action.click(socialTab).build().perform();
	}

	public void openPromotionsTab() throws InterruptedException
	{
		action.click(promotionsTab).build().perform();

	}

	public void dragAnddropMailToPromotionsTab() throws InterruptedException
	{
		String mailtext = getTheFirstPrimaryMail().getText();
		Waiter.wait(driver,promotionsTab);
        action.dragAndDrop(getTheFirstPrimaryMail(), promotionsTab).build().perform();
		Waiter.waitElementIsAbsent(driver,inboxmail,mailtext );
	}
}
