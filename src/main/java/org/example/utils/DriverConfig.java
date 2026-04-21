package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverConfig {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "edge-headless":
                EdgeOptions headlessOptions = new EdgeOptions();
                headlessOptions.addArguments("--headless=new");
                headlessOptions.addArguments("--no-sandbox");
                headlessOptions.addArguments("--disable-dev-shm-usage");
                webDriver = new EdgeDriver(headlessOptions);
                break;

            case "edge":
            default:
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                webDriver = new EdgeDriver(options);
                break;
        }

        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}