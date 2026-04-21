package org.example.pages;

import io.qameta.allure.Step;
import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    @FindBy(css = "[data-test='dashboard-title']")
    private WebElement dashboardTitle;

    @FindBy(css = "[data-test='logout-button']")
    private WebElement logoutButton;

    @FindBy(css = "[data-test='dashboard-page']")
    private WebElement dashboardPage;


    @Step("visiter la page dashboard")
    public DashboardPage open() {
        visit("/dashboard");
        return this;
    }

    @Step("cliquer sur le bouton de déconnexion")
    public void clickLogout() {
        click(logoutButton);
    }

    @Step("vérifier si l'utilisateur est sur le dashboard")
    public boolean isOnDashboard() {
        try {
            wait.until(ExpectedConditions.urlContains("/dashboard"));
            return driver.getCurrentUrl().contains("/dashboard");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("récupérer le titre de bienvenue")
    public String getWelcomeTitle() {
        return getText(dashboardTitle);
    }

    @Step("vérifier si le bouton logout est visible")
    public boolean isLogoutButtonVisible() {
        return isDisplayed(logoutButton);
    }
}