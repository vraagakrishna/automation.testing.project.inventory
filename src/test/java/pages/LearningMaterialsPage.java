package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.JavascriptExecutorUtils;
import utils.LoggingManager;
import utils.ScreenshotUtils;
import utils.UserTestData;

import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LearningMaterialsPage {

    // <editor-fold desc="Class Fields / Constants">
    private static final Logger logger = LoggingManager.getLogger(LearningMaterialsPage.class.getName());

    private static ScreenshotUtils screenshotUtils;

    private final WebDriver driver;

    private final JavascriptExecutorUtils javascriptExecutorUtils;

    private final UserTestData userTestData;

    @FindBy(id = "practice-heading")
    WebElement practiceHeading_id;

    @FindBy(id = "logout-button")
    WebElement logoutButton_id;

    @FindBy(id = "nav-btn-about")
    WebElement aboutButton_id;

    @FindBy(id = "practice-tabs")
    WebElement practiceTabs_id;
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public LearningMaterialsPage(WebDriver driver) throws IOException {
        this.driver = driver;
        this.javascriptExecutorUtils = new JavascriptExecutorUtils(driver);
        this.userTestData = new UserTestData();
        screenshotUtils = new ScreenshotUtils();
    }
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public void verifyLearningMaterialsPageIsDisplayed() {
        String expectedHeading = String.format("Welcome back, %s!", UserTestData.getFirstName());

        logger.info("Waiting for Learning Materials Page to be visible");
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(visibilityOf(practiceHeading_id));

        String heading = practiceHeading_id.getText();
        logger.info(String.format("Heading found: %s", heading));

        Assert.assertEquals(heading, expectedHeading, "Heading does not match");

        logger.info("Verify if Logout Button to be visible");
        this.javascriptExecutorUtils.scrollToTop();
        Assert.assertTrue(logoutButton_id.isDisplayed(), "Logout button did not display");

        logger.info("Checking if token exists in local storage");
        String token = this.javascriptExecutorUtils.getLocalStorageItem("authToken");

        logger.info(String.format("Token from localStorage: %s", token));

        Assert.assertNotNull(token, "Auth token is not present");

        screenshotUtils.captureAndAttach(driver, "Verifying Learning Materials Page is displayed");
    }

    public void clickLogOut() {
        this.javascriptExecutorUtils.scrollToTop();
        logoutButton_id.click();
    }

    public void clickAboutUsPage() {
        aboutButton_id.click();

        screenshotUtils.captureAndAttach(driver, "Navigation to About Us Page");
    }

    public void clickWebAutomationTab() {
        logger.info("Click on Web Automation Advance tab");

        String tabName = "Web Automation Advance";
        this.clickTab(tabName);
    }

    public void clickApiTestingTab() {
        logger.info("Click on API Testing tab");

        String tabName = "API Testing";
        this.clickTab(tabName);
    }
    // </editor-fold>

    // <editor-fold desc="Private Methods">
    private void clickTab(String tabName) {
        WebElement webAutomationTab = null;

        this.javascriptExecutorUtils.scrollToTop();

        // Look for the correct tab
        for (WebElement webElement : practiceTabs_id.findElements(By.tagName("button"))) {
            if (webElement.getText().contains(tabName)) {
                webAutomationTab = webElement;
                break;
            }
        }

        // if tab is not found...
        Assert.assertNotNull(webAutomationTab, String.format("%s tab not found", tabName));

        logger.info("Tab is found");

        screenshotUtils.captureAndAttach(driver, "Switched tab to " + tabName);

        webAutomationTab.click();
    }
    // </editor-fold>

}
