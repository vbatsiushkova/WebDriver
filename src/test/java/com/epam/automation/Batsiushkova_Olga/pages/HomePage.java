package com.epam.automation.Batsiushkova_Olga.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class HomePage {
    @FindBy(xpath= "//*[@id='gb']/x:div[1]/x:div[1]/x:div[2]/x:div[4]/x:div[1]/x:a/x:span")
    private WebElement findLinkToUserAccount;

    @FindBy(xpath= "//*[@id='gb']/x:div[1]/x:div[1]/x:div[2]/x:div[4]/x:div[2]/x:div[1]/x:div/x:div[2]")
    private WebElement userLogin;

    public String invokeUserAccount(){
        findLinkToUserAccount.click();
        return  userLogin.getText();
    }

}
