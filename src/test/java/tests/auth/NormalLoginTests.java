package tests.auth;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import tests.TestsBase;

public class NormalLoginTests extends TestsBase {

    // <editor-fold desc="Set Up">
    @Override
    protected void setUpPage() {
        homePage.verifyHomePageIsDisplayed();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();
    }
    // </editor-fold>

    // <editor-fold desc="Normal Login Tests">
    @Test(description = "Login with valid credentials", groups = "4. Normal Login Tests")
    @Severity(SeverityLevel.CRITICAL)
    public void validCredentialsLogin() {
        loginPage.validCredentialsLogin();

        learningMaterialsPage.verifyLearningMaterialsPageIsDisplayed();

        learningMaterialsPage.clickLogOut();

        loginPage.verifyLoggedOut();

        loginPage.verifyLoginPageIsDisplayed();
    }

    @Test(description = "Login with valid credentials with whitespace", groups = "4. Normal Login Tests", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void validCredentialsWithWhitespaceLogin() {
        loginPage.validCredentialsWithWhitespaceLogin();

        learningMaterialsPage.verifyLearningMaterialsPageIsDisplayed();

        learningMaterialsPage.clickLogOut();

        loginPage.verifyLoggedOut();

        loginPage.verifyLoginPageIsDisplayed();
    }

    @Test(description = "Login with valid credentials and logout using switch tabs", groups = "4. Normal Login Tests", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void validCredentialsAndLogoutUsingSwitchTabs() {
        loginPage.validCredentialsLogin();

        learningMaterialsPage.verifyLearningMaterialsPageIsDisplayed();

        // the user should be logged out when the tab is changed
        learningMaterialsPage.clickAboutUsPage();

        loginPage.verifyLoggedOut();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();
    }

    @Test(description = "Login with valid credentials and logout using reload", groups = "4. Normal Login Tests", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void validCredentialsAndLogoutUsingReload() {
        loginPage.validCredentialsLogin();

        learningMaterialsPage.verifyLearningMaterialsPageIsDisplayed();

        homePage.reloadPage();

        loginPage.verifyLoggedOut();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();
    }
    // </editor-fold>

}
