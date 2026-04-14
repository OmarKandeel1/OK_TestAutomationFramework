package internet.dropdown;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.internet.DropDownPage;

import java.util.List;

public class DropDownTests extends BaseTests {

    @Test
    public void testDropDown() {
        String option = "Option 1";
       DropDownPage dropDownPage = homePage.clickDropDown();
       dropDownPage.selectFromDropDown("1");
       List<String> selectedOptions = dropDownPage.getSelectedOptionsText();
       Assert.assertTrue(selectedOptions.size()==1,"Too many selections!");
       Assert.assertTrue(selectedOptions.contains(option),"Wrong choosen option!");
       Assert.assertFalse(dropDownPage.getMultipleState());


    }
}
