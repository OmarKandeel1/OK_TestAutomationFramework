package internet.shadowdom;

import internet.base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShadowDomTests extends BaseTests {

    @Test
    public void validateFirstParagTextNotInShadowDom()
    {
        var shadowDomPage = homePage.clickShadowDOMLink();
        Assert.assertEquals(shadowDomPage.getFirstParagraphTextNotInShadowDom() , "Let's have some different text!" , "Not equal text!");
    }

    @Test
    public void validateFirstParagTextInShadowDom()
    {
        var shadowDomPage = homePage.clickShadowDOMLink();
        Assert.assertEquals(shadowDomPage.getFirstParagraphTextInShadowDom() , "My default text" , "Not equal text!");
    }
}
