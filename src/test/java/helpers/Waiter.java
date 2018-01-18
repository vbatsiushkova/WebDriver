package helpers;

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

		new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOf(webElement));

	}

	public static void waitElementIsAbsent(WebDriver driver, WebElement webElement, String text){

		new WebDriverWait(driver, 40).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(webElement, text)));
	}




	}
