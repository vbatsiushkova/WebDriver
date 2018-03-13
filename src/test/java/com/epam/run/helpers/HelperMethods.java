package com.epam.run.helpers;

import com.epam.run.reporting.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.run.tests.GoogleMailSentTest.driver;

/**
 * Created by Volha_Batsiushkova on 1/13/2018.
 */
public class HelperMethods
{
	public static final String SCREENSHOTS_NAME_TPL = "screenshot";

	public String generateString()
	{
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder stringBuilder = new StringBuilder();
		Random rnd = new Random();
		while (stringBuilder.length() < 10)
		{ // length of the random string.
			int index = (int) (rnd.nextFloat() * chars.length());
			stringBuilder.append(chars.charAt(index));
		}
		String charStr = stringBuilder.toString();
		return charStr;
	}

	public String parsingString(String stringValue)
	{
		String value = null;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(stringValue);
		while (m.find())
		{
			value = m.group();
		}
		return value;
	}

	public boolean isElementPresent(WebElement locator) throws InterruptedException
	{

		boolean succeed = locator.isDisplayed();
		if (succeed == true) {
			Log.info("Element " + locator.getText() + " is present.");
			highlightElement(locator, 500);
		} else Log.error("Element " + locator.getText() + " is not present.");
		return succeed;
	}
	
	private void takeScreenshot() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenShotPath = "./target/screenshots/" + SCREENSHOTS_NAME_TPL + System.nanoTime() + ".png" ;
		try {
			FileUtils.copyFile(screenshot, new File(screenShotPath));
			Log.info("Saved screenshot: " + screenShotPath);
		} catch (IOException e) {
			Log.error("Failed to make screenshot");
		}
	}

	private void highlightElement(WebElement element, int duration) throws InterruptedException
	{
		String bg = element.getCssValue("backgroundColor");
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + "green" + "'", element);
		Thread.sleep(duration);
		takeScreenshot();
		js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);

	}
	
}
