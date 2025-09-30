package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptExecutorUtils {

    // <editor-fold desc="Class Fields / Constants">
    private final JavascriptExecutor js;

    public JavascriptExecutorUtils(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public String getLocalStorageItem(String key) {
        return (String) this.js.executeScript(
                String.format("return window.localStorage.getItem('%s');", key)
        );
    }

    public void scrollToTop() {
        this.js.executeScript("window.scroll(0, 0)");
    }

    public void resetForm(WebElement form) {
        js.executeScript("arguments[0].reset();", form);
    }

    public void scrollToView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

    }
    // </editor-fold>

}
