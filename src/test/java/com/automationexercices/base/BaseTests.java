package com.automationexercices.base;

import customlisteners.TestNGListeners;
import driver.GUIDriver;
import driver.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import utils.dataReader.JsonReader;

@Listeners(TestNGListeners.class)
public class BaseTests implements WebDriverProvider {
    protected GUIDriver gui;
    protected JsonReader testData;

@BeforeClass
public void beforeClass(){
    testData = new JsonReader("test-data/register-data");
}

    @Override
    public WebDriver getWebDriver() {
        return gui.get();
    }
}
