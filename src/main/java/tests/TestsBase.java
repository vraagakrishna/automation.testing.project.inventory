package tests;

import factory.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.*;
import utils.DriverManager;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class TestsBase {

    // <editor-fold desc="Class Fields / Constants">
    private static final Logger logger = Logger.getLogger(TestsBase.class.getName());

    public final String WEBSITE_URL = "https://www.ndosiautomation.co.za/";

    private final BrowserFactory browserFactory;

    protected WebDriver driver;

    protected HomePage homePage;

    protected LoginPage loginPage;

    protected RegisterPage registerPage;

    protected LearningMaterialsPage learningMaterialsPage;

    protected InventoryPage inventoryPage;
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public TestsBase() {
        this.browserFactory = new BrowserFactory();
    }
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser) {
        logger.info("Setting up the driver");

        this.driver = browserFactory.startBrowser(browser, WEBSITE_URL);
        DriverManager.setDriver(driver);

        this.homePage = PageFactory.initElements(driver, HomePage.class);
        this.loginPage = PageFactory.initElements(driver, LoginPage.class);
        this.registerPage = PageFactory.initElements(driver, RegisterPage.class);
        this.learningMaterialsPage = PageFactory.initElements(driver, LearningMaterialsPage.class);
        this.inventoryPage = PageFactory.initElements(driver, InventoryPage.class);
    }

    @AfterClass
    public void tearDown() {
        logger.info("Tearing down...");

        if (driver != null) {
            driver.quit();
            DriverManager.unload();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        // If more than 1 tab is open
        if (driver.getWindowHandles().size() > 1) {
            Set<String> allHandles = new HashSet<>(driver.getWindowHandles());
            String mainHandle = null;

            // Find the main application window by its title
            for (String handle : allHandles) {
                driver.switchTo().window(handle);
                String title = driver.getTitle();

                if (title.contains("Test Automation")) {
                    mainHandle = handle;
                    break;
                }
            }

            // Close any extra windows that are not the main app
            for (String handle : new HashSet<>(allHandles)) {
                if (!handle.equals(mainHandle)) {
                    driver.switchTo().window(handle);
                    String title = driver.getTitle();

                    if (!title.equalsIgnoreCase("Downloads"))
                        driver.close();
                }
            }

            // Switch back to the main app tab
            if (mainHandle != null)
                driver.switchTo().window(mainHandle);
        }

        if (inventoryPage.isPurchaseSuccessToastVisible())
            inventoryPage.closePurchaseSuccessToast();

        if (inventoryPage.isInvoiceHistoryPanelVisible())
            inventoryPage.closeInvoiceHistoryPanel();

        inventoryPage.removeInjectedElements();

        if (inventoryPage.isInvoiceVisible() && inventoryPage.invoiceHasItems()) {
            inventoryPage.clickInvoiceButton();
            inventoryPage.clearAllInvoices();
        }

        if (inventoryPage.isInventoryReviewVisible()) {
            inventoryPage.resetInventoryReviewForm();
            inventoryPage.backToInventoryForm();
        }

        if (inventoryPage.isInventoryFormVisible()) {
            inventoryPage.resetInventoryForm();
        }

        if (registerPage.isRegisterFormVisible())
            registerPage.clearRegisterForm();

        if (loginPage.isLoginFormVisible())
            loginPage.clearLoginForm();
    }
    // </editor-fold>

}
