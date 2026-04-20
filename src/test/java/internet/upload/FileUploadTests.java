package internet.upload;

import internet.base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUploadTests extends BaseTests {

    @Test
    public void testFileUpload() {

        var fileUploadPage = homePage.clickFileUploadLink();
        fileUploadPage.uploadFile("C:\\Users\\shady gamal\\IdeaProjects\\FirstSeleniumWebDriverFW\\resources\\omar.txt");
        fileUploadPage.clickOnUploadButton();
        Assert.assertTrue(fileUploadPage.getUploadedFileText().contains("omar.txt"), "Wrong File Upload!");
    }



}
