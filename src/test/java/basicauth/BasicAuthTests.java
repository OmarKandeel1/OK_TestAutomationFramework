package basicauth;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthTests extends BaseTests {


    @Test
    public void testLogin()
    {
        var basicAuthPage = homePage.clickBasicAuthLink();
        basicAuthPage.loginIn("admin","admin");
        Assert.assertTrue(basicAuthPage.getText().contains("Congratulations!"), "Wrong Text!");
    }
}
