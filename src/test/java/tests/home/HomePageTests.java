package tests.home;

import org.testng.annotations.Test;
import tests.TestsBase;

public class HomePageTests extends TestsBase {

    // <editor-fold desc="Home Page Tests">
    @Test(description = "Verify that Home Page is displayed", groups = "1. Home Page")
    public void verifyHomePageIsDisplayed() {
        homePage.verifyHomePageIsDisplayed();
    }

    @Test(description = "Click Learning Material", groups = "1. Home Page", dependsOnMethods = "verifyHomePageIsDisplayed")
    public void clickLearningMaterial() {
        homePage.clickLearningMaterial();
    }

    @Test(description = "Navigate to Login Page", groups = "1. Home Page", dependsOnMethods = "clickLearningMaterial")
    public void verifyLoginPageIsDisplayed() {
        loginPage.verifyLoginPageIsDisplayed();
    }
    // </editor-fold>

}
