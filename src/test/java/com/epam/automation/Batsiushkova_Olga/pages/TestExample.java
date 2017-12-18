package com.epam.automation.Batsiushkova_Olga.pages;

import com.sun.org.glassfish.gmbal.Description;
import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Volha_Batsiushkova on 11/24/2016.
 */
public class TestExample {
    public static final String BASE_URL ="https://www.google.by";
        private final String USERNAME1 = "OBVTest0";
        private final String USERNAME2 = "OBVTest1";
        private final String PASSWORD = "12345678OB";


        @Test
        @Description("OpenloginPage")
        public void openLoginPage() {
            System.setProperty("webdriver.chrome.driver", "c://Users//Volha_Batsiushkova//Downloads//chromedriver");
            WebDriver driver = new ChromeDriver();
       //     driver.get(BASE_URL);

            StartPage startPage = new StartPage(driver);
            startPage.open();
            LoginPage signInPage = startPage.invokeSignIn();
            HomePage homePage = signInPage.signIn(USERNAME1, PASSWORD);
            HomePage userAccount = new HomePage();
            String userAccountName = userAccount.invokeUserAccount();
            Assert.assertEquals(userAccountName, USERNAME1);

        }
}
