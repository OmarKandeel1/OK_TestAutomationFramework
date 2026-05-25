package utils.validations;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.WaitManager;

//Hard Assertions
public class Verification extends BaseAssertion{

    public Verification()
    {
        super();
    }

    public Verification(WebDriver driver , WaitManager wait) {
        super(driver,wait );
    }

    @Override
    public Verification assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition , message);
        return this;
    }

    @Override
    public Verification assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
        return this;

    }

    @Override
     public Verification assertEquals(String actual, String expected, String message) {        Assert.assertEquals(actual, expected, message);
        return this;
    }
}
