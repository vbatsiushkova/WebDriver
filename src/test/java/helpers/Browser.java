package helpers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Volha_Batsiushkova on 1/25/2018.
 */
public class Browser
{
	private static RemoteWebDriver driver;

	private Browser(){}

	public static RemoteWebDriver getDriver(BrowserType name) throws MalformedURLException
	{
		if (driver == null)
		{
			driver = new RemoteWebDriver(new URL("http://localhost:4442/wd/hub"), getBrowser(BrowserType.CHROME));
		}
	return driver;
	}

	private static MutableCapabilities getBrowser(BrowserType name)
	{
		switch (name)
		{
		case FIREFOX:
			FirefoxOptions firefoxDriver = new FirefoxOptions();
			return firefoxDriver;
		case CHROME:
			ChromeOptions chromeDriver = new ChromeOptions();
			return chromeDriver;
		default:
			ChromeOptions defaultDriver = new ChromeOptions();
			return defaultDriver;

		}
	}

}
