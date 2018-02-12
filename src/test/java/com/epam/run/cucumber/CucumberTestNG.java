package com.epam.run.cucumber;

import com.epam.run.helpers.Browser;
import com.epam.run.helpers.BrowserType;
import cucumber.api.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

/**
 * Created by Volha_Batsiushkova on 2/9/2018.
 */

@CucumberOptions(strict = true, plugin = { "json:target/cucumber-report.json",
		"html:target/cucumber-report" }, tags = "@smokeTest", features = "src/test/resources/features", glue = {  "com.epam.run.steps"})
public class CucumberTestNG
{
	private static WebDriver driver = Browser.getBrowser(BrowserType.CHROME);

	@BeforeClass(description = "Start browser, add implicit wait and maximize window")
	public void startBrowser() {
		// set a certain implicit wait timeout
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Maximize browser window
		driver.manage().window().maximize();
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
	}

}
