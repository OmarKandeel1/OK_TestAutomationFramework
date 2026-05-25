package com.automationexercices;

import driver.GUIDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.com.automationexercices.components.NavigationBarComponent;
import utils.dataReader.JsonReader;

public class PaymentTests extends BaseTests{

    //********************************* Config ************************************//
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("test-data/payment-data");
    }

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



}
