package internet.hovers;

import internet.base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.internet.HoversPage;

public class HoversTests extends BaseTests {

    @Test
    public void testHovers()
    {
        int userIndex = 1;
        HoversPage hoversPage = homePage.clickHoversLink();
        HoversPage.FigureCaption figureCaption = hoversPage.hoverOverFigure(userIndex);
        Assert.assertTrue(figureCaption.isCaptionDisplayed());
        Assert.assertEquals(figureCaption.getfigureText(),"name: user"+userIndex,"Wrong caption Text!");
        Assert.assertEquals(figureCaption.getLinkText(),"View profile","Wrong Link Text!");
        Assert.assertTrue(figureCaption.getLink().endsWith("/users/"+userIndex),"Wrong Link!");





    }


}
