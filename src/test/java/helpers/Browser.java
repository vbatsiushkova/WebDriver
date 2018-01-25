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
	public static RemoteWebDriver getDriver(String browser) throws MalformedURLException
	{
		return new RemoteWebDriver(new URL("http://localhost:4443/wd/hub"), getBrowser(browser));
	}

	private static MutableCapabilities getBrowser(String browserType)
	{
		switch (browserType)
		{
		case "firefox":
			FirefoxOptions firefoxDriver = new FirefoxOptions();
			return firefoxDriver;
		case "chrome":
			ChromeOptions chromeDriver = new ChromeOptions();
			return chromeDriver;
		default:
			ChromeOptions driver = new ChromeOptions();
			return driver;
		}
	}
}
