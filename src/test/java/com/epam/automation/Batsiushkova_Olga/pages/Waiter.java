package com.epam.automation.Batsiushkova_Olga.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Volha_Batsiushkova on 1/9/2018.
 */
public class Waiter
{
	

	public static void wait(WebDriver driver, WebElement webElement){

		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(webElement));

	}
}
