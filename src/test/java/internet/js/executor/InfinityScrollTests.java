package internet.js.executor;

import internet.base.BaseTests;
import org.testng.annotations.Test;

public class InfinityScrollTests extends BaseTests {

    @Test
    public void testParagraphScroll()
    {
        int index = 6;
        var infinityScrollPage = homePage.clickInfiniteScrollLink();
        infinityScrollPage.scrollToParagraph(index);
        System.out.println("Omar:"+infinityScrollPage.getParagraphText(index));


    }
}
