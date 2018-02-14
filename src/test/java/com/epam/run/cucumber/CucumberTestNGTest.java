package com.epam.run.cucumber;

import com.epam.run.helpers.Browser;
import com.epam.run.helpers.BrowserType;
import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Volha_Batsiushkova on 2/9/2018.
 */

@CucumberOptions(strict = true,
		plugin = { "json:target/cucumber-report.json", "html:target/cucumber-report" },
		tags = {"@SmokeTest","@web"},
		features = "src/test/resources/features",
		glue = {  "com.epam.run.steps", "com.epam.run.hooks"})

public class CucumberTestNGTest extends AbstractTestNGCucumberTests
{
	public static WebDriver driver;


	@Before
	public static WebDriver beforeScenario()
	{
		if(driver==null){
			//driver.quit();
			driver = Browser.getBrowser(BrowserType.CHROME);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		return driver;
	}

	@After("@web")
	public static void afterScenario() {
		driver.quit();
	}

}
