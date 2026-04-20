package internet.modals;

import internet.base.BaseTests;
import org.testng.annotations.Test;

public class EntryAddTests extends BaseTests {

    @Test
    public void testClosingModals() {
        var entryAddPage = homePage.clickEntryAddLink();

//        try {
//            Thread.sleep(2000);  // Wait 2 seconds (2000 milliseconds)
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();  // Restore interrupted status (good practice)
//            // or just e.printStackTrace();
//        }
        entryAddPage.closeModal();
        entryAddPage.clickOnLink();


    }
}
