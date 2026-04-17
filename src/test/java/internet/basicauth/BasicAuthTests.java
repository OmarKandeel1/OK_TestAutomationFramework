package internet.basicauth;

import base.BaseTests;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthTests extends BaseTests {


    @Test
    @Description("Verify that the user is redirected to login page")
    @Tag("validLogin")
    @Owner("OmarKandeel")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogin()
    {
        var basicAuthPage = homePage.clickBasicAuthLink();
      //  basicAuthPage.loginIn(jsonReader.getJsonData("username"), jsonReader.getJsonData("password"));
        Assert.assertTrue(basicAuthPage.getText().contains("Congratulations!"), "Wrong Text!");
    }
}
