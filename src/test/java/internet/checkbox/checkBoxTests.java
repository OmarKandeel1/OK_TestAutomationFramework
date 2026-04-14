package internet.checkbox;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class checkBoxTests extends BaseTests {

    @Test
    public void checkBoxTest()
    {
       var checkBoxPage = homePage.clickCheckboxesLink();
       checkBoxPage.selectCheckBox(1);
       checkBoxPage.selectCheckBox(2);
        Assert.assertTrue(checkBoxPage.isSelected(1),"Error in the selection!");
        Assert.assertFalse(checkBoxPage.isSelected(2),"Error in the  Not selection!");

    }

}
