package basicauth;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonReader;
import utils.PropertyReader;

public class BasicAuthTests extends BaseTests {


    @Test
    public void testLogin()
    {
        var basicAuthPage = homePage.clickBasicAuthLink();
        basicAuthPage.loginIn(jsonReader.getJsonData("username"), jsonReader.getJsonData("password"));
        Assert.assertTrue(basicAuthPage.getText().contains("Congratulations!"), "Wrong Text!");
    }
}
