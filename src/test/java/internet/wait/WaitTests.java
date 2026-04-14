package internet.wait;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WaitTests extends BaseTests {

    @Test
    public void testLoadingPage1() {
        var example1Page = homePage.clickDynamicLoadingLink().clickExample1();
        example1Page.clickOnStartButton();
        Assert.assertEquals(example1Page.getHelloWorldText(), "Hello World!", "Loading page error!");

    }
    @Test
    public void testLoadingPage2() {
        var example1Page = homePage.clickDynamicLoadingLink().clickExample2();
        example1Page.clickOnStartButton();
        Assert.assertEquals(example1Page.getHelloWorldText(), "Hello World!", "Loading page error!");

    }
}
