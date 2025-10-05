package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import utils.FileUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BrowserFactory {

    // <editor-fold desc="Class Fields / Constants">
    private static final Logger logger = Logger.getLogger(BrowserFactory.class.getName());

    private static FileUtils fileUtils;
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public BrowserFactory() {
        fileUtils = new FileUtils();
    }
    // </editor-fold>

    // <editor-fold desc="Public methods">
    public WebDriver startBrowser(String browserName, String url) {
        logger.info(String.format("Starting browser %s", browserName));

        String downloadPath = fileUtils.getDownloadPath();

        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("credentials_enable_service", false);
                chromePrefs.put("profile.password_manager_enabled", false);
                chromePrefs.put("download.default_directory", downloadPath);
                chromePrefs.put("plugins.always_open_pdf_externally", true);
                chromePrefs.put("download.prompt_for_download", false);
                chromePrefs.put("download.directory_upgrade", true);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);

                // Disable password manager popups
                chromeOptions.addArguments("--disable-notifications");

                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--window-size=1920,1080");

                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.dir", downloadPath);
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                        "application/pdf,application/vnd.ms-excel,text/csv");
                profile.setPreference("pdfjs.disabled", true);

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(profile);

                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--window-size=1920,1080");

                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();

                Map<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("download.default_directory", downloadPath);
                edgePrefs.put("download.prompt_for_download", false);
                edgePrefs.put("download.directory_upgrade", true);
                edgePrefs.put("plugins.always_open_pdf_externally", true);
                edgePrefs.put("safebrowsing.enabled", true);
                edgeOptions.setExperimentalOption("prefs", edgePrefs);

                edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--window-size=1920,1080");

                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        // removed because of window-size added to Options
        //driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
    // </editor-fold>

}
