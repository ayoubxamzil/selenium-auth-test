package org.example.base;

import org.example.utils.ConfigReader;
import org.example.utils.DriverConfig;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage () {
        this.driver = DriverConfig.getDriver();
        int time = Integer.parseInt(ConfigReader.get("implicit.wait"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        PageFactory.initElements(driver, this);
    }

    protected void waitForVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void visit (String path) {
        String baseURL = ConfigReader.get("base.url");
        driver.get(baseURL + path);
    }

    protected void click(WebElement element) {
        waitForClickable(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        waitForVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        waitForVisible(element);
        return element.getText().trim();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
