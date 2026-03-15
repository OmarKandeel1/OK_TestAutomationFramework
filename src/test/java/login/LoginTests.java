package login;

import base.BaseTests;
import org.testng.Assert;
import org.testng.Assert.*;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

public class LoginTests extends BaseTests {
    @Test
    public void testSuccessfullLogin() {
        LoginPage loginPage = homePage.clickFormAuth();
        loginPage.setUserName("tomsmith");
        loginPage.setPassword("SuperSecretPassword!");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        Assert.assertTrue(secureAreaPage.getWelcomeMessage().contains("You logged into a secure area!"), "The message is Incorrect!");

    }


}
