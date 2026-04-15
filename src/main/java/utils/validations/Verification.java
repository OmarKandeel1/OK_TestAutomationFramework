package utils.validations;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.WaitManager;

//Hard Assertions
public class Verification extends BaseAssertion{
    public Verification(WebDriver driver , WaitManager wait) {
        super(driver,wait );
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition , message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
}
