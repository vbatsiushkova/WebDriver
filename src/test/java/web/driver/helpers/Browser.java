package web.driver.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Volha_Batsiushkova on 1/25/2018.
 */
public class Browser
{
	private static WebDriver driver;

	private Browser(){}


	public static WebDriver getBrowser(BrowserType name)
	{
		switch (name)
		{
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		return driver;
	}

}
