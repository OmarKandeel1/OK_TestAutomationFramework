package com.automationexercices;

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


    @Override
    public WebDriver getWebDriver() {
        return gui.get();
    }
}
