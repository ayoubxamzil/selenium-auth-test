package org.example.pages;

import io.qameta.allure.*;
import org.example.base.BaseTest;
import org.example.utils.ConfigReader;
import org.example.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("authentification")
@Feature("dashboard")
public class DashboardTest extends BaseTest {

    @DataProvider(name = "loginValidPaths")
    public Object[][] loginValidPaths() {
        return ExcelUtil.getData(ConfigReader.get("EXCEL_PATH"), "loginValid");
    }

    @Test(priority = 1)
    @Story("logout")
    @Severity(SeverityLevel.BLOCKER)
    @Description("vérifier que l'utilisateur peut se déconnecter et être redirigé vers /login")
    public void testLogoutSuccess() {

        LoginPage loginPage = new LoginPage();
        DashboardPage dashboard = new DashboardPage();

        loginPage.visit();
        loginPage.login("ayoubamzil@email.com", "Password123");

        Assert.assertTrue(
                dashboard.isOnDashboard(),
                "l'utilisateur doit être sur le dashboard après login"
        );

        Assert.assertTrue(
                dashboard.isLogoutButtonVisible(),
                "le bouton Logout doit être visible"
        );

        dashboard.clickLogout();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "l'utilisateur doit être redirigé vers /login"
        );
    }


    @Test(priority = 2, dataProvider = "loginValidPaths")
    @Story("affichage utilisateur")
    @Severity(SeverityLevel.NORMAL)
    @Description("le dashboard affiche un message de bienvenue après connexion")
    public void testDashboardDisplaysWelcomeMessage() {

        LoginPage loginPage = new LoginPage();
        DashboardPage dashboard = new DashboardPage();

        loginPage.visit();
        loginPage.login("valid@email.com", "Password123");

        String title = dashboard.getWelcomeTitle();

        Assert.assertTrue(
                title.contains("Welcome"),
                "le titre doit contenir 'Welcome'"
        );
    }
}