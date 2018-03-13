package com.epam.run.helpers;

import com.epam.run.reporting.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by Volha_Batsiushkova on 1/25/2018.
 */
public class Browser
{
	private static WebDriver driver;
	private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

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
