package js.executor;

import base.BaseTests;
import org.testng.annotations.Test;

public class LargeAndDeepDomTests extends BaseTests {

    @Test
    public void testScrollDownUsingJSExecutor()
    {
        var largeDomPage = homePage.clickLargeAndDeepDomLink();
        largeDomPage.scrollToTable();
    }
}
