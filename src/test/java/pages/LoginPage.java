package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.*;

import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage {

    // <editor-fold desc="Class Fields / Constants">
    private static final Logger logger = LoggingManager.getLogger(LoginPage.class.getName());

    private static ScreenshotUtils screenshotUtils;

    private final WebDriver driver;

    private final AlertUtils alertUtils;

    private final UserTestData userTestData;

    @FindBy(id = "login-heading")
    WebElement loginHeading_id;

    @FindBy(id = "login-email")
    WebElement emailField_id;

    @FindBy(id = "login-password")
    WebElement passwordField_id;

    @FindBy(id = "login-submit")
    WebElement loginButton_id;

    @FindBy(id = "signup-toggle")
    WebElement registerButton_id;
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public LoginPage(WebDriver driver) throws IOException {
        this.driver = driver;
        this.alertUtils = new AlertUtils(driver);
        this.userTestData = new UserTestData();
        screenshotUtils = new ScreenshotUtils();
    }
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public boolean isLoginFormVisible() {
        try {
            return loginHeading_id.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void verifyLoginPageIsDisplayed() {
        String expectedHeading = "Login to Access Learning Materials";
        logger.info("Waiting for Login Page to be visible");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(loginHeading_id));

        String heading = loginHeading_id.getText();
        logger.info(String.format("Heading found: %s", heading));

        Assert.assertEquals(heading, expectedHeading, "Heading does not match");

        screenshotUtils.captureAndAttach(driver, "Verifying login page is displayed");
    }

    public void blankFormSubmission() {
        String expectedMessage = "Please enter both email and password";
        logger.info("Submission of a blank login form");

        this.clearLoginForm();

        screenshotUtils.captureAndAttach(driver, "Submission of blank form");

        this.clickLoginButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);
    }

    public void invalidCredentialsLogin() {
        String expectedMessage = "Invalid email or password";
        logger.info("Invalid credentials login");

        this.clearLoginForm();

        this.enterEmailAddress("123");
        this.enterPassword("123");

        screenshotUtils.captureAndAttach(driver, "Submission with invalid credentials");

        this.clickLoginButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);
    }

    public void clickRegisterButton() {
        registerButton_id.click();
    }

    public void checkPrePopulatedEmailAddress() {
        String emailAddress = getEmailAddress();
        Assert.assertEquals(emailAddress, UserTestData.getUniqueEmail(), "Email address is not pre-populated");
    }

    public void enterValidPasswordAfterRegistration() {
        logger.info("Valid credentials login");

        // Note: email address is pre-populated
        this.enterPassword(UserTestData.getPassword());

        this.clickLoginButton();
    }

    public void verifyLoggedOut() {
        JavascriptExecutorUtils storage = new JavascriptExecutorUtils(driver);

        logger.info("Checking if token exists in local storage");
        String token = storage.getLocalStorageItem("authToken");

        logger.info(String.format("Token from localStorage: %s", token));

        Assert.assertNull(token, "Auth token is present");

        screenshotUtils.captureAndAttach(driver, "Verify user is logged out");
    }

    public void validCredentialsLogin() {
        logger.info("Valid credentials login");

        this.clearLoginForm();

        this.enterEmailAddress(UserTestData.getUniqueEmail());
        this.enterPassword(UserTestData.getPassword());

        screenshotUtils.captureAndAttach(driver, "Submission with valid credentials");

        this.clickLoginButton();
    }

    public void validCredentialsWithWhitespaceLogin() {
        logger.info("Valid credentials login");

        this.clearLoginForm();

        this.enterEmailAddress(String.format("   %s   ", UserTestData.getUniqueEmail()));
        this.enterPassword(String.format("   %s   ", UserTestData.getPassword()));

        screenshotUtils.captureAndAttach(driver, "Submission with valid credentials with whitespace");

        this.clickLoginButton();
    }

    public void clearLoginForm() {
        this.enterEmailAddress("");
        this.enterPassword("");
    }
    // </editor-fold>

    // <editor-fold desc="Private Methods">
    private void enterEmailAddress(String emailAddress) {
        emailField_id.clear();
        emailField_id.sendKeys(emailAddress);
    }

    private void enterPassword(String password) {
        passwordField_id.clear();
        passwordField_id.sendKeys(password);
    }

    private void clickLoginButton() {
        loginButton_id.click();
    }

    private String getEmailAddress() {
        return emailField_id.getDomProperty("value");
    }
    // </editor-fold>

}
