package navigation;


import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationPageTests extends BaseTests {

    @Test
    public void testNavigationInDynamicPage() {
        homePage.clickDynamicLoadingLink().clickExample1();
        windowManager.goBack();

        windowManager.goToURL("https://www.google.com/?hl=ar");
        windowManager.goBack();

    }

    @Test
    public void testSwitchToTab(){
        var newWindowPage = homePage.clickMultipleWindowLink().clickOnLink();
        windowManager.switchToTab("New Window");
        Assert.assertEquals(newWindowPage.getText(),"New Window","Wrong text on the new Window!");
        Assert.assertNotEquals(newWindowPage.getText(),"Not New Window","Wrong text on the new Window!");
    }
}
