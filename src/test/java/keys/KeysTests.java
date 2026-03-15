package keys;

import base.BaseTests;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.KeyPressesPage;

public class KeysTests extends BaseTests {

    @Test
    public void testBackSpace()
    {
        KeyPressesPage keyPressesPage = homePage.clickKeyPressLink();
        keyPressesPage.setText(""+Keys.BACK_SPACE);
        Assert.assertEquals(keyPressesPage.getEnteredText(),"You entered: BACK_SPACE","Wrong Pressed key!");
    }

}
