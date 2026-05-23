package utils.validations;



import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
import utils.WaitManager;
import utils.logs.LogsManager;

//Soft Assertion
public class Validation extends BaseAssertion {
    private static SoftAssert softAssert = new SoftAssert();
    private static boolean used=false;

    public Validation() {
        super();
    }

    public Validation(WebDriver driver, WaitManager wait) {
        super(driver, wait);
    }


    @Override
    public void assertTrue(boolean condition, String message) {
        used = true;
        softAssert.assertTrue(condition, message);
    }

    @Override
    public void assertFalse(boolean condition, String message) {
        used = true;
        softAssert.assertFalse(condition, message);
    }

    @Override
    public void assertEquals(String actual, String expected, String message) {
        used = true;
        softAssert.assertEquals(actual, expected, message);
    }

    public static void assertAll(ITestResult result){
        if(!used) return;
        try{
            softAssert.assertAll();

        }catch (AssertionError e)
        {
            LogsManager.error("Assertion failed: ",e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        }
        finally {
            softAssert = new SoftAssert();  // Reset the soft assert instance
        }
    }
}
