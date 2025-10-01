package extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportManager {

    // <editor-fold desc="Class Fields / Constants">
    private static final String reportDir = System.getProperty("user.dir") + File.separator + "Reports";

    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public static ExtentReports extentReportSetup() {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(new File(reportDir + File.separator + "inventory.html"));
        extentReports.attachReporter(extentSparkReporter);

        extentSparkReporter.config().setDocumentTitle("Extent Report");
        extentSparkReporter.config().setReportName("Inventory Form");

        // Make the right-hand details panel scrollable
        extentSparkReporter.config().setCss(".test-contents { overflow-y: scroll; max-height: 600px; }");

        // Fetch system details
        String os = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String javaVersion = System.getProperty("java.version");

        // Add them to the report
        extentReports.setSystemInfo("Operating System", os + " " + osVersion);
        extentReports.setSystemInfo("Java Version", javaVersion);

        return extentReports;
    }
    // </editor-fold>

}
