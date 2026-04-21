package org.example.pages;

import io.qameta.allure.Step;
import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(css = "[data-test='register-username-input']")
    private WebElement usernameInput;

    @FindBy(css = "[data-test='register-email-input']")
    private WebElement emailInput;

    @FindBy(css = "[data-test='register-password-input']")
    private WebElement passwordInput;

    @FindBy(css = "[data-test='register-confirm-password-input']")
    private WebElement confirmPasswordInput;

    @FindBy(css = "[data-test='register-submit-button']")
    private WebElement submitButton;

    @FindBy(css = "[data-test='register-error-message']")
    private WebElement errorMessage;

    @FindBy(css = "[data-test='register-success-message']")
    private WebElement successMessage;

    @FindBy(linkText = "Login")
    private WebElement loginLink;


    @Step("visiter register page")
    public RegisterPage visit() {
        visit("/register");
        return this;
    }

    @Step("clique bouton login")
    public void clickLoginLink() {
        click(loginLink);
    }

    @Step("tapper l'username: {username}")
    public RegisterPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    @Step("tapper l'email: {email}")
    public RegisterPage enterEmail(String email) {
        type(emailInput, email);
        return this;
    }

    @Step("tapper le password")
    public RegisterPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    @Step("re-tapper le password")
    public RegisterPage enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordInput, confirmPassword);
        return this;
    }

    @Step("cliquer bouton submit ")
    public RegisterPage clickSubmit() {
        click(submitButton);
        return this;
    }


    @Step("Register utilisateur avec username={username}, email={email}")
    public RegisterPage register(String username, String email,
                                 String password, String confirmPassword) {

        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickSubmit();

        return this;
    }


    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public boolean isSuccessDisplayed() {
        return isDisplayed(successMessage);
    }
}