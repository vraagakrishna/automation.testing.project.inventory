package extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import tests.TestsBase;
import utils.DriverManager;
import utils.LoggingManager;
import utils.ReportManager;
import utils.ScreenshotUtils;

import java.util.logging.Logger;

public class Listener implements ITestListener {

    // <editor-fold desc="Class Fields / Constants">
    private static final Logger logger = LoggingManager.getLogger(Listener.class.getName());

    private static ExtentReports extent;

    private static ExtentTest extentTest;

    private static ScreenshotUtils screenshotUtils;

    private WebDriver driver;
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public Listener() {
        screenshotUtils = new ScreenshotUtils();
    }
    // </editor-fold>

    // <editor-fold desc="Overrides">
    @Override
    public void onStart(ITestContext results) {
        extent = ExtentReportManager.extentReportSetup();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String description = this.getDescription(method);
        extentTest = extent.createTest(description);
        ReportManager.setTest(extentTest);

        Severity severity = method.getConstructorOrMethod().getMethod().getAnnotation(Severity.class);

        if (severity != null) {
            SeverityLevel level = severity.value();
            extentTest.getModel().setDescription(level.toString().toUpperCase());
        } else {
            extentTest.getModel().setDescription("UNDEFINED");
        }

        String[] groups = result.getMethod().getGroups();
        for (String group : groups) {
            extentTest.assignCategory(group);
        }

        driver = DriverManager.getDriver();
        screenshotUtils.captureAndAttach(driver, "Before Test Screenshot", true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        extentTest.log(Status.PASS, "Test Case '" + this.getDescription(method) + "' has Passed.");
        logger.info(String.format("PASSED: %s - %s", result.getName(), this.getDescription(method)));

        this.takeAfterScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Case '" + this.getDescription(result.getMethod()) + "' has Failed.");

        // Log the actual exception/assertion message
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            extentTest.log(Status.FAIL, "Reason: " + throwable.getMessage());
            logger.info(String.format("FAILED: %s - %s", result.getName(), result.getThrowable()));

            // If you also want the stacktrace:
            extentTest.log(Status.FAIL, throwable);
        }

        this.takeAfterScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test Case '" + this.getDescription(result.getMethod()) + "' has been Skipped.");
        logger.info(String.format("SKIPPED: %s", result.getName()));

        this.takeAfterScreenshot(result);
    }

    @Override
    public void onFinish(ITestContext results) {
        try {
            ReportManager.unload();
            if (extent != null) {
                extent.setSystemInfo("Browser", TestsBase.browserName);
                extent.flush();
                logger.info("Extent report flushed successfully.");
            } else {
                logger.info("Extent instance was null - report not flushed");
            }
        } catch (Exception e) {
            logger.info("Error flushing extent report: " + e.getMessage());
        }
    }
    // </editor-fold>

    // <editor-fold desc="Private Methods">
    private String getDescription(ITestNGMethod method) {
        String description = method.getDescription();
        if (description == null || description.isEmpty())
            description = method.getMethodName();

        return description;
    }

    private void takeAfterScreenshot(ITestResult result) {
        if (driver != null) {
            screenshotUtils.captureAndAttach(driver, "After Test Screenshot", true);
        }
    }
    // </editor-fold>

}
