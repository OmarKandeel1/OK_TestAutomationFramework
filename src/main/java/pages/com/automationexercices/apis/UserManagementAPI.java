package pages.com.automationexercices.apis;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pages.com.automationexercices.models.UserResponseModel;
import utils.api.RestHelper;
import utils.dataReader.JsonReader;
import utils.dataReader.PropertyReader;

import java.util.HashMap;
import java.util.Map;

public class UserManagementAPI {

    // ENDPOINTS
    private static final String BASE_URL = PropertyReader.getProperty("baseUrlApi");
    private static final String CREATE_ACCOUNT_ENDPOINT = "/createAccount";
    private static final String DELETE_ACCOUNT_ENDPOINT = "/deleteAccount";
    private static final String LOGIN_ACCOUNT_ENDPOINT = "/verifyLogin";
    //************************************************************************************//


    // Create Account api methods
    @Step("Create User Account with full details")
    public UserManagementAPI createRegisterUserAccount(int status, String name, String email, String password, String title, String birth_date, String birth_month, String birth_year, String firstname, String lastname, String company, String address1, String address2, String country, String zipcode, String state, String city, String mobile_number) {
        java.util.HashMap<String, String> accountData = new java.util.HashMap<>();
        accountData.put("name", name);
        accountData.put("email", email);
        accountData.put("password", password);
        accountData.put("title", title);
        accountData.put("birth_date", birth_date);
        accountData.put("birth_month", birth_month);
        accountData.put("birth_year", birth_year);
        accountData.put("firstname", firstname);
        accountData.put("lastname", lastname);
        accountData.put("company", company);
        accountData.put("address1", address1);
        accountData.put("address2", address2);
        accountData.put("country", country);
        accountData.put("zipcode", zipcode);
        accountData.put("state", state);
        accountData.put("city", city);
        accountData.put("mobile_number", mobile_number);
        RestHelper.sendPostRequestWithFormParams(BASE_URL, CREATE_ACCOUNT_ENDPOINT, accountData, null, status, UserResponseModel.class);

        return this;
    }

    // Create Account api methods
    @Step("Create User Account with partial details")
    public UserResponseModel createRegisterUserAccount(int status, String name, String email, String password, String firstName, String lastName) {
        java.util.HashMap<String, String> accountData = new java.util.HashMap<>();
        accountData.put("name", name);
        accountData.put("email", email);
        accountData.put("password", password);
        accountData.put("title", "Mr");
        accountData.put("birth_date", "13");
        accountData.put("birth_month", "9");
        accountData.put("birth_year", "2002");
        accountData.put("firstname", firstName);
        accountData.put("lastname", lastName);
        accountData.put("company", "iti");
        accountData.put("address1", "address1");
        accountData.put("address2", "address2");
        accountData.put("country", "India");
        accountData.put("zipcode", "123456");
        accountData.put("state", "Cairo");
        accountData.put("city", "city");
        accountData.put("mobile_number", "01014241624");
       return RestHelper.sendPostRequestWithFormParams(BASE_URL, CREATE_ACCOUNT_ENDPOINT, accountData, null, status, UserResponseModel.class);
    }

    @Step ("Delete User Account with email: {email} , {password}")
    public UserManagementAPI deleteUserAccount(int status, String email, String password) {
        java.util.HashMap<String, String> accountData = new java.util.HashMap<>();
        accountData.put("email", email);
        accountData.put("password", password);
        RestHelper.sendDeleteRequestWithFormParams(BASE_URL, DELETE_ACCOUNT_ENDPOINT, accountData, null, 200 , UserResponseModel.class );
        return this;
    }


    @Step("Login using user email: {email} and pass: {pass}")
    public UserManagementAPI loginToUserAccount(int status , String email, String pass)
    {
        HashMap<String, String> accountData = new HashMap<>();
        accountData.put("email", email);
        accountData.put("password", pass);
        RestHelper.sendPostRequestWithFormParams(BASE_URL , LOGIN_ACCOUNT_ENDPOINT , accountData,null ,status ,UserResponseModel.class  );
        return this;
    }


}
