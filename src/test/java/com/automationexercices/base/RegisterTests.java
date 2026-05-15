package com.automationexercices.base;

import driver.GUIDriver;
import org.aspectj.lang.annotation.After;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.automationexercices.components.NavigationBarComponent;
import pages.com.automationexercices.models.ResponseMessage;
import pages.com.automationexercices.pages.SignUpPage;
import pages.com.automationexercices.pages.SignupLoginPage;
import utils.TimeManager;
import utils.api.RestHelper;
import utils.dataReader.PropertyReader;

import java.util.HashMap;
import java.util.Map;

public class RegisterTests extends BaseTests {

    //****************************    Configurations    ****************************//
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
    @Test
    public void signupTC() {
        new SignupLoginPage(gui).navigate()
                .enterSignupEmail(testData.getJsonData("email")+ TimeManager.getSimpleTimeStamp() + "@gmail.com")
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
    }


    @Test
    public void ValidateAlreadyRegisteredAcc()
    {
        Map<String, Object> body = new HashMap<>();
        String email = testData.getJsonData("email")+ TimeManager.getSimpleTimeStamp() + "@gmail.com";
        body.put("name", testData.getJsonData("name"));
        body.put("email", email);
        body.put("password", testData.getJsonData("password"));
        body.put("title", testData.getJsonData("titleMale"));
        body.put("birth_date", testData.getJsonData("day"));
        body.put("birth_month", testData.getJsonData("month"));
        body.put("birth_year", testData.getJsonData("year"));
        body.put("firstname", testData.getJsonData("firstName"));
        body.put("lastname", testData.getJsonData("lastName"));
        body.put("company", testData.getJsonData("company"));
        body.put("address1", testData.getJsonData("address1"));
        body.put("address2", testData.getJsonData("address2"));
        body.put("country", testData.getJsonData("country"));
        body.put("zipcode", testData.getJsonData("postalCode"));
        body.put("state", testData.getJsonData("state"));
        body.put("city", testData.getJsonData("city"));
        body.put("mobile_number", testData.getJsonData("mobilePhone"));

        Map<String, String> headers = new HashMap<>();

        headers.put("Accept", "application/json, application/javascript, text/javascript, text/json");
        headers.put("Content-Type", "application/json");

        RestHelper.sendPostRequestWithHeaders(PropertyReader.getProperty("baseUrlApi") , "createAccount" ,body,headers,201 , ResponseMessage.class);

        new SignupLoginPage(gui).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(email)
                .verifyRegistrationErrorMessage("Email Address already exist!");




    }






}
