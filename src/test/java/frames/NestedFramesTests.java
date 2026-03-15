package frames;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NestedFramesTests extends BaseTests {

    @Test
    public void testNestedFrames()
    {
        var framesPage = homePage.clickFramesLink();
        var nestedFramesPage = framesPage.clickOnNestedFrames();
        Assert.assertTrue(nestedFramesPage.getLeftFrameText().contains("LEFT"),"Wrong frame Text!");
        Assert.assertTrue(nestedFramesPage.getBottomFrameText().contains("BOTTOM"),"Wrong frame Text!");
    }

}
