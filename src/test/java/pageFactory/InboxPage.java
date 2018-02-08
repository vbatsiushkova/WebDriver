package pageFactory;

import com.google.common.collect.Iterables;
import helpers.Page;
import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Volha_Batsiushkova on 1/23/2018.
 */
public class InboxPage extends AbstractMailPage
{

	public InboxPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	Actions action = new Actions(driver);

	@FindBy(xpath = "//div[@class='BltHke nH oy8Mbf aE3']//span[@class='bog']")
	WebElement inboxmail;
	@FindBy(xpath = "//div[@aria-label='Promotions']//div[contains(text(),'Promotions')]")
	WebElement promotionsTab;
	@FindBy(xpath = "//div[@aria-label='Social']")
	WebElement socialTab;

	public String getBodyInboxMail()
	{
		return inboxmail.getText();
	}

	public String getTheLastSocialMail()
	{
		List<WebElement> allSocialMail = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf aE3']//span[@class='bog']"));
		WebElement lastSocialElement = Iterables.getLast(allSocialMail);
		return lastSocialElement.getText();
	}

	public WebElement getMail()
	{
		List<WebElement> allInboxMail = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf aE3']//span[@class='bog']"));
		WebElement firstPrimaryElement = allInboxMail.get(0);
		return firstPrimaryElement;
	}

	public void openContextMenu()
	{
		action.contextClick(inboxmail).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_RIGHT).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
	}

	@Override
	public AbstractMailPage openPage()
	{
		inboxPage.click();
		return page.createPage(Page.INBOX_PAGE, driver);
	}

	public void dragAnddropMailToPromotionsTab() throws InterruptedException
	{
		String mailtext = getMail().getText();
		Waiter.wait(driver, promotionsTab);
		action.dragAndDrop(getMail(), promotionsTab).build().perform();
	}

	public void openPromotionTab()
	{
		action.click(promotionsTab).build().perform();
	}

	public void openSocialTab()
	{
		action.click(socialTab).build().perform();
	}

	public void createDraftMail(String address, String subject, String body)
	{
		Waiter.wait(driver, gmailPageLabel);
		openMailPopUp.click();
		Waiter.wait(driver, addressField);
		addressField.sendKeys(address);
		subjectField.sendKeys(subject);
		bodyField.click();
		bodyField.sendKeys(body);
		closePopUpMail.click();
		Waiter.waitElementIsAbsent(driver,bodyField, body);
    }

}
