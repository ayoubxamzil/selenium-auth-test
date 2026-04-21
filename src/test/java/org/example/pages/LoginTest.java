package org.example.pages;

import io.qameta.allure.*;
import org.example.base.BaseTest;
import org.example.utils.ConfigReader;
import org.example.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("authentication")
@Feature("login")
public class LoginTest extends BaseTest {

    @DataProvider(name = "loginValidPaths")
    public Object[][] loginValidPaths() {
        return ExcelUtil.getData(ConfigReader.get("EXCEL_PATH"), "loginValid");
    }

    @DataProvider(name = "loginInvalidPaths")
    public Object[][] loginInvalidPaths() {
        return ExcelUtil.getData(ConfigReader.get("EXCEL_PATH"), "loginInvalid");
    }

    @Test(priority = 1, dataProvider = "loginValidPaths")
    @Story("login valide")
    @Description("vérifier que l'utilisateur peut se connecter avec des informations valides")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginSuccess(String email, String password, String expectedResult, String testCase) {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboard = new DashboardPage();

        loginPage.visit();
        loginPage.login(email, password);

        Assert.assertTrue(
                dashboard.isOnDashboard(),
                "l'utilisateur doite etre redirigé vers /dashboard"
        );
        Assert.assertTrue(
                dashboard.getWelcomeTitle().contains("Welcome"),
                "le titre doit contenir 'Welcome'"
        );
    }

    @Test(priority = 2, dataProvider = "loginInvalidPaths")
    @Story("login invalide")
    @Severity(SeverityLevel.CRITICAL)
    @Description("vérifier que l'utilisateur ne peut pas se connecter avec des informations non valides")
    public void testLoginInvalid(String email, String password,
                                 String expectedResult, String testCase) {
        LoginPage loginPage = new LoginPage();

        loginPage.visit();
        loginPage.login(email, password);

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                expectedResult,
                "message d'erreur incorrect"
        );
    }

    @Test(priority = 3)
    @Story("redirection vers register page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("vérifier la redirection du login au register page ")
    public void testNavigationToRegister() {
        LoginPage loginPage = new LoginPage();

        loginPage.visit();
        loginPage.clickRegisterLink();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/register"),
                "le bouton register doit rediriger vers /register"
        );
    }
}


