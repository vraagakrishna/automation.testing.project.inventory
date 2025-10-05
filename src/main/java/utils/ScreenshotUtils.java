package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    // <editor-fold desc="Class Fields / Constants">
    private static final String screenshotDir = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "Screenshots";
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public void captureAndAttach(WebDriver driver, String label) {
        // removed capture code because of storage constraints on GitHub actions
        // captureAndAttach(driver, label, true);
    }

    public void captureAndAttach(WebDriver driver, String label, boolean takesScreenshot) {
        String path = takeScreenshot(driver, label.replace(" ", "_") + "_" + System.currentTimeMillis());
        ReportManager.getTest()
                .info(label)
                .addScreenCaptureFromPath(path, label);
    }
    // </editor-fold>

    // <editor-fold desc="Private Methods">
    private File getScreenshotPath(String screenshotName) {
        return new File(screenshotDir, screenshotName + ".png");
    }

    private String takeScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File destination = this.getScreenshotPath(screenshotName);

        try {
            FileUtils.copyFile(src, destination);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Screenshots" + File.separator + screenshotName + ".png";
    }
    // </editor-fold>

}
