package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverUtils {
	private static WebDriver driver;

	private static WebDriver getDriver(BrowserType name)
	{
		switch (name)
		{
			case FIREFOX:
				System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
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

	public static WebDriver getInstance() {
		if (driver == null) {
			driver = getDriver(BrowserType.FIREFOX);
		}
		return driver;
	}
}
