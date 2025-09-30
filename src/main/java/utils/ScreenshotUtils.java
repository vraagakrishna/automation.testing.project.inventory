package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    // <editor-fold desc="Class Fields / Constants">
    private static final String screenshotDir = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "Screenshots";

    private static Rectangle browserBounds;
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public void setBrowserBounds(WebDriver driver) {
        org.openqa.selenium.Point pos = driver.manage().window().getPosition();
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();

        browserBounds = new Rectangle(pos.getX(), pos.getY(), size.getWidth(), size.getHeight());
    }

    public void captureAndAttach(WebDriver driver, String label) {
        String path = takeScreenshot(driver, label.replace(" ", "_") + "_" + System.currentTimeMillis());
        ReportManager.getTest()
                .info(label)
                .addScreenCaptureFromPath(path, label);
    }

    public void captureWithAlert(String label) {
        try {
            if (browserBounds == null) {
                // fallback to full screen if not set
                browserBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            }

            Robot robot = new Robot();
            BufferedImage screenFullImage = robot.createScreenCapture(browserBounds);

            String screenshotName = label.replace(" ", "_") + System.currentTimeMillis();

            File destination = this.getScreenshotPath(screenshotName);

            ImageIO.write(screenFullImage, "png", destination);

            ReportManager.getTest()
                    .info(label)
                    .addScreenCaptureFromPath("Screenshots" + File.separator + screenshotName + ".png", label);
        } catch (Exception ex) {
            ReportManager.getTest().info("Could not capture screenshot");
        }
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
