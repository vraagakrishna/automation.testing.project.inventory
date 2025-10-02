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

    public String getOuterHtml(WebElement element) {
        return (String) js.executeScript("return arguments[0].outerHTML;", element);
    }

    public void insertElementIntoContainer(WebElement container, String element) {
        js.executeScript(
                "arguments[0].insertAdjacentHTML('beforeend', '<div data-test-id=\"injected-item\">' + arguments[1] + '</div>');",
                container, element
        );
    }

    public void removeInjectedElements() {
        js.executeScript(
                "document.querySelectorAll('[data-test-id=\"injected-item\"]').forEach(el => el.remove());"
        );
    }
    // </editor-fold>

}
