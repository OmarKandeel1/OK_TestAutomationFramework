package com.automationexercices.base;

import customlisteners.TestNGListeners;
import driver.GUIDriver;
import driver.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;

@Listeners(TestNGListeners.class)
public class BaseTests implements WebDriverProvider {
    protected GUIDriver gui;


//    @BeforeClass(alwaysRun = true)
//    public void setUp() {
//        gui = new GUIDriver();
//    }
//
//
//    @AfterClass(alwaysRun = true)
//    public void tearDown() {
//        if (gui != null) {
//            gui.quit();
//        }
//    }

    @Override
    public WebDriver getWebDriver() {
        return gui.get();
    }
}
