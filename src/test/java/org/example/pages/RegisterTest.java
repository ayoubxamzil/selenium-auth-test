package org.example.pages;

import io.qameta.allure.*;
import org.example.base.BaseTest;
import org.example.utils.ConfigReader;
import org.example.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("authentication")
@Feature("register")
public class RegisterTest extends BaseTest {

    @DataProvider(name = "registerValidPaths")
    public Object[][] registerValidPaths() {
        return ExcelUtil.getData(ConfigReader.get("EXCEL_PATH"), "registerValid");
    }

    @DataProvider(name = "registerInvalidPaths")
    public Object[][] registerInvalidPaths() {
        return ExcelUtil.getData(ConfigReader.get("EXCEL_PATH"), "registerInvalid");
    }

    @Test(priority = 1, dataProvider = "registerValidPaths")
    @Story("register valide")
    @Severity(SeverityLevel.CRITICAL)
    @Description("vérifier qu'un utilisateur peut créer un compte avec des données valides")
    public void testRegisterSuccess(String username, String email,
                                    String password, String confirmPassword,
                                    String testCase) {

        RegisterPage registerPage = new RegisterPage();

        registerPage.visit();
        registerPage.register(username, email, password, confirmPassword);

        Assert.assertTrue(
                registerPage.isSuccessDisplayed(),
                "le message de succès doit s'afficher"
        );

        Assert.assertEquals(
                registerPage.getSuccessMessage(),
                "Account created successfully",
                "le message de succès est incorrect"
        );
    }

    @Test(priority = 2, dataProvider = "registerInvalidPaths")
    @Story("register invalide")
    @Severity(SeverityLevel.NORMAL)
    @Description("vérifier que l'utilisateur ne peut pas s'inscrire avec des données invalides")
    public void testRegisterInvalid(String username, String email,
                                    String password, String confirmPassword,
                                    String expectedResult, String testCase) {

        RegisterPage registerPage = new RegisterPage();

        registerPage.visit();
        registerPage.register(username, email, password, confirmPassword);

        Assert.assertTrue(
                registerPage.isErrorDisplayed(),
                "un message d'erreur doit s'afficher"
        );

        Assert.assertEquals(
                registerPage.getErrorMessage(),
                expectedResult,
                "message d'erreur incorrect"
        );
    }

    @Test(priority = 3)
    @Story("navigation vers login page")
    @Severity(SeverityLevel.MINOR)
    @Description("vérifier la redirection de register vers login page")
    public void testNavigationToLogin() {

        RegisterPage registerPage = new RegisterPage();

        registerPage.visit();
        registerPage.clickLoginLink();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "le lien Login doit rediriger vers /login"
        );
    }
}