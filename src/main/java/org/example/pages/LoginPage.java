package org.example.pages;

import io.qameta.allure.Step;
import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[data-test='login-email-input']")
    private WebElement emailInput;

    @FindBy(css = "[data-test='login-password-input']")
    private WebElement passwordInput;

    @FindBy(css = "[data-test='login-submit-button']")
    private WebElement submitButton;

    @FindBy(css = "[data-test='login-error-message']")
    private WebElement errorMessage;

    @FindBy(linkText = "Register")
    private WebElement registerLink;


    @Step("visiter login page")
    public LoginPage visit() {
        visit("/login");
        return this;
    }

    @Step("cliquer sur le bouton d'inscription")
    public void clickRegisterLink() {
        click(registerLink);
    }

    @Step("tapper l'email : {email}")
    public LoginPage enterEmail(String email) {
        type(emailInput, email);
        return this;
    }

    @Step("tapper le password")
    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    @Step("cliquer sur le bouton de connexion")
    public LoginPage clickSubmit() {
        click(submitButton);
        return this;
    }

    @Step("connexion avec email={email}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmit();
    }


    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public boolean isOnLoginPage() {
        return getCurrentUrl().contains("/login");
    }
}