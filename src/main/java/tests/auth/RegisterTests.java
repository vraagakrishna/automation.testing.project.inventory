package tests.auth;

import org.testng.annotations.Test;
import tests.TestsBase;

public class RegisterTests extends TestsBase {

    // <editor-fold desc="Set Up">
    @Test(description = "Navigate to Login Page", groups = "3. Register")
    public void navigateToLoginPage() {
        homePage.verifyHomePageIsDisplayed();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();
    }

    @Test(description = "Click on Register button", groups = "3. Register", dependsOnMethods = "navigateToLoginPage")
    public void clickRegister() {
        loginPage.clickRegisterButton();
    }
    // </editor-fold>

    // <editor-fold desc="Register Tests">
    @Test(description = "Verify that Register Page is displayed", groups = "3. Register", dependsOnMethods = "clickRegister")
    public void verifyRegisterPageIsDisplayed() {
        registerPage.verifyRegisterPageIsDisplayed();
    }

    @Test(description = "Submission of a blank register form", groups = "3. Register", dependsOnMethods = "verifyRegisterPageIsDisplayed", priority = 2)
    public void registerWithBlankForm() {
        registerPage.registerWithBlankForm();
    }

    @Test(description = "Register with first name only", groups = "3. Register", dependsOnMethods = "verifyRegisterPageIsDisplayed", priority = 3)
    public void registerWithFirstNameOnly() {
        registerPage.registerWithFirstNameOnly();
    }

    @Test(description = "Register with first name and last name only", groups = "3. Register", dependsOnMethods = "verifyRegisterPageIsDisplayed", priority = 4)
    public void registerWithFirstAndLastNameOnly() {
        registerPage.registerWithFirstAndLastNameOnly();
    }

    @Test(description = "Register with invalid email address", groups = "3. Register", dependsOnMethods = "verifyRegisterPageIsDisplayed", priority = 5)
    public void registerWithInvalidEmail() {
        registerPage.registerWithInvalidEmail();
    }

    @Test(description = "Register with invalid email address and invalid password", groups = "3. Register", dependsOnMethods = "verifyRegisterPageIsDisplayed", priority = 6)
    public void registerWithInvalidEmailAndInvalidPassword() {
        registerPage.registerWithInvalidEmailAndInvalidPassword();
    }

    @Test(description = "Register with invalid email address, password and confirm password", groups = "3. Register", dependsOnMethods = "verifyRegisterPageIsDisplayed", priority = 7)
    public void registerWithInvalidEmailAndInvalidPasswordAndConfirmPassword() {
        registerPage.registerWithInvalidEmailAndInvalidPasswordAndConfirmPassword();
    }

    @Test(description = "Register with valid email address and invalid password", groups = "3. Register", dependsOnMethods = "verifyRegisterPageIsDisplayed", priority = 8)
    public void registerWithValidEmailndInvalidPassword() {
        registerPage.registerWithValidEmailAndInvalidPassword();
    }

    @Test(description = "Register with valid credentials", groups = "3. Register", dependsOnMethods = "verifyRegisterPageIsDisplayed", priority = 9)
    public void registerWithValidCredentials() {
        registerPage.registerWithValidCredentials();
    }
    // </editor-fold>

}
