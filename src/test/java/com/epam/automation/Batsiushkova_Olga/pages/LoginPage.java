package com.epam.automation.Batsiushkova_Olga.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Email")
    private WebElement inputEmail;

    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "Passwd")
    private WebElement inputPassword;

    @FindBy(id = "signIn")
    private WebElement signInButton;


    public HomePage signIn(String username, String password) {
        inputEmail.sendKeys(username);
        nextButton.click();
        inputPassword.sendKeys(password);
        signInButton.click();
        return new HomePage();
    }


}
