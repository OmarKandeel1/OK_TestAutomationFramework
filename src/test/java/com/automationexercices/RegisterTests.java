package com.automationexercices;

import driver.GUIDriver;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.automationexercices.apis.UserManagementAPI;
import pages.com.automationexercices.components.NavigationBarComponent;
import pages.com.automationexercices.pages.SignupLoginPage;
import utils.TimeManager;
import utils.dataReader.JsonReader;

public class RegisterTests extends BaseTests {

    //****************************    Configurations    ****************************//
    @BeforeClass
    public void beforeClass(){
        testData = new JsonReader("test-data/register-data");
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


    //****************************    Tests    *********************************//
    @Epic("Automation Exercise")
    @Feature("UI User Management")
    @Story("Register Tests")
    @Test
    public void signupTC() {
        String email = testData.getJsonData("email")+ TimeManager.getSimpleTimeStamp() + "@gmail.com";
        new SignupLoginPage(gui).navigate()
                .enterSignupEmail(email)
                .enterSignupName(testData.getJsonData("name"))
                .clickSignupButton()
                .fillRegisterationForm(testData.getJsonData("titleMale"),
                        testData.getJsonData("password"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("company"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("postalCode"),
                        testData.getJsonData("mobilePhone")
                )
                .clickCreateAccountButton().
                verifyAccountCreatedByLabel("Account Created!")
                .clickContinureButton();
        new UserManagementAPI().deleteUserAccount(200, email, testData.getJsonData("password"));

    }

    @Epic("Automation Exercise")
    @Feature("UI User Management")
    @Story("Register Tests")
    @Test
    public void ValidateAlreadyRegisteredAcc()
    {




        String email = testData.getJsonData("email")+ TimeManager.getSimpleTimeStamp() + "@gmail.com";
        new UserManagementAPI().createRegisterUserAccount(200,
                testData.getJsonData("name"),
                email,
                testData.getJsonData("password"),
                testData.getJsonData("titleMale"),
                testData.getJsonData("day"),
                testData.getJsonData("month"),
                testData.getJsonData("year"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName"),
                testData.getJsonData("company"),
                testData.getJsonData("address1"),
                testData.getJsonData("address2"),
                testData.getJsonData("country"),
                testData.getJsonData("postalCode"),
                testData.getJsonData("state"),
                testData.getJsonData("city"),
                testData.getJsonData("mobilePhone")
        );

        new SignupLoginPage(gui).navigate()
                .enterSignupEmail(email)
                .enterSignupName(testData.getJsonData("name"))
                .clickSignupButton();
                new SignupLoginPage(gui).verifyRegistrationErrorMessage(testData.getJsonData("messages.emailAlreadyRegistered"));

        new UserManagementAPI().deleteUserAccount(200, email, testData.getJsonData("password"));


    }






}
