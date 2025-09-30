package extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverManager;
import utils.ReportManager;
import utils.ScreenshotUtils;

public class Listener implements ITestListener {

    // <editor-fold desc="Class Fields / Constants">
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
        String description = this.getDescription(result);
        extentTest = extent.createTest(description);
        ReportManager.setTest(extentTest);

        // Add groups as categories
        String[] groups = result.getMethod().getGroups();
        for (String group : groups) {
            extentTest.assignCategory(group);
        }

        driver = DriverManager.getDriver();
        screenshotUtils.setBrowserBounds(driver);

        screenshotUtils.captureAndAttach(driver, "Before Test Screenshot");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Case '" + this.getDescription(result) + "' has Passed.");

        this.takeAfterScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Case '" + this.getDescription(result) + "' has Failed.");

        // Log the actual exception/assertion message
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            extentTest.log(Status.FAIL, "Reason: " + throwable.getMessage());

            // If you also want the stacktrace:
            extentTest.log(Status.FAIL, throwable);
        }

        this.takeAfterScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test Case '" + this.getDescription(result) + "' has been Skipped.");

        this.takeAfterScreenshot(result);
    }

    @Override
    public void onFinish(ITestContext results) {
        ReportManager.unload();
        extent.flush();
    }
    // </editor-fold>

    // <editor-fold desc="Private Methods">
    private String getDescription(ITestResult result) {
        String description = result.getMethod().getDescription();
        if (description == null || description.isEmpty())
            description = result.getMethod().getMethodName();

        return description;
    }

    private void takeAfterScreenshot(ITestResult result) {
        if (driver != null) {
            //String description = this.getDescription(result);
            //String screenshotPath = screenshotUtils.takeScreenshot(driver, description + "_after");

            //extentTest.addScreenCaptureFromPath(screenshotPath, "After Test Screenshot");
            screenshotUtils.captureAndAttach(driver, "After Test Screenshot");
        }
    }
    // </editor-fold>

}
