package tests.auth;

import org.testng.annotations.Test;
import tests.TestsBase;

public class LoginTests extends TestsBase {

    // <editor-fold desc="Set Up">
    @Test(description = "Navigate to Login Page", groups = "2. Login Page")
    public void navigateToLoginPage() {
        homePage.verifyHomePageIsDisplayed();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();
    }
    // </editor-fold>

    // <editor-fold desc="Negative Tests">
    @Test(description = "Submission of a blank login form", groups = "2. Login Page", dependsOnMethods = "navigateToLoginPage")
    public void blankLoginFormSubmission() {
        loginPage.blankFormSubmission();
    }

    @Test(description = "Login using invalid credentials", groups = "2. Login Page", dependsOnMethods = "navigateToLoginPage", priority = 3)
    public void invalidCredentialsLogin() {
        loginPage.invalidCredentialsLogin();
    }
    // </editor-fold>

}
