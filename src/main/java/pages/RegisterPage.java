package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.AlertUtils;
import utils.ScreenshotUtils;
import utils.UserTestData;

import java.time.Duration;
import java.util.logging.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RegisterPage {

    // <editor-fold desc="Class Fields / Constants">
    private static final Logger logger = Logger.getLogger(RegisterPage.class.getName());

    private static ScreenshotUtils screenshotUtils;

    private final WebDriver driver;

    private final AlertUtils alertUtils;

    @FindBy(id = "registration-heading")
    WebElement registrationHeading_id;

    @FindBy(id = "register-firstName")
    WebElement firstNameField_id;

    @FindBy(id = "register-lastName")
    WebElement lastNameField_id;

    @FindBy(id = "register-email")
    WebElement emailField_id;

    @FindBy(id = "register-password")
    WebElement passwordField_id;

    @FindBy(id = "register-confirmPassword")
    WebElement confirmPasswordField_id;

    @FindBy(id = "register-submit")
    WebElement registerButton_id;

    @FindBy(id = "login-toggle")
    WebElement loginButton_id;
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.alertUtils = new AlertUtils(driver);
        screenshotUtils = new ScreenshotUtils();
    }
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public void verifyRegisterPageIsDisplayed() {
        String expectedHeading = "Create Your Account";
        logger.info("Waiting for Register Page to be visible");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(registrationHeading_id));

        String heading = registrationHeading_id.getText();
        logger.info(String.format("Heading found: %s", heading));

        Assert.assertEquals(heading, expectedHeading, "Heading does not match");
    }

    public boolean isRegisterFormVisible() {
        try {
            return registrationHeading_id.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void registerWithBlankForm() {
        String expectedMessage = "Please fill in all fields";
        logger.info("Submission of a blank register form");

        this.clearRegisterForm();

        screenshotUtils.captureAndAttach(driver, "Submission of blank form");

        this.clickRegisterButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);

        this.clearRegisterForm();
    }

    public void registerWithFirstNameOnly() {
        String expectedMessage = "Please fill in all fields";
        logger.info("Submission of a register form with only first name");

        this.enterFirstName(UserTestData.FIRST_NAME);

        screenshotUtils.captureAndAttach(driver, "Submission with only first name");

        this.clickRegisterButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);

        this.clearRegisterForm();
    }

    public void registerWithFirstAndLastNameOnly() {
        String expectedMessage = "Please fill in all fields";
        logger.info("Submission of a register form with only first name and last name");

        this.enterFirstName(UserTestData.FIRST_NAME);
        this.enterLastName(UserTestData.LAST_NAME);

        screenshotUtils.captureAndAttach(driver, "Submission with first name and last name");

        this.clickRegisterButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);

        this.clearRegisterForm();
    }

    public void registerWithInvalidEmail() {
        String expectedMessage = "Please fill in all fields";
        logger.info("Submission of a register form with first name, last name and invalid email address");

        this.enterFirstName(UserTestData.FIRST_NAME);
        this.enterLastName(UserTestData.LAST_NAME);
        this.enterEmailAddress(".");

        screenshotUtils.captureAndAttach(driver, "Submission with first name, last name and invalid email address");

        this.clickRegisterButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);

        this.clearRegisterForm();
    }

    public void registerWithInvalidEmailAndInvalidPassword() {
        String expectedMessage = "Passwords do not match!";
        logger.info("Submission of a register form with first name, last name, invalid email address and password");

        this.enterFirstName(UserTestData.FIRST_NAME);
        this.enterLastName(UserTestData.LAST_NAME);
        this.enterEmailAddress(".");
        this.enterPassword(".");

        screenshotUtils.captureAndAttach(driver, "Submission with first name, last name, invalid email address and password");

        this.clickRegisterButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);

        this.clearRegisterForm();
    }

    public void registerWithInvalidEmailAndInvalidPasswordAndConfirmPassword() {
        String expectedMessage = "Please enter a valid email address";
        logger.info("Submission of a register form with first name, last name, invalid email address, password and confirm password");

        this.enterFirstName(UserTestData.FIRST_NAME);
        this.enterLastName(UserTestData.LAST_NAME);
        this.enterEmailAddress(".");
        this.enterPassword(".");
        this.enterConfirmPassword(".");

        screenshotUtils.captureAndAttach(driver, "Submission with first name, last name and invalid email address, password and confirm password");

        this.clickRegisterButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);

        this.clearRegisterForm();
    }

    public void registerWithValidEmailAndInvalidPassword() {
        String expectedMessage = "Password must be at least 8 characters long";
        logger.info("Submission of a register form with first name, last name, valid email address, short password and confirm password");

        this.enterFirstName(UserTestData.FIRST_NAME);
        this.enterLastName(UserTestData.LAST_NAME);
        this.enterEmailAddress(UserTestData.generateUniqueEmail());
        this.enterPassword(".");
        this.enterConfirmPassword(".");

        screenshotUtils.captureAndAttach(driver, "Submission with first name, last name and invalid email address, short password and confirm password");

        this.clickRegisterButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);

        this.clearRegisterForm();
    }

    public void registerWithValidCredentials() {
        String expectedMessage = "Registration successful! You can now login with your credentials.";
        logger.info("Submission of a register form with valid credentials");

        this.enterFirstName(UserTestData.FIRST_NAME);
        this.enterLastName(UserTestData.LAST_NAME);
        this.enterEmailAddress(UserTestData.generateUniqueEmail());
        this.enterPassword(UserTestData.PASSWORD);
        this.enterConfirmPassword(UserTestData.PASSWORD);

        screenshotUtils.captureAndAttach(driver, "Submission with valid credentials");

        this.clickRegisterButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);
    }

    public void reRegisterWithValidCredentials() {
        String expectedMessage = "User with this email already exists";
        logger.info("Re-register user");

        this.enterFirstName(UserTestData.FIRST_NAME);
        this.enterLastName(UserTestData.LAST_NAME);
        this.enterEmailAddress(UserTestData.generateUniqueEmail());
        this.enterPassword(UserTestData.PASSWORD);
        this.enterConfirmPassword(UserTestData.PASSWORD);

        screenshotUtils.captureAndAttach(driver, "Re-register user");

        this.clickRegisterButton();

        this.alertUtils.verifyIfAlertMessageIsCorrect(expectedMessage);

        this.clearRegisterForm();
    }

    public void clickLoginButton() {
        loginButton_id.click();
    }

    public void clearRegisterForm() {
        this.enterFirstName("");
        this.enterLastName("");
        this.enterEmailAddress("");
        this.enterPassword("");
        this.enterConfirmPassword("");
    }

    // </editor-fold>

    // <editor-fold desc="Private Methods">
    private void enterFirstName(String firstName) {
        firstNameField_id.clear();
        firstNameField_id.sendKeys(firstName);
    }

    private void enterLastName(String lastName) {
        lastNameField_id.clear();
        lastNameField_id.sendKeys(lastName);
    }

    private void enterEmailAddress(String emailAddress) {
        emailField_id.clear();
        emailField_id.sendKeys(emailAddress);
    }

    private void enterPassword(String password) {
        passwordField_id.clear();
        passwordField_id.sendKeys(password);
    }

    private void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField_id.clear();
        confirmPasswordField_id.sendKeys(confirmPassword);
    }

    private void clickRegisterButton() {
        registerButton_id.click();
    }
    // </editor-fold>

}
