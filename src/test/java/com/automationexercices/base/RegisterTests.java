package com.automationexercices.base;

import driver.GUIDriver;
import org.aspectj.lang.annotation.After;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.automationexercices.components.NavigationBarComponent;

public class RegisterTests extends BaseTests {

    //****************************    Configurations    ****************************//
    @BeforeMethod
    public void setUp() {
        gui = new GUIDriver();
        new NavigationBarComponent(gui).navigate();
    }

    @AfterMethod()
    public void tearDown() {
        if (gui != null) {
            gui.quit();
        }
    }

    //**************************************************************************//


    //****************************    Tests    *********************************//
    @Test
    public void signupTC() {

    }


}
