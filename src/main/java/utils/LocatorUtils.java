package utils;

import org.openqa.selenium.By;

public class LocatorUtils {
    public LocatorUtils(){}
    public static By byQa(String value) {
        return By.cssSelector("[data-qa='" + value + "']");
    }
}
