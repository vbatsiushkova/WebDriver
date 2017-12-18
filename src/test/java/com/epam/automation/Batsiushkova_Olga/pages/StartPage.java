package com.epam.automation.Batsiushkova_Olga.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class StartPage {
    public static final String BASE_URL ="https://www.google.by";
    private WebDriver driver;

    public StartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(xpath="//*[@id='gb_70']")
    private WebElement loginButton;



    public void open(){
        driver.get(BASE_URL);
    }

    public LoginPage invokeSignIn(){
     loginButton.click();
        return new LoginPage(driver);
    }


}
