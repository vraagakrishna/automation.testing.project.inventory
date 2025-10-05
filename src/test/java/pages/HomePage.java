package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ScreenshotUtils;

import java.time.Duration;
import java.util.logging.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class HomePage {

    // <editor-fold desc="Class Fields / Constants">
    private static final Logger logger = Logger.getLogger(HomePage.class.getName());

    private static ScreenshotUtils screenshotUtils;

    private final WebDriver driver;

    @FindBy(id = "overview-hero")
    WebElement homePageTitle_id;

    @FindBy(id = "nav-btn-practice")
    WebElement learningMaterialButton_id;
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public HomePage(WebDriver driver) {
        this.driver = driver;
        screenshotUtils = new ScreenshotUtils();
    }
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public void verifyHomePageIsDisplayed() {
        String expectedHeading = "Learn Automation the Right Way";
        logger.info("Waiting for Home Page to be visible...");

        // wait until the element is visible
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(homePageTitle_id));

        String heading = homePageTitle_id.findElement(By.tagName("h2")).getText();
        logger.info(String.format("Heading found: %s", heading));

        Assert.assertEquals(heading, expectedHeading, "Heading does not match");
    }

    public void clickLearningMaterial() {
        logger.info("Waiting for Learning Material button to be visible...");

        // wait until the element is visible
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(learningMaterialButton_id));

        logger.info("Element found; Clicking Learning Material button");
        learningMaterialButton_id.click();
    }

    public void reloadPage() {
        logger.info("Reloading the page; should log out the user");

        // the user should be logged out when the tab is changed
        driver.navigate().refresh();

        screenshotUtils.captureAndAttach(driver, "Verifying page is refreshed");
    }
    // </editor-fold>

}
