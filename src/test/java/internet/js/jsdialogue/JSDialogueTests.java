package internet.js.jsdialogue;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JSDialogueTests extends BaseTests {

    @Test
    public void testAlerts() {
        var javaScriptAlert = homePage.clickJavaScriptAlertLink();
        javaScriptAlert.clickOnAlertButton();
        javaScriptAlert.jsDialogue_clickToAccept();
        Assert.assertEquals(javaScriptAlert.getResultText(), "You successfully clicked an alert", "Wrong result message!");

    }

    @Test
    public void testConfirm(){
        var javaScriptAlert = homePage.clickJavaScriptAlertLink();
        javaScriptAlert.clickOnConfirmButton();
        String text = javaScriptAlert.jsDialogue_getText();
        javaScriptAlert.jsDialogue_clickToDismiss();
        Assert.assertEquals(javaScriptAlert.getResultText(), "You clicked: Cancel" , "Wrong result message!");
        Assert.assertEquals(text,"I am a JS Confirm","Wrong Alert msg!");
    }

    @Test
    public void testPrompt(){
        String input = "OmarKandeel";
        var javaScriptAlert = homePage.clickJavaScriptAlertLink();
        javaScriptAlert.clickOnPromptButton();
        javaScriptAlert.jsDialogue_setText(input);
        javaScriptAlert.jsDialogue_clickToAccept();
        Assert.assertEquals(javaScriptAlert.getResultText(), "You entered: "+input , "Wrong result message!");
    }

}
