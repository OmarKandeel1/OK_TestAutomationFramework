package com.automationexercices.base;

import driver.GUIDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.automationexercices.apis.UserManagementAPI;
import pages.com.automationexercices.components.NavigationBarComponent;
import pages.com.automationexercices.models.UserResponseModel;
import pages.com.automationexercices.pages.SignupLoginPage;
import utils.TimeManager;
import utils.dataReader.JsonReader;
import utils.validations.Verification;

public class LoginTests extends BaseTests {

    Verification verification = new Verification();

    //****************************    Configurations    ****************************//
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("test-data/login-data");
    }

    @BeforeMethod
    public void setUp() {
        gui = new GUIDriver();
        new NavigationBarComponent(gui).navigate();
    }

    @AfterMethod()
    public void tearDown() {
        if (gui != null) {
            gui.quit();
        }
    }

    //**************************************************************************//

    @Epic("Automation Exercise")
    @Feature("UI User Management")
    @Story("Login Tests")
    @Description("Verify Valid login")
    @Test
    public void verifyValidLogin() {
        String email = testData.getJsonData("email") + TimeManager.getSimpleTimeStamp() + "@gmail.com";
        UserResponseModel response =  new UserManagementAPI().createRegisterUserAccount(200, testData.getJsonData("name"), email, testData.getJsonData("password"), testData.getJsonData("firstName"), testData.getJsonData("lastName"));
        verification.assertEquals(response.message,"User created!","Error in the response msg!");

        new SignupLoginPage(gui).navigate()
                .enterLoginEmail(email)
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton();
        new NavigationBarComponent(gui).verifyUserLabelVisible(testData.getJsonData("name"));

        new UserManagementAPI().deleteUserAccount(200, email, testData.getJsonData("password"));
    }

    @Epic("Automation Exercise")
    @Feature("UI User Management")
    @Story("Login Tests")
    @Description("Verify invalid login with invalid email")
    @Test
    public void verifyInvalidLoginWithInvalidEmail() {
        String email = testData.getJsonData("email") + TimeManager.getSimpleTimeStamp() + "@gmail.com";
        new UserManagementAPI().createRegisterUserAccount(200, testData.getJsonData("name"), email, testData.getJsonData("password"), testData.getJsonData("firstName"), testData.getJsonData("lastName"));
        new SignupLoginPage(gui).navigate()
                .enterLoginEmail(email+1)
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton().verifyLoginErrorMessage(testData.getJsonData("messages.error"));


        new UserManagementAPI().deleteUserAccount(200, email, testData.getJsonData("password"));
    }

    @Epic("Automation Exercise")
    @Feature("UI User Management")
    @Story("Login Tests")
    @Description("Verify invalid login with invalid Password")
    @Test
    public void verifyInvalidLoginWithInvalidPassword() {
        String email = testData.getJsonData("email") + TimeManager.getSimpleTimeStamp() + "@gmail.com";
        new UserManagementAPI().createRegisterUserAccount(200, testData.getJsonData("name"), email, testData.getJsonData("password"), testData.getJsonData("firstName"), testData.getJsonData("lastName"));
        new SignupLoginPage(gui).navigate()
                .enterLoginEmail(email)
                .enterLoginPassword(testData.getJsonData("password")+1)
                .clickLoginButton().clickLoginButton().verifyLoginErrorMessage(testData.getJsonData("messages.error"));


        new UserManagementAPI().deleteUserAccount(200, email, testData.getJsonData("password"));
    }


}
