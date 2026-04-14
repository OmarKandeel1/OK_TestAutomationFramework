package internet.draganddrop;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTests extends BaseTests {


    @Test
    public void testDragAndDrop() {

        var dragAndDropPage = homePage.clickDragAndDropPageLink();
        Assert.assertEquals(dragAndDropPage.getColumnText(),"A");
        dragAndDropPage.dragAndDrop();
        Assert.assertEquals(dragAndDropPage.getColumnText(),"B");


    }
}
