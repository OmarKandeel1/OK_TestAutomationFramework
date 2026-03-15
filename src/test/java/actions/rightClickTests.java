package actions;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class rightClickTests extends BaseTests {


    @Test
    public void validateRightClick()
    {
        var contextMenuPage = homePage.clickContextMenuPageLink();
        contextMenuPage.rightClickOnBox();
        Assert.assertTrue(contextMenuPage.isAlertPresent(),"Alert is not appeared");
        Assert.assertEquals(contextMenuPage.getAlertText() , "You selected a context menu" , "Wrong text on JS!");
        contextMenuPage.closeJavaAlert();
        Assert.assertFalse(contextMenuPage.isAlertPresent(),"Alert is not appeared");
    }
}
