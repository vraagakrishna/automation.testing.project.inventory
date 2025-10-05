package pages;

import enums.Enums;
import model.InventoryItem;
import model.Invoice;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class InventoryPage {

    // <editor-fold desc="Class Fields / Constants">
    private static final Logger logger = Logger.getLogger(InventoryPage.class.getName());

    private static ScreenshotUtils screenshotUtils;

    private static FileUtils fileUtils;

    private final SoftAssert softAssert;

    private final WebDriver driver;

    private final AlertUtils alertUtils;

    private final JavascriptExecutorUtils javascriptExecutorUtils;

    private final UserTestData userTestData;

    // <editor-fold desc="Title">
    @FindBy(id = "inventory-title")
    WebElement inventoryTitle_id;
    // </editor-fold>

    // <editor-fold desc="Inventory Form">
    @FindBy(id = "inventory-card")
    WebElement inventoryCard_id;

    @FindBy(id = "inventory-form")
    WebElement inventoryForm_id;

    @FindBy(id = "inventory-form-grid")
    WebElement inventoryFormGrid_id;

    @FindBy(id = "device-preview")
    WebElement devicePreview_id;

    @FindBy(id = "inventory-review-step")
    WebElement inventoryReviewStep_id;
    // </editor-fold>

    // <editor-fold desc="Inventory Form Inputs">
    @FindBy(id = "deviceType")
    WebElement deviceType_id;

    @FindBy(id = "brand")
    WebElement brand_id;

    @FindBy(name = "storage")
    List<WebElement> storages;

    @FindBy(id = "quantity")
    WebElement quantity_id;

    @FindBy(id = "color")
    WebElement color_id;

    @FindBy(id = "address")
    WebElement address_id;
    // </editor-fold>

    // <editor-fold desc="Inventory Form Buttons">
    @FindBy(id = "inventory-next-btn")
    WebElement inventoryNextBtn_id;

    @FindBy(id = "inventory-back-btn")
    WebElement inventoryBackBtn_id;

    @FindBy(id = "add-to-cart-btn")
    WebElement addToCartBtn_id;

    @FindBy(id = "purchase-device-btn")
    WebElement purchaseDeviceBtn_id;
    // </editor-fold>

    // <editor-fold desc="Inventory Form Errors">
    @FindBy(className = "error-text")
    List<WebElement> errorTexts;

    @FindBy(id = "inventory-errors")
    WebElement inventoryErrors_id;
    // </editor-fold>

    // <editor-fold desc="Inventory Review Form">
    @FindBy(id = "device-summary-list")
    WebElement deviceSummaryList_id;

    @FindBy(name = "shippingMethod")
    List<WebElement> shippingMethods;

    @FindBy(name = "warranty")
    List<WebElement> warranties;

    // <editor-fold desc="Discount">
    @FindBy(id = "discount-code")
    WebElement discountCode_id;

    @FindBy(id = "apply-discount-btn")
    WebElement applyDiscountBtn_id;

    @FindBy(id = "discount-feedback")
    WebElement discountFeedback_id;
    // </editor-fold>

    // <editor-fold desc="Price Breakdown">
    @FindBy(id = "base-price-value")
    WebElement basePriceValue_id;

    @FindBy(id = "breakdown-quantity-value")
    WebElement breakdownQuantityValue_id;

    @FindBy(id = "breakdown-subtotal-value")
    WebElement breakdownSubtotalValue_id;

    @FindBy(id = "breakdown-warranty-value")
    WebElement breakdownWarrantyValue_id;

    @FindBy(id = "breakdown-shipping-value")
    WebElement breakdownShippingValue_id;

    @FindBy(id = "breakdown-discount-value")
    WebElement breakdownDiscountValue_id;

    @FindBy(id = "breakdown-total-value")
    WebElement breakdownTotalValue_id;
    // </editor-fold>

    // <editor-fold desc="Cart Review">
    @FindBy(id = "cart-items-preview")
    WebElement cartItemsPreview_id;

    @FindBy(xpath = "//*[@id=\"cart-items-preview\"]/div[2]")
    WebElement cartItemsPreviewList;
    // </editor-fold>
    // </editor-fold>

    // <editor-fold desc="Inventory Form Price">
    @FindBy(id = "step1-pricing-summary")
    WebElement step1PricingSummary_id;

    @FindBy(id = "unit-price-value")
    WebElement unitPriceValue_id;

    @FindBy(id = "quantity-value")
    WebElement quantityValue_id;

    @FindBy(id = "subtotal-value")
    WebElement subtotalValue_id;
    // </editor-fold>

    // <editor-fold desc="Cart">
    @FindBy(id = "cart-summary")
    WebElement cartSummary_id;

    @FindBy(id = "cart-title")
    WebElement cartTitle_id;

    @FindBy(id = "review-cart-btn")
    WebElement reviewCartBtn_id;

    @FindBy(id = "cart-grand-total-value")
    WebElement cartGrandTotalValue_id;

    @FindBy(css = "[id^='cart-item-']")
    List<WebElement> cartItems;

    @FindBy(id = "cancel-cart-btn")
    WebElement cancelCartBtn_id;

    @FindBy(id = "confirm-cart-btn")
    WebElement confirmCartBtn_id;
    // </editor-fold>

    // <editor-fold desc="Purchase Success Toast">
    @FindBy(id = "purchase-success-toast")
    WebElement purchaseSuccessToast_id;

    @FindBy(css = "button[title='Dismiss notification']")
    WebElement dismissNotificationBtn_id;

    @FindBy(xpath = "//*[@id='purchase-success-toast']//p[1]")
    WebElement orderSuccessMessage;

    @FindBy(xpath = "//*[@id=\"purchase-success-toast\"]/div[2]/p")
    WebElement orderDetails;

    @FindBy(xpath = "//*[@id=\"purchase-success-toast\"]/p[2]")
    WebElement totalPrice;

    @FindBy(id = "view-history-btn")
    WebElement viewHistoryBtn_id;

    @FindBy(xpath = "//*[@id=\"purchase-success-toast\"]/div[3]/p")
    WebElement purchaseToastTimestamp;
    // </editor-fold>

    // <editor-fold desc="Invoice">
    @FindBy(id = "invoices-toggle-btn")
    WebElement invoicesToggleBtn_id;

    @FindBy(id = "invoice-history-modal")
    WebElement invoiceHistoryModal_id;

    @FindBy(id = "invoice-history-panel")
    WebElement invoiceHistoryPanel_id;

    @FindBy(id = "invoices-list")
    WebElement invoicesList_id;

    @FindBy(id = "clear-all-invoices-btn")
    WebElement clearAllInvoicesBtn_id;

    @FindBy(id = "no-invoices-text")
    WebElement noInvoicesText_id;

    @FindBy(id = "close-invoice-history-btn")
    WebElement closeInvoiceHistoryBtn_id;
    // </editor-fold>

    // <editor-fold desc="View Invoice">
    @FindBy(css = ".company-logo")
    WebElement invoiceCompanyLogo;

    @FindBy(css = ".company-info .company-name")
    WebElement invoiceCompanyName;

    @FindBy(css = ".customer-info .info-line strong")
    WebElement invoiceCustomerName;

    @FindBy(css = ".customer-info .info-line:nth-of-type(3)")
    WebElement invoiceCustomerEmail;

    @FindBy(css = ".customer-info .info-line:nth-of-type(4)")
    WebElement invoiceCustomerAddress;

    @FindBy(xpath = "//div[@class='invoice-info']//strong[text()='Invoice #:']/parent::div")
    WebElement invoiceDetailsNumber;

    @FindBy(xpath = "//div[@class='invoice-info']//strong[text()='Date:']/parent::div")
    WebElement invoiceDetailsDate;

    @FindBy(xpath = "//div[@class='invoice-info']//strong[text()='Time:']/parent::div")
    WebElement invoiceDetailsTime;

    @FindBy(css = ".items-table tbody tr")
    List<WebElement> invoiceItems;

    @FindBy(xpath = "//div[@class='totals']//div[contains(text(),'Subtotal')]")
    WebElement invoiceSubTotal;

    @FindBy(xpath = "//div[@class='totals']//div[contains(text(),'Shipping')]")
    WebElement invoiceShipping;

    @FindBy(xpath = "//div[@class='totals']//div[contains(text(),'Warranty')]")
    WebElement invoiceWarranty;

    @FindBy(xpath = "//div[@class='totals']//div[contains(text(),'Discount')]")
    WebElement invoiceDiscount;

    @FindBy(xpath = "//div[@class='totals']//div[contains(text(),'Total')]")
    WebElement invoiceTotal;

    @FindBy(xpath = "//div[@class='thank-you']")
    WebElement thankYouMessage;
    // </editor-fold>
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public InventoryPage(WebDriver driver) throws IOException {
        this.driver = driver;
        this.softAssert = new SoftAssert();
        this.javascriptExecutorUtils = new JavascriptExecutorUtils(driver);
        this.alertUtils = new AlertUtils(driver);
        this.userTestData = new UserTestData();
        screenshotUtils = new ScreenshotUtils();
        fileUtils = new FileUtils();
    }
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    // <editor-fold desc="Clean up Inventory Form">
    public boolean isInventoryFormVisible() {
        logger.info("Checking if the Inventory Form is visible");
        try {
            return inventoryFormGrid_id.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isInventoryReviewVisible() {
        logger.info("Checking if the Inventory Review is visible");
        try {
            return inventoryReviewStep_id.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isInvoiceVisible() {
        logger.info("Checking if the Invoice is visible");
        try {
            return invoicesToggleBtn_id.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean invoiceHasItems() {
        logger.info("Checking if the Invoice has items");
        try {
            String invoiceButtonText = invoicesToggleBtn_id.getText();

            return !invoiceButtonText.contains("Invoices (0)");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isInvoiceHistoryPanelVisible() {
        logger.info("Checking if the Invoice History Panel is visible");
        try {
            return invoiceHistoryPanel_id.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPurchaseSuccessToastVisible() {
        logger.info("Checking if the Purchase Success Toast is visible");
        try {
            return purchaseSuccessToast_id.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void backToInventoryForm() {
        logger.info("Back to Inventory Form");
        inventoryBackBtn_id.click();

        screenshotUtils.captureAndAttach(driver, "Navigating by clicking on Back Button");
    }

    public void resetInventoryReviewForm() {
        logger.info("Reset Inventory Review Form");

        javascriptExecutorUtils.scrollToTop();

        // select shipping method
        logger.info("Selecting shipping method");
        this.selectRadioButton(shippingMethods, "standard");

        // select warranty
        logger.info("Selecting warranty");
        this.selectRadioButton(warranties, "none");

        // enter discount
        logger.info("Entering discount");
        this.enterDiscount("");
    }

    public void resetInventoryForm() {
        this.javascriptExecutorUtils.resetForm(inventoryForm_id);

        // the reset of form is not always working...
        this.selectBrand("Select brand");
        this.selectDeviceType("Select");
        this.selectColor("Black");

        // Note: this does not reset Quantity and Address
        selectQuantity(1);
        typeAddress("");
    }

    public boolean isCartSummaryVisible() {
        logger.info("Checking if the Cart Summary is visible");
        try {
            return cartSummary_id.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    // </editor-fold>

    public void verifyWebAutomationTabOpened() {
        String expectedHeading = "Inventory Form";
        logger.info("Verify if Web Automation tab opened");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(inventoryTitle_id));
        String heading = inventoryTitle_id.getText();

        logger.info(String.format("Heading found: %s", heading));
        Assert.assertEquals(heading, expectedHeading, "Heading does not match");

        screenshotUtils.captureAndAttach(driver, "Verifying Web Automation Tab is displayed");
    }

    public void validateBlankInventoryForm() {
        logger.info("Verify the fields on the form");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(inventoryFormGrid_id));

        Assert.assertFalse(this.isDisplayed(devicePreview_id), "Device preview is displayed");

        // checking fields
        List<String> expectedFields = new ArrayList<>(Arrays.asList("Device Type", "Brand", "Storage", "Color", "Quantity", "Delivery Address"));

        for (WebElement fieldRow : inventoryFormGrid_id.findElements(By.className("field-row"))) {
            if (expectedFields.isEmpty())
                break;

            String fieldName = fieldRow.findElement(By.className("field-label")).getText();
            WebElement fieldControl = fieldRow.findElement(By.className("field-control"));

            // remove ":"
            fieldName = fieldName.replace(":", "");

            String errorMessage = String.format("%s is not blank", fieldName);

            expectedFields.remove(fieldName);

            switch (fieldName) {
                case "Device Type" -> {
                    String expectedBlankDeviceTypeText = "Select";
                    Assert.assertEquals(expectedBlankDeviceTypeText, new Select(fieldControl.findElement(By.id("deviceType"))).getFirstSelectedOption().getText(), errorMessage);
                }
                case "Brand" -> {
                    String expectedBlankBrandText = "Select device type first";
                    Assert.assertEquals(expectedBlankBrandText, new Select(fieldControl.findElement(By.id("brand"))).getFirstSelectedOption().getText(), errorMessage);
                }
                case "Storage" -> {
                    for (WebElement radioButton : fieldControl.findElements(By.tagName("label"))) {
                        Assert.assertFalse(radioButton.isSelected(), errorMessage);
                    }
                }
                case "Color" -> {
                    String expectedBlankColorText = "Black";
                    Assert.assertEquals(expectedBlankColorText, new Select(fieldControl.findElement(By.id("color"))).getFirstSelectedOption().getText(), errorMessage);
                }
                case "Quantity" -> {
                    String expectedQuantity = "1";
                    Assert.assertEquals(expectedQuantity, fieldControl.findElement(By.id("quantity")).getAttribute("value"), errorMessage);
                }
                case "Delivery Address" -> {
                    String expectedDeliveryAddress = "";
                    Assert.assertEquals(expectedDeliveryAddress, fieldControl.findElement(By.id("address")).getText(), errorMessage);
                }
            }

            logger.info(String.format("%s is found", fieldName));
        }

        screenshotUtils.captureAndAttach(driver, "Validate blank inventory form");

        Assert.assertEquals(0, expectedFields.size(), "All the expected fields are not present");
    }

    public void blankInventoryFormSubmission() {
        logger.info("Submit a blank inventory form");

        // verify purchase device form is disabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is enabled...");

        screenshotUtils.captureAndAttach(driver, "Submitting blank inventory form");
    }

    public void validDeviceTypeSubmission() {
        logger.info("Submit inventory form with valid device type");

        // choose Device Type
        String selectedDeviceType = Enums.DeviceType.PHONE.getDisplayName();
        this.selectDeviceType(selectedDeviceType);

        // Brand changes to "Select Brand"
        String expectedBlankBrandText = "Select brand";
        Assert.assertEquals(expectedBlankBrandText, new Select(brand_id).getFirstSelectedOption().getText(), "Brand is not blank");

        // verify purchase device form is disabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is enabled...");

        screenshotUtils.captureAndAttach(driver, "Submitting inventory form with device type");
    }

    public void validDeviceTypeAndBrandSubmission() {
        logger.info("Submit inventory form with valid device type and brand");

        // choose Device Type
        String selectedDeviceType = Enums.DeviceType.PHONE.getDisplayName();
        this.selectDeviceType(selectedDeviceType);

        // choose Brand
        String selectedBrand = Enums.Brand.APPLE.getDisplayName();
        this.selectBrand(selectedBrand);

        // verify purchase device form is disabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is enabled...");

        screenshotUtils.captureAndAttach(driver, "Submitting inventory form with device type and brand");
    }

    public void validDeviceTypeAndBrandAndStorageSubmission() {
        logger.info("Submit inventory form with valid device type, brand and storage");

        // choose Device Type
        String selectedDeviceType = Enums.DeviceType.PHONE.getDisplayName();
        this.selectDeviceType(selectedDeviceType);

        // choose Brand
        String selectedBrand = Enums.Brand.APPLE.getDisplayName();
        this.selectBrand(selectedBrand);

        // choose "64GB" for Storage
        String selectedStorage = "64GB";
        this.selectRadioButton(storages, selectedStorage);

        // verify purchase device form is enabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is enabled...");

        screenshotUtils.captureAndAttach(driver, "Submitting inventory form with device type, brand and storage");
    }

    public void invalidQuantitySubmission() {
        logger.info("Submit inventory form with valid device type, brand, storage, but invalid quantity");

        // choose Device Type
        String selectedDeviceType = Enums.DeviceType.PHONE.getDisplayName();
        this.selectDeviceType(selectedDeviceType);

        // choose Brand
        String selectedBrand = Enums.Brand.APPLE.getDisplayName();
        this.selectBrand(selectedBrand);

        // choose "64GB" for Storage
        String selectedStorage = "64GB";
        this.selectRadioButton(storages, selectedStorage);

        // choose "0" for Quantity
        int selectedQuantity = 0;
        this.selectQuantity(selectedQuantity);

        // verify purchase device form is enabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is disabled...");

        screenshotUtils.captureAndAttach(driver, "Submitting inventory form with device type, brand, storage and 0 quantity");
    }

    public void invalidAddressSubmission() {
        logger.info("Submit inventory form with valid device type, brand, storage, quantity, but invalid address");

        // choose Device Type
        String selectedDeviceType = Enums.DeviceType.PHONE.getDisplayName();
        this.selectDeviceType(selectedDeviceType);

        // choose Brand
        String selectedBrand = Enums.Brand.APPLE.getDisplayName();
        this.selectBrand(selectedBrand);

        // choose "64GB" for Storage
        String selectedStorage = "64GB";
        this.selectRadioButton(storages, selectedStorage);

        // choose "2" for Quantity
        int selectedQuantity = 2;
        this.selectQuantity(selectedQuantity);

        String selectedAddress = "";
        this.typeAddress(selectedAddress);

        // verify purchase device form is enabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is disabled...");

        screenshotUtils.captureAndAttach(driver, "Submitting inventory form with device type, brand, storage, 2 quantity and blank address");
    }

    public void invalidQuantityAmountSubmission(int quantity) {
        logger.info("Submit inventory form with valid device type, brand, storage, address, but invalid quantity amount");

        InventoryItem inventoryItem = new InventoryItem(
                Enums.DeviceType.PHONE.getDisplayName(),
                Enums.Brand.APPLE.getDisplayName(),
                "64GB", quantity, "Gold", UserTestData.address
        );

        this.enterInventoryFormData(inventoryItem);

        this.submitInventoryForm();

        // validate the errors
        logger.info("Validate the errors");
        Assert.assertTrue(errorTexts.size() > 0, "Error messages are not displayed...");

        screenshotUtils.captureAndAttach(driver, "Validating the error messages");

        String expectedErrorMessage = "Please correct highlighted fields.";
        Assert.assertEquals(inventoryErrors_id.getText(), expectedErrorMessage, "Expected error message is not found");
        Assert.assertFalse(inventoryErrors_id.getAttribute("aria-live").isEmpty(), "Expected error message does not have aria-live");

        List<String> expectedErrorMessages = new ArrayList<>(List.of("Quantity must be ≤ 10"));
        expectedErrorMessages = expectedErrorMessages.stream().map(String::toLowerCase).collect(Collectors.toList());

        for (WebElement errorText : errorTexts) {
            Assert.assertTrue(expectedErrorMessages.contains(errorText.getText().toLowerCase()), "Expected error message is not found...");
            Assert.assertEquals(errorText.getAttribute("role"), "alert", "Error text is not role=alert");
        }

        this.validatePrice(inventoryItem);

        this.validateImage(inventoryItem);
    }

    public void changeSelectionOfDeviceType() {
        logger.info("Changing the selection of device type Test");

        logger.info("Selecting Phone as Device Type");
        // choose Device Type
        this.selectDeviceType(Enums.DeviceType.PHONE.getDisplayName());

        screenshotUtils.captureAndAttach(driver, "Selected Phone as Device Type");

        // Brand changes to "Select Brand"
        String expectedBlankBrandText = "Select brand";
        Assert.assertEquals(expectedBlankBrandText, new Select(brand_id).getFirstSelectedOption().getText(), "Brand is not blank");

        // verify purchase device form is disabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is enabled...");


        logger.info("Selection Apple as Brand");
        // choose Brand
        this.selectBrand(Enums.Brand.APPLE.getDisplayName());

        screenshotUtils.captureAndAttach(driver, "Selected Phone and Apple");

        // verify purchase device form is disabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is enabled...");


        logger.info("Change selection of Device Type to Tablet");
        // choose "Tablet" for Device Type
        this.selectDeviceType(Enums.DeviceType.TABLET.getDisplayName());

        screenshotUtils.captureAndAttach(driver, "Changed selection of Device Type to Tablet");

        // Brand changes to "Select Brand"
        String expectedBlankBrandText2 = "Select brand";
        Assert.assertEquals(expectedBlankBrandText2, new Select(brand_id).getFirstSelectedOption().getText(), "Brand is not blank");

        // verify purchase device form is disabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is enabled...");

        screenshotUtils.captureAndAttach(driver, "Brand selection should be removed");
    }

    public void partiallyFillInInventoryForm() {
        logger.info("Partially filling in the Inventory form");
        screenshotUtils.captureAndAttach(driver, "Checking Inventory Form before entering data");

        this.selectDeviceType(Enums.DeviceType.PHONE.getDisplayName());
        this.selectBrand(Enums.Brand.APPLE.getDisplayName());

        logger.info("Form is populated");

        screenshotUtils.captureAndAttach(driver, "Partially filled in inventory form");

        // verify purchase device form is enabled
        Assert.assertFalse(inventoryNextBtn_id.isEnabled(), "Next Button is enabled...");
    }

    public void inventoryFormDevicePriceVerification(String selectedDeviceType) {
        logger.info(String.format("Submit inventory form with %s device type", selectedDeviceType));

        InventoryItem inventoryItem = new InventoryItem(selectedDeviceType, "", "", 1, "Black", "");

        this.selectDeviceType(selectedDeviceType);

        this.validatePrice(inventoryItem);

        this.validateImage(inventoryItem);

        // remove Device selection
        this.selectDeviceType("Select");

        String blankPrice = "—";
        this.validateUnitPrice(blankPrice);
        this.validateQuantity(inventoryItem.getQuantity());
        this.validateSubTotal(blankPrice);

        screenshotUtils.captureAndAttach(driver, "Validating the price after removing device selection");
    }

    public void inventoryFormDevicePriceVerificationOnChangingDeviceType(String selectedDeviceType1, String selectedDeviceType2) {
        logger.info(String.format("Submit inventory form with %s device type", selectedDeviceType1));

        InventoryItem inventoryItem = new InventoryItem(
                selectedDeviceType1,
                Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Black", "address1"
        );

        this.enterInventoryFormData(inventoryItem);

        this.validatePrice(inventoryItem);

        this.validateImage(inventoryItem);

        this.submitInventoryForm();

        // wait until Step 2 is displayed
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(inventoryReviewStep_id));

        screenshotUtils.captureAndAttach(driver, "Navigating to Next step");

        this.validateInventoryDetails(inventoryItem);

        this.backToInventoryForm();


        // changing Device Type selection
        logger.info("Changing device type to: " + selectedDeviceType2);
        inventoryItem.setDeviceType(selectedDeviceType2);

        this.selectDeviceType(selectedDeviceType2);
        this.selectBrand(inventoryItem.getBrand());

        this.validatePrice(inventoryItem);

        this.validateImage(inventoryItem);

        this.submitInventoryForm();

        // wait until Step 2 is displayed
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(inventoryReviewStep_id));

        screenshotUtils.captureAndAttach(driver, "Navigating to Next step");

        this.validateInventoryDetails(inventoryItem);

        this.backToInventoryForm();
    }

    public void validAndResetInventoryFormSubmission(InventoryItem item) {
        this.validInventoryFormSubmission(item);

        this.resetInventoryReviewForm();

        this.backToInventoryForm();
    }

    public void selectRadioButtonWithKeyboard(String selectedStorage) {
        logger.info(String.format("Select %s storage with keyboard", selectedStorage));
        this.selectRadioButtonWithKeyboard(storages, selectedStorage);

        screenshotUtils.captureAndAttach(driver, String.format("Selected %s storage with keyboard", selectedStorage));

        String chosenStorage = "";
        for (WebElement radio : storages) {
            if (radio.getAttribute("value").equals(selectedStorage) && radio.isSelected()) {
                chosenStorage = selectedStorage;
                break;
            }
        }

        Assert.assertEquals(chosenStorage, selectedStorage, String.format("%s should be selected", selectedStorage));
    }

    public void discountRemoval(InventoryItem item) {
        this.validInventoryFormSubmission(item);

        logger.info("Remove the discount");
        javascriptExecutorUtils.scrollToTop();
        item.setDiscountCode("");
        this.enterDiscount(item.getDiscountCode());

        screenshotUtils.captureAndAttach(driver, "Removed discount");

        this.validatePriceOnReviewPage(item);

        this.resetInventoryReviewForm();

        this.backToInventoryForm();
    }

    public void purchaseItem(InventoryItem item) {
        this.validInventoryFormSubmission(item);
        this.clickPurchaseDeviceButton();

        this.validateBlankInventoryForm();
    }

    public void addItemToCart(InventoryItem item) {
        this.validInventoryFormSubmission(item);
        this.clickAddItemToCartButton();

        this.validateBlankInventoryForm();
    }

    public void addItemsToCart(List<InventoryItem> inventoryItemList) {
        logger.info(String.format("Adding %s items to cart", inventoryItemList.size()));
        int numberOfItem = 1;

        for (InventoryItem inventoryItem : inventoryItemList) {
            logger.info(String.format("Adding item %s of %s to cart", numberOfItem, inventoryItemList.size()));

            this.addItemToCart(inventoryItem);
            this.validateBlankInventoryForm();

            this.isCartVisible();

            this.verifyNumberOfItemsInCart(numberOfItem);

            numberOfItem += 1;
        }
    }

    public void isCartVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(cartSummary_id));

        Assert.assertTrue(cartSummary_id.isDisplayed(), "Cart summary is not displayed");

        screenshotUtils.captureAndAttach(driver, "Cart summary");
    }

    public void verifyNumberOfItemsInCart(int expectedNumberOfItems) {
        this.javascriptExecutorUtils.scrollToView(cartTitle_id);

        String expectedCartHeading = this.getCartHeading(expectedNumberOfItems);
        Assert.assertEquals(cartTitle_id.getText(), expectedCartHeading, "Cart heading is incorrect");

        List<WebElement> filteredCartItems = getCartItems();

        logger.info(String.format("Number of cart items: %s", filteredCartItems.size()));

        Assert.assertEquals(filteredCartItems.size(), expectedNumberOfItems, "Number of items in cart is incorrect");

        screenshotUtils.captureAndAttach(driver, "Items in cart");
    }

    public void verifyCartItems(List<InventoryItem> inventoryItems) {
        List<WebElement> filteredCartItems = getCartItems();

        String cartItemId = "cart-item-";

        double grandTotal = 0;

        logger.info(String.format("Items in cart: %s", inventoryItems.size()));

        javascriptExecutorUtils.scrollToView(filteredCartItems.get(0));
        screenshotUtils.captureAndAttach(driver, "Verify cart items");

        for (WebElement cartItem : filteredCartItems) {
            String idNumber = cartItem.getAttribute("id");
            idNumber = idNumber.replace(cartItemId, "");

            WebElement cartItemDescription = cartItem.findElement(By.id(cartItemId + "description-" + idNumber));

            logger.info(String.format("Item description: '%s'", cartItemDescription.getText()));

            // get item from list
            InventoryItem inventoryItem = inventoryItems.stream()
                    .filter(item -> item.getItemDescription().trim().equals(cartItemDescription.getText().trim()))
                    .findFirst()
                    .orElse(null);

            Assert.assertNotNull(inventoryItem, "Inventory item is not found");

            WebElement cartItemWarranty = cartItem.findElement(By.id(cartItemId + "warranty-" + idNumber));
            Assert.assertEquals(cartItemWarranty.getText(), "Warranty: " + inventoryItem.getWarranty(), "Warranty is incorrect");

            WebElement cartItemShipping = cartItem.findElement(By.id(cartItemId + "shipping-" + idNumber));
            Assert.assertEquals(cartItemShipping.getText(), "Ship: " + inventoryItem.getShippingMethod(), "Shipping is incorrect");

            if (inventoryItem.getDiscountPrice() > 0) {
                WebElement cartItemDiscount = cartItem.findElement(By.id(cartItemId + "discount-" + idNumber));
                Assert.assertEquals(cartItemDiscount.getText(), "Discount: " + inventoryItem.getDiscountPercentageAsString(), "Discount is incorrect");
            }

            WebElement cartItemTotal = cartItem.findElement(By.id(cartItemId + "total-" + idNumber));
            String totalFormatted = this.formatCurrency(inventoryItem.getTotalPrice());
            Assert.assertEquals(cartItemTotal.getText(), totalFormatted, "Total is not the same...");

            grandTotal += inventoryItem.getTotalPrice();
        }

        String grandTotalFormatted = this.formatCurrency(grandTotal);
        logger.info(String.format("Grand total is %s", grandTotalFormatted));
        Assert.assertEquals(cartGrandTotalValue_id.getText(), grandTotalFormatted, "Grand total is incorrect");

        Assert.assertTrue(reviewCartBtn_id.isDisplayed(), "Review Cart Button is displayed");
    }

    public void removeAllItemsInCart() {
        logger.info("Removing all items in the cart");
        List<WebElement> filteredCartItems = getCartItems();

        if (this.isCartSummaryVisible())
            javascriptExecutorUtils.scrollToView(cartSummary_id);

        String cartItemId = "cart-item-";

        for (WebElement cartItem : filteredCartItems) {
            String idNumber = cartItem.getAttribute("id");
            idNumber = idNumber.replace(cartItemId, "");

            logger.info("Removing item with id " + idNumber);

            String removeBtnId = cartItemId + "remove-" + idNumber;

            WebElement removeBtn = cartItem.findElement(By.id(removeBtnId));

            removeBtn.click();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(invisibilityOfElementLocated(By.id(removeBtnId)));
        }

        screenshotUtils.captureAndAttach(driver, "Removed all items in cart");

        Assert.assertFalse(this.isDisplayed(cartSummary_id), "Cart summary is visible");
    }

    public void removeFirstItemInCart(List<InventoryItem> inventoryItems) {
        logger.info("Removing first item in the cart");
        List<WebElement> filteredCartItems = getCartItems();

        String cartItemId = "cart-item-";

        for (WebElement cartItem : filteredCartItems) {
            String idNumber = cartItem.getAttribute("id");
            idNumber = idNumber.replace(cartItemId, "");

            WebElement cartItemDescription = cartItem.findElement(By.id(cartItemId + "description-" + idNumber));

            // get item from list
            InventoryItem inventoryItem = inventoryItems.stream()
                    .filter(item -> item.getItemDescription().equals(cartItemDescription.getText().trim()))
                    .findFirst()
                    .orElse(null);

            Assert.assertNotNull(inventoryItem, "Inventory item is not found");

            String removeBtnId = cartItemId + "remove-" + idNumber;

            WebElement removeBtn = cartItem.findElement(By.id(removeBtnId));

            removeBtn.click();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(invisibilityOfElementLocated(By.id(removeBtnId)));

            inventoryItems.remove(inventoryItem);

            break;
        }

        screenshotUtils.captureAndAttach(driver, "Remove first item in cart");

        this.verifyCartItems(inventoryItems);
    }

    public void removeLastItemInCart(List<InventoryItem> inventoryItems) {
        logger.info("Removing last item in the cart");
        List<WebElement> reversedCartItems = new ArrayList<>(getCartItems());

        // reverse items
        Collections.reverse(reversedCartItems);

        String cartItemId = "cart-item-";

        for (WebElement cartItem : reversedCartItems) {
            String idNumber = cartItem.getAttribute("id");
            idNumber = idNumber.replace(cartItemId, "");

            WebElement cartItemDescription = cartItem.findElement(By.id(cartItemId + "description-" + idNumber));

            // get item from list
            InventoryItem inventoryItem = inventoryItems.stream()
                    .filter(item -> item.getItemDescription().equals(cartItemDescription.getText().trim()))
                    .findFirst()
                    .orElse(null);

            Assert.assertNotNull(inventoryItem, "Inventory item is not found");

            String removeBtnId = cartItemId + "remove-" + idNumber;

            WebElement removeBtn = cartItem.findElement(By.id(removeBtnId));

            removeBtn.click();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(invisibilityOfElementLocated(By.id(removeBtnId)));

            inventoryItems.remove(inventoryItem);

            break;
        }

        screenshotUtils.captureAndAttach(driver, "Remove last item in cart");

        this.verifyCartItems(inventoryItems);
    }

    public void reduceQuantityOfItem(InventoryItem inventoryItem) {
        logger.info("Reduce quantity of item");
        List<WebElement> filteredCartItems = new ArrayList<>(getCartItems());

        String cartItemId = "cart-item-";

        WebElement cartItem = filteredCartItems.get(0);
        String idNumber = cartItem.getAttribute("id");
        idNumber = idNumber.replace(cartItemId, "");

        WebElement cartItemQuantityDecrease = cartItem.findElement(By.id(cartItemId + "decrease-" + idNumber));

        while (cartItemQuantityDecrease.isEnabled()) {
            cartItemQuantityDecrease.click();

            inventoryItem.reduceQuantity();

            screenshotUtils.captureAndAttach(driver, "Reduce quantity by 1");

            List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem));

            this.verifyCartItems(inventoryItemList);

            logger.info(String.format("Quantity of item: %s", inventoryItem.getQuantity()));
        }
    }

    public void increaseQuantityOfItem(InventoryItem inventoryItem) {
        logger.info("Increase quantity of item");
        List<WebElement> filteredCartItems = new ArrayList<>(getCartItems());

        String cartItemId = "cart-item-";

        WebElement cartItem = filteredCartItems.get(0);

        String idNumber = cartItem.getAttribute("id");
        idNumber = idNumber.replace(cartItemId, "");
        WebElement cartItemQuantityIncrease = cartItem.findElement(By.id(cartItemId + "increase-" + idNumber));

        if (cartItemQuantityIncrease.isEnabled()) {
            cartItemQuantityIncrease.click();

            inventoryItem.increaseQuantity();

            screenshotUtils.captureAndAttach(driver, "Increase quantity by 1");

            List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem));

            this.verifyCartItems(inventoryItemList);

            logger.info(String.format("Quantity of item: %s", inventoryItem.getQuantity()));
        }
    }

    public void clickReviewCartOrder() {
        logger.info("Clicking Review Cart Order button");
        javascriptExecutorUtils.scrollToView(reviewCartBtn_id);
        reviewCartBtn_id.click();

        screenshotUtils.captureAndAttach(driver, "Clicked Review Cart Order button");

        Assert.assertFalse(this.isDisplayed(reviewCartBtn_id), "Review Cart Button is displayed");
    }

    public void clickCancelOrder() {
        logger.info("Clicking Cancel Order button");
        cancelCartBtn_id.click();

        screenshotUtils.captureAndAttach(driver, "Clicked Cancel Order button");
    }

    public void clickConfirmOrder() {
        logger.info("Clicking Confirm Order button");
        confirmCartBtn_id.click();

        screenshotUtils.captureAndAttach(driver, "Clicked Confirm Order button");
    }

    public void clickConfirmOrderDoubleClick() {
        logger.info("Double clicking Confirm Order button");

        Actions actions = new Actions(driver);
        actions.doubleClick(confirmCartBtn_id).perform();

        screenshotUtils.captureAndAttach(driver, "Double clicked Confirm Order button");
    }

    public void verifyPurchaseSuccessAfterConfirmOrder(List<InventoryItem> inventoryItems) {
        logger.info("Verifying Purchase");

        Assert.assertTrue(this.isDisplayed(purchaseSuccessToast_id), "Purchase success toast is not displayed");

        Assert.assertEquals(purchaseSuccessToast_id.getAttribute("role"), "status", "Toast is not role=status");

        screenshotUtils.captureAndAttach(driver, "Purchase success toast");

        logger.info("Verifying success message");
        Assert.assertEquals(orderSuccessMessage.getText(), UserTestData.getFirstName() + ", your order was purchased successfully!", "Order success message is incorrect");

        logger.info("Verifying order details");
        String details = orderDetails.getText().replace("Order Details:\n", "");

        // Building up the order details string
        StringBuilder orderDetails = new StringBuilder(getCartHeading(inventoryItems.size()));
        orderDetails.append(": ");

        List<String> orderItemDetails = new ArrayList<>();
        int indexInventoryItem = 0;
        for (InventoryItem inventoryItem : inventoryItems) {
            orderItemDetails.add(inventoryItem.orderDetailsForPlaceOrderPurchaseToast());
            indexInventoryItem++;

            if (indexInventoryItem >= 5)
                break;
        }

        orderDetails.append(String.join(" | ", orderItemDetails));

        if (inventoryItems.size() > 5)
            orderDetails.append(String.format(" (+%s more)", inventoryItems.size() - 5));

        logger.info("Order details are: " + orderDetails);
        Assert.assertEquals(details, orderDetails.toString(), "Order details are not the same");

        logger.info("Verifying total price");
        double grandTotal = this.getGrandTotal(inventoryItems);
        Assert.assertEquals(totalPrice.getText(), "Total: " + this.formatCurrency(grandTotal));

        logger.info("Verifying the dismiss button is displayed");
        Assert.assertTrue(this.isDisplayed(dismissNotificationBtn_id), "Dismiss button is not displayed");

        logger.info("Verifying the View Invoice button is displayed");
        Assert.assertTrue(this.isDisplayed(viewHistoryBtn_id), "View Invoice button is not displayed");
    }

    public void verifyPurchaseSuccessAfterPurchase(InventoryItem inventoryItem) {
        logger.info("Verifying Purchase");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(purchaseSuccessToast_id));

        Assert.assertTrue(this.isDisplayed(purchaseSuccessToast_id), "Purchase success toast is not displayed");

        screenshotUtils.captureAndAttach(driver, "Purchase success toast");

        logger.info("Verifying success message");
        Assert.assertEquals(orderSuccessMessage.getText(), UserTestData.getFirstName() + ", your order was purchased successfully!", "Order success message is incorrect");

        logger.info("Verifying order details");
        String details = orderDetails.getText();
        Assert.assertTrue(details.toLowerCase().contains(inventoryItem.orderDetailsForPlacePurchaseToast().toLowerCase()), "Order details are missing expected item");

        logger.info("Verifying total price");
        Assert.assertEquals(totalPrice.getText(), "Total: " + this.formatCurrency(inventoryItem.getTotalPrice()));

        logger.info("Verifying the dismiss button is displayed");
        Assert.assertTrue(this.isDisplayed(dismissNotificationBtn_id), "Dismiss button is not displayed");

        logger.info("Verifying the View Invoice button is displayed");
        Assert.assertTrue(this.isDisplayed(viewHistoryBtn_id), "View Invoice button is not displayed");
    }

    public void closePurchaseSuccessToast() {
        logger.info("Dismissing the purchase success toast");
        dismissNotificationBtn_id.click();

        screenshotUtils.captureAndAttach(driver, "Purchase success toast after dismissing");
    }

    public void viewInvoiceOnPurchaseSuccessToast() {
        logger.info("View Invoice the purchase success toast");
        viewHistoryBtn_id.click();

        screenshotUtils.captureAndAttach(driver, "Purchase success toast after clicking View Invoice");
    }

    public void verifyInvoiceButton(int numberOfInvoices) {
        logger.info("Verifying invoice button");

        screenshotUtils.captureAndAttach(driver, "Verifying invoice button");

        String expectedInvoiceButtonText = String.format("Invoices (%s)", numberOfInvoices);
        String invoiceButtonText = invoicesToggleBtn_id.getText();
        Assert.assertTrue(invoiceButtonText.contains(expectedInvoiceButtonText), "Number of invoices is incorrect");

        if (numberOfInvoices == 0) {
            logger.info("Expected invoices is 0, check the message");

            this.clickInvoiceButton();

            this.verifyIfInvoiceIsEmpty();

            this.closeInvoiceHistoryPanel();
        }
    }

    public void clickInvoiceButton() {
        logger.info("Clicking Invoice button");

        invoicesToggleBtn_id.click();

        screenshotUtils.captureAndAttach(driver, "Clicked invoice button");
    }

    public Invoice verifyInvoiceAndGetInvoiceNumber(Invoice invoice) {
        logger.info("Verifying invoice");

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(visibilityOf(invoiceHistoryPanel_id));

        Assert.assertTrue(this.isDisplayed(invoiceHistoryPanel_id), "Invoice history is not displayed");

        screenshotUtils.captureAndAttach(driver, "Invoice history opened");

        Assert.assertEquals(invoiceHistoryModal_id.getAttribute("aria-label"), "Invoice History Panel", "aria-label is incorrect");

        String itemId = "invoice-item-INV-";

        // Note: the new invoice is always on top...
        WebElement invoiceItem = invoicesList_id.findElements(By.tagName("div")).get(0);

        String idNumber = invoiceItem.getAttribute("id");
        idNumber = idNumber.replace(itemId, "");

        logger.info(String.format("idNumber: %s", idNumber));

        // set the invoice number
        invoice.setInvoiceNumber("INV-" + idNumber);

        logger.info("Getting invoice datetime");
        WebElement invoiceDateTime = invoiceItem.findElement(By.id("invoice-datetime-INV-" + idNumber));
        invoice.setTimeStamp(invoiceDateTime.getText());

        logger.info("Verifying the invoice customer name");
        WebElement customerName = invoiceItem.findElement(By.id("invoice-customer-name-INV-" + idNumber));
        Assert.assertEquals(customerName.getText(), String.format("Customer: %s %s", UserTestData.getFirstName(), UserTestData.getLastName()), "Customer name is incorrect");

        logger.info("Verifying invoice item count");
        WebElement itemCount = invoiceItem.findElement(By.id("invoice-item-count-INV-" + idNumber));
        Assert.assertEquals(itemCount.getText(), this.getItemCountStr(invoice.getNumberOfItems()), "Item count is not correct");

        logger.info("Verifying invoice total price");
        WebElement totalPrice = invoiceItem.findElement(By.id("invoice-total-INV-" + idNumber));
        Assert.assertEquals(totalPrice.getText(), this.formatCurrency(invoice.getTotalPrice()));

        logger.info("Verifying the View Invoice button is displayed");
        WebElement viewInvoiceBtn = invoiceItem.findElement(By.id("view-invoice-INV-" + idNumber));
        Assert.assertTrue(this.isDisplayed(viewInvoiceBtn), "View Invoice button is not displayed");

        logger.info("Verifying the Download Invoice button is displayed");
        WebElement downloadInvoiceBtn = invoiceItem.findElement(By.id("download-invoice-INV-" + idNumber));
        Assert.assertTrue(this.isDisplayed(downloadInvoiceBtn), "Download Invoice button is not displayed");

        logger.info("Verifying the invoice Delete button");
        WebElement deleteInvoiceBtn = invoiceItem.findElement(By.id("delete-invoice-INV-" + idNumber));
        Assert.assertTrue(this.isDisplayed(deleteInvoiceBtn), "Delete Invoice button is not displayed");

        return invoice;
    }

    public void clearAllInvoices() {
        logger.info("Clearing all invoices");

        screenshotUtils.captureAndAttach(driver, "Before clearing all invoices");

        String expectedMessage = "Are you sure you want to delete ALL invoices? This action cannot be undone.";

        javascriptExecutorUtils.scrollToView(clearAllInvoicesBtn_id);

        logger.info("Clicking cancel on alert");
        clearAllInvoicesBtn_id.click();
        alertUtils.verifyIfConfirmationAlertMessageIsCorrect(expectedMessage, false);

        logger.info("Clicking confirm on alert");
        clearAllInvoicesBtn_id.click();
        alertUtils.verifyIfConfirmationAlertMessageIsCorrect(expectedMessage, true);

        this.verifyIfInvoiceIsEmpty();

        this.closeInvoiceHistoryPanel();
    }

    public Invoice orderMultipleItems(int numberOfCurrentOrders, List<InventoryItem> inventoryItemList, boolean closePurchaseToast) {
        logger.info("Order multiple items");

        this.verifyInvoiceButton(numberOfCurrentOrders);

        // Verifying the items in the cart
        this.addItemsToCart(inventoryItemList);
        this.verifyCartItems(inventoryItemList);

        // Review Cart
        this.clickReviewCartOrder();

        // Cancel Review
        this.clickCancelOrder();
        this.verifyCartItems(inventoryItemList);

        // Review Cart
        this.clickReviewCartOrder();

        // Place Order
        this.clickConfirmOrder();
        this.verifyPurchaseSuccessAfterConfirmOrder(inventoryItemList);

        if (closePurchaseToast) {
            this.closePurchaseSuccessToast();

            // Verifying number of Invoice
            this.verifyInvoiceButton(numberOfCurrentOrders + 1);

            // Clicking Invoice Button
            this.clickInvoiceButton();
        } else {
            this.viewInvoiceOnPurchaseSuccessToast();
        }

        Invoice invoice = new Invoice(inventoryItemList);

        // Verifying Invoice and Getting Invoice Number
        invoice = this.verifyInvoiceAndGetInvoiceNumber(invoice);

        return invoice;
    }

    public void closeInvoiceHistoryPanel() {
        logger.info("Close invoice history panel");
        closeInvoiceHistoryBtn_id.click();
    }

    public void deleteInvoiceFromInvoiceHistory(Invoice invoice) {
        logger.info(String.format("Deleting invoice %s from Invoice History", invoice.getInvoiceNumber()));

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(visibilityOf(invoiceHistoryPanel_id));

        Assert.assertTrue(this.isDisplayed(invoiceHistoryPanel_id), "Invoice history is not displayed");

        screenshotUtils.captureAndAttach(driver, "Verifying invoice history before deleting " + invoice.getInvoiceNumber());

        Assert.assertEquals(invoiceHistoryModal_id.getAttribute("aria-label"), "Invoice History Panel", "aria-label is incorrect");

        logger.info("Verifying the invoice Delete button");
        WebElement deleteInvoiceBtn = driver.findElement(By.id("delete-invoice-" + invoice.getInvoiceNumber()));

        logger.info("Scrolling down to Invoice Delete button");
        javascriptExecutorUtils.scrollToView(deleteInvoiceBtn);

        screenshotUtils.captureAndAttach(driver, "Scrolling down to invoice " + invoice.getInvoiceNumber());

        Assert.assertTrue(this.isDisplayed(deleteInvoiceBtn), "Delete Invoice button is not displayed");

        String expectedMessage = "Are you sure you want to delete this invoice? This action cannot be undone.";

        logger.info("Clicking cancel on alert");
        deleteInvoiceBtn.click();
        alertUtils.verifyIfConfirmationAlertMessageIsCorrect(expectedMessage, false);

        logger.info("Clicking confirm on alert");
        deleteInvoiceBtn.click();
        alertUtils.verifyIfConfirmationAlertMessageIsCorrect(expectedMessage, true);

        screenshotUtils.captureAndAttach(driver, "Verifying invoice history after deleting " + invoice.getInvoiceNumber());
    }

    public void verifyIfInvoiceIsEmpty() {
        logger.info("Verifying if invoices are cleared");
        Assert.assertTrue(this.isDisplayed(noInvoicesText_id), "No invoice text is not displayed");
    }

    public List<Invoice> purchaseMultipleItems(List<InventoryItem> inventoryItemList) {
        logger.info("Purchasing multiple items");
        int orderNumber = 0;
        List<Invoice> invoices = new ArrayList<>();

        this.verifyInvoiceButton(orderNumber);

        logger.info(String.format("Purchasing %s items", inventoryItemList.size()));

        for (InventoryItem inventoryItem : inventoryItemList) {
            logger.info(String.format("Purchasing item %s of %s", orderNumber + 1, inventoryItemList.size()));
            this.purchaseItem(inventoryItem);
            orderNumber++;

            this.verifyPurchaseSuccessAfterPurchase(inventoryItem);

            this.closePurchaseSuccessToast();

            // Verifying number of Invoice
            this.verifyInvoiceButton(Math.min(orderNumber, 10));

            // Clicking Invoice Button
            this.clickInvoiceButton();

            // Verifying Invoice and Getting Invoice Number
            Invoice invoice = new Invoice(new ArrayList<>(List.of(inventoryItem)));
            invoice = this.verifyInvoiceAndGetInvoiceNumber(invoice);
            invoices.add(invoice);

            this.closeInvoiceHistoryPanel();
        }

        return invoices;
    }

    public void verifyingInvoices(List<Invoice> invoices) {
        logger.info("Verifying invoices");

        List<Invoice> reversedInvoices = new ArrayList<>(invoices);

        // reverse items
        Collections.reverse(reversedInvoices);

        reversedInvoices = reversedInvoices.subList(0, Math.min(10, reversedInvoices.size()));

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(visibilityOf(invoiceHistoryPanel_id));

        Assert.assertTrue(this.isDisplayed(invoiceHistoryPanel_id), "Invoice history is not displayed");

        screenshotUtils.captureAndAttach(driver, "Invoice history opened");

        Assert.assertEquals(invoiceHistoryModal_id.getAttribute("aria-label"), "Invoice History Panel", "aria-label is incorrect");

        int numberOfInvoice = 0;

        for (Invoice invoice : reversedInvoices) {
            logger.info(String.format("Verifying invoice %s of %s : %s", numberOfInvoice + 1, reversedInvoices.size(), invoice.getInvoiceNumber()));

            logger.info("Verifying the invoice number");
            WebElement invoiceNumber = driver.findElement(By.id("invoice-number-" + invoice.getInvoiceNumber()));

            javascriptExecutorUtils.scrollToView(invoiceNumber);
            screenshotUtils.captureAndAttach(driver, String.format("Invoice history for invoice %s", invoice.getInvoiceNumber()));

            Assert.assertEquals(invoiceNumber.getText(), invoice.getInvoiceNumber(), "Invoice number is incorrect");

            logger.info("Verifying the invoice customer name");
            WebElement customerName = driver.findElement(By.id("invoice-customer-name-" + invoice.getInvoiceNumber()));
            Assert.assertEquals(customerName.getText(), String.format("Customer: %s %s", UserTestData.getFirstName(), UserTestData.getLastName()), "Customer name is incorrect");

            logger.info("Verifying the invoice datetime");
            WebElement invoiceDateTime = driver.findElement(By.id("invoice-datetime-" + invoice.getInvoiceNumber()));
            Assert.assertEquals(invoiceDateTime.getText(), invoice.getTimeStamp(), "Invoice datetime is incorrect");

            logger.info("Verifying invoice item count");
            WebElement itemCount = driver.findElement(By.id("invoice-item-count-" + invoice.getInvoiceNumber()));
            Assert.assertEquals(itemCount.getText(), this.getItemCountStr(invoice.getNumberOfItems()), "Item count is not correct");

            logger.info("Verifying invoice total price");
            WebElement totalPrice = driver.findElement(By.id("invoice-total-" + invoice.getInvoiceNumber()));
            Assert.assertEquals(totalPrice.getText(), this.formatCurrency(invoice.getTotalPrice()));

            logger.info("Verifying View Invoice");
            WebElement viewInvoiceBtn = driver.findElement(By.id("view-invoice-" + invoice.getInvoiceNumber()));
            this.clickViewInvoiceAndVerify(viewInvoiceBtn, invoice);

            logger.info("Verifying Download Invoice");
            WebElement downloadInvoiceBtn = driver.findElement(By.id("download-invoice-" + invoice.getInvoiceNumber()));
            this.clickDownloadInvoiceAndVerify(downloadInvoiceBtn, invoice);

            numberOfInvoice++;
        }

        softAssert.assertAll();
    }

    public void addItemAndRemoveExistingItemOnReviewStep(InventoryItem inventoryItem, List<InventoryItem> previousItems) {
        logger.info(String.format("Submit a valid inventory form with Device Type (%s), Brand (%s), Storage (%s), Quantity (%s), Color (%s), and Address (%s)\n" +
                        "with Shipping (%s), Warranty (%s), Discount Code (%s)",
                inventoryItem.getDeviceType(), inventoryItem.getBrand(), inventoryItem.getStorage(), inventoryItem.getQuantity(), inventoryItem.getColor(), inventoryItem.getAddress(),
                inventoryItem.getShippingMethod(), inventoryItem.getWarranty(), inventoryItem.getDiscountCode()));

        this.enterInventoryFormData(inventoryItem);

        this.validatePrice(inventoryItem);

        this.validateImage(inventoryItem);

        this.submitInventoryForm();

        this.enterExtraDetails(inventoryItem);

        // wait until Step 2 is displayed
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(inventoryReviewStep_id));

        screenshotUtils.captureAndAttach(driver, "Navigating to Next step");

        this.validateInventoryDetails(inventoryItem);

        logger.info("Checking if the cart items is visible");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(cartItemsPreview_id));

        javascriptExecutorUtils.scrollToView(cartItemsPreview_id);
        screenshotUtils.captureAndAttach(driver, "Cart items");

        WebElement cartItem = cartItemsPreviewList.findElements(By.tagName("div")).get(0);
        InventoryItem currentItem = previousItems.get(0);

        javascriptExecutorUtils.scrollToView(cartItem);

        logger.info("Verifying item: " + currentItem.getItemDescription());
        WebElement itemDescription = cartItem.findElement(By.xpath(".//div[1]/span[1]"));
        Assert.assertEquals(itemDescription.getText(), currentItem.getItemDescription(), "Item description is incorrect");

        logger.info("Verifying warranty");
        WebElement itemWarranty = cartItem.findElement(By.xpath(".//div[1]/span[2]"));
        Assert.assertEquals(itemWarranty.getText(), "Warranty: " + currentItem.getWarranty(), "Item warranty is incorrect");

        logger.info("Verifying shipping");
        WebElement itemShipping = cartItem.findElement(By.xpath(".//div[1]/span[3]"));
        Assert.assertEquals(itemShipping.getText(), "Ship: " + currentItem.getShippingMethod(), "Item shipping is incorrect");

        if (currentItem.getDiscountPrice() > 0) {
            logger.info("Verifying discount");
            WebElement itemDiscount = cartItem.findElement(By.xpath(".//div[1]/span[4]"));
            Assert.assertEquals(itemDiscount.getText(), "Discount: " + currentItem.getDiscountPercentageAsString(), "Item discount is incorrect");
        }

        logger.info("Verifying price");
        WebElement itemTotalPrice = cartItem.findElement(By.xpath(".//div[2]"));
        Assert.assertEquals(itemTotalPrice.getText(), this.formatCurrency(currentItem.getTotalPrice()), "Item total price is incorrect");

        logger.info("Removing item from cart");
        WebElement removeItemBtn = cartItem.findElement(By.xpath(".//button"));
        Assert.assertTrue(removeItemBtn.isDisplayed(), "Remove item button is not displayed");

        removeItemBtn.click();
        previousItems.remove(currentItem);

        screenshotUtils.captureAndAttach(driver, "Removed previous item");

        logger.info("Add current item");
        this.clickAddItemToCartButton();

        logger.info("Verifying if previous item is removed");
        this.verifyCartItems(new ArrayList<>(List.of(inventoryItem)));

        this.validateBlankInventoryForm();
    }

    public String getCartAsHtml() {
        return javascriptExecutorUtils.getOuterHtml(cartSummary_id);
    }

    public void insertCartItem(String cartItemHtml) {
        javascriptExecutorUtils.insertElementIntoContainer(inventoryCard_id, cartItemHtml);

        screenshotUtils.captureAndAttach(driver, "DOM tweak to add items to UI");
    }

    public void removeInjectedElements() {
        javascriptExecutorUtils.removeInjectedElements();

        screenshotUtils.captureAndAttach(driver, "Cleaned up injected elements");
    }

    public void validatingPreviewImages() {
        logger.info("Validating preview images");

        logger.info("Validating default");
        Assert.assertFalse(this.isDisplayed(devicePreview_id), "Device preview is displayed");

        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");

        logger.info("Verifying phone image");
        this.selectDeviceType(inventoryItem.getDeviceType());
        this.validateImage(inventoryItem);

        logger.info("Verifying Apple phone image");
        inventoryItem.setBrand(Enums.Brand.APPLE.getDisplayName());
        this.selectBrand(inventoryItem.getBrand());
        this.validateImage(inventoryItem);

        logger.info("Verifying Samsung phone image");
        inventoryItem.setBrand(Enums.Brand.SAMSUNG.getDisplayName());
        this.selectBrand(inventoryItem.getBrand());
        this.validateImage(inventoryItem);

        logger.info("Verifying Xiaomi phone image");
        inventoryItem.setBrand(Enums.Brand.XIAOMI.getDisplayName());
        this.selectBrand(inventoryItem.getBrand());
        this.validateImage(inventoryItem);

        logger.info("Verifying Other phone image");
        inventoryItem.setBrand(Enums.Brand.OTHER.getDisplayName());
        this.selectBrand(inventoryItem.getBrand());
        this.validateImage(inventoryItem);


        logger.info("Verifying tablet image");
        inventoryItem.setDeviceType(Enums.DeviceType.TABLET.getDisplayName());
        this.selectDeviceType(inventoryItem.getDeviceType());
        this.validateImage(inventoryItem);


        logger.info("Verifying laptop image");
        inventoryItem.setDeviceType(Enums.DeviceType.TABLET.getDisplayName());
        this.selectDeviceType(inventoryItem.getDeviceType());
        this.validateImage(inventoryItem);
    }
    // </editor-fold>

    // <editor-fold desc="Private Methods">
    // <editor-fold desc="Enter Data into Inventory Form">
    private void selectDeviceType(String selectedDeviceType) {
        new Select(deviceType_id).selectByVisibleText(selectedDeviceType);
        javascriptExecutorUtils.triggerManualOnChange(deviceType_id);
    }

    private void selectBrand(String selectedBrand) {
        if (brand_id.isEnabled()) {
            new Select(brand_id).selectByVisibleText(selectedBrand);
            javascriptExecutorUtils.triggerManualOnChange(brand_id);
        }
    }

    private void selectQuantity(int selectedQuantity) {
        // quantity_id.clear();
        quantity_id.click();
        for (int i = 15; i >= 0; i--) {
            quantity_id.sendKeys(Keys.ARROW_DOWN);
        }

        screenshotUtils.captureAndAttach(driver, "Checking if quantity is 1");

        logger.info(String.format("Entering quantity: %s", selectedQuantity));
        quantity_id.click();

        int maximumQuantity = Integer.parseInt(quantity_id.getAttribute("max"));

        for (int i = 1; i < selectedQuantity; i++) {
            quantity_id.sendKeys(Keys.ARROW_UP);

            if (i >= maximumQuantity)
                break;
        }

        logger.info("Quantity is: " + quantity_id.getAttribute("value"));

        if (!quantity_id.getAttribute("value").equals(Integer.toString(selectedQuantity))) {
            logger.info("Quantity is not the same as entered; try enter manually");
            quantity_id.clear();
            quantity_id.sendKeys(Integer.toString(selectedQuantity));
        }

        screenshotUtils.captureAndAttach(driver, "Checking if quantity is entered correct");
    }

    private void selectColor(String selectedColor) {
        new Select(color_id).selectByVisibleText(selectedColor);
    }

    private void typeAddress(String address) {
        //address_id.clear();

        // since there is a onChange event
        address_id.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);

        address_id.sendKeys(address);
    }

    private void selectRadioButton(List<WebElement> radioButtonList, String selectedValue) {
        for (WebElement radio : radioButtonList) {
            if (radio.getAttribute("value").equals(selectedValue)) {
                radio.click();
                break;
            }
        }
    }

    private void selectRadioButtonWithKeyboard(List<WebElement> radioButtons, String valueToSelect) {
        Actions actions = new Actions(driver);

        // Focus the first radio button
        WebElement firstRadio = radioButtons.get(0);
        firstRadio.click();

        for (WebElement radio : radioButtons) {
            String val = radio.getAttribute("value");
            if (val.equals(valueToSelect)) {
                // Press space bar to select the focused radio
                actions.sendKeys(Keys.SPACE).perform();
                screenshotUtils.captureAndAttach(driver, "Desired item selected");
                break;
            } else {
                // Move focus to next radio in the group (right arrow)
                actions.sendKeys(Keys.ARROW_RIGHT).perform();
            }
        }
    }

    private void typeDiscountCode(String discountCode) {
        //discountCode_id.clear();

        // since there is a onChange event
        discountCode_id.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);

        discountCode_id.sendKeys(discountCode);
    }

    private void enterInventoryFormData(InventoryItem inventoryItem) {
        screenshotUtils.captureAndAttach(driver, "Checking Inventory Form before entering data");

        this.selectDeviceType(inventoryItem.getDeviceType());
        this.selectBrand(inventoryItem.getBrand());
        this.selectRadioButton(storages, inventoryItem.getStorage());
        this.selectQuantity(inventoryItem.getQuantity());
        this.selectColor(inventoryItem.getColor());
        this.typeAddress(inventoryItem.getAddress());

        logger.info("Form is populated");

        screenshotUtils.captureAndAttach(driver, "Submitting inventory form");

        // verify purchase device form is enabled
        Assert.assertTrue(inventoryNextBtn_id.isEnabled(), "Next Button is disabled...");
    }

    private void enterExtraDetails(InventoryItem inventoryItem) {
        javascriptExecutorUtils.scrollToTop();

        // select shipping method
        logger.info("Selecting shipping method");
        this.selectRadioButton(shippingMethods, inventoryItem.getShippingMethod());

        // select warranty
        logger.info("Selecting warranty");
        this.selectRadioButton(warranties, inventoryItem.getWarranty());

        // enter discount
        logger.info("Entering discount");
        this.enterDiscount(inventoryItem.getDiscountCode());
    }
    // </editor-fold>

    // <editor-fold desc="Click Buttons">
    private void submitInventoryForm() {
        logger.info("Submit form");
        inventoryNextBtn_id.click();
    }

    private void submitApplyDiscountForm() {
        logger.info("Submit apply discount form");
        //javascriptExecutorUtils.scrollToView(applyDiscountBtn_id);

        applyDiscountBtn_id.click();

        screenshotUtils.captureAndAttach(driver, "Submitted discount code");
    }

    private void clickPurchaseDeviceButton() {
        logger.info("Clicking Purchase Device button");
        purchaseDeviceBtn_id.click();
    }

    private void clickAddItemToCartButton() {
        logger.info("Adding item to cart");
        addToCartBtn_id.click();
    }
    // </editor-fold>

    // <editor-fold desc="Validating Details">
    private void validateInventoryDetails(InventoryItem item) {
        logger.info("Validating that the correct details are displayed");
        List<String> expectedSummary = new ArrayList<>();
        expectedSummary.add("Type: " + item.getDeviceType().toLowerCase());
        expectedSummary.add("Brand: " + item.getBrand().toLowerCase());
        expectedSummary.add("Storage: " + item.getStorage());
        expectedSummary.add("Color: " + item.getColor().toLowerCase());
        expectedSummary.add("Quantity: " + item.getQuantity());
        expectedSummary.add("Address: " + item.getAddress());

        for (WebElement fieldRow : deviceSummaryList_id.findElements(By.tagName("li"))) {
            String fieldName = fieldRow.getText();
            expectedSummary.remove(fieldName);
        }

        Assert.assertEquals(0, expectedSummary.size(), "All the expected fields are not present");
    }

    private void validateUnitPrice(String pricePerUnitFormatted) {
        Assert.assertEquals(unitPriceValue_id.getText(), pricePerUnitFormatted, "Price per unit is incorrect");
    }

    private void validateQuantity(int quantity) {
        String quantityFormatted = Integer.toString(quantity);
        Assert.assertEquals(quantityValue_id.getText(), quantityFormatted, "Quantity is incorrect");
    }

    private void validateSubTotal(String subTotalFormatted) {
        Assert.assertEquals(subtotalValue_id.getText(), subTotalFormatted, "Subtotal is incorrect");
    }

    private void validatePrice(InventoryItem item) {
        logger.info("Validate the price on the form");

        javascriptExecutorUtils.scrollToView(step1PricingSummary_id);
        screenshotUtils.captureAndAttach(driver, "Validating the price");

        logger.info("Validate unit price");
        this.validateUnitPrice(this.formatCurrency(item.getUnitPrice()));

        logger.info(String.format("Validate quantity is %s", item.getQuantity()));
        this.validateQuantity(item.getQuantity());

        logger.info("Validate sub total");
        this.validateSubTotal(this.formatCurrency(item.getSubTotalPrice()));
    }

    private void validateImage(InventoryItem item) {
        logger.info(
                String.format("Validating image with Device Name %s and Brand %s", item.getDeviceType(), item.getBrand())
        );
        screenshotUtils.captureAndAttach(
                driver,
                String.format("Validate image with Device Name %s and Brand %s", item.getDeviceType(), item.getBrand())
        );
        String deviceType = item.getDeviceType();

        if (deviceType.equalsIgnoreCase(Enums.DeviceType.LAPTOP.getDisplayName())) {
            WebElement image = devicePreview_id.findElement(By.tagName("img"));
            Assert.assertTrue(image.getAttribute("src").contains("laptop"), "Laptop image is not displayed");
        } else if (deviceType.equalsIgnoreCase(Enums.DeviceType.TABLET.getDisplayName())) {
            WebElement image = devicePreview_id.findElement(By.tagName("img"));
            Assert.assertTrue(image.getAttribute("src").contains("tablet"), "Tablet image is not displayed");
        } else if (deviceType.equals(Enums.DeviceType.PHONE.getDisplayName())) {
            String brand = item.getBrand();

            if (brand.isEmpty() || brand.equalsIgnoreCase(Enums.Brand.OTHER.getDisplayName())) {
                WebElement image = devicePreview_id.findElement(By.xpath(".//*[name()='svg']"));
                Assert.assertTrue(image.isDisplayed(), "Phone svg is not displayed");

                String svgText = image.findElement(By.xpath(".//*[name()='text']")).getText();

                if (brand.isEmpty())
                    Assert.assertEquals(svgText, "Device", "Default image is not displayed");
                else
                    Assert.assertEquals(svgText, "Other", "Default image is not displayed");
            } else {
                WebElement image = devicePreview_id.findElement(By.tagName("img"));

                if (brand.equalsIgnoreCase(Enums.Brand.APPLE.getDisplayName())) {
                    Assert.assertTrue(image.getAttribute("src").contains("iphone"), "Apple phone is not displayed");
                } else if (brand.equalsIgnoreCase(Enums.Brand.SAMSUNG.getDisplayName())) {
                    Assert.assertTrue(image.getAttribute("src").contains("sumsung"), "Samsung phone is not displayed");
                } else if (brand.equalsIgnoreCase(Enums.Brand.XIAOMI.getDisplayName())) {
                    Assert.assertTrue(image.getAttribute("src").contains("xiami"), "Xiaomi phone is not displayed");
                }
            }


        }
    }

    private void validatePriceOnReviewPage(InventoryItem item) {
        // verify that the price is correct
        String pricePerUnitFormatted = this.formatCurrency(item.getUnitPrice());
        String quantityFormatted = Integer.toString(item.getQuantity());
        String subTotalFormatted = this.formatCurrency(item.getSubTotalPrice());
        String shippingPriceFormatted = this.formatCurrency(item.getShippingPrice());
        String warrantyPriceFormatted = this.formatCurrency(item.getWarrantyPrice());
        String discountPriceFormatted = this.formatCurrency(item.getDiscountPrice());
        String totalPriceFormatted = this.formatCurrency(item.getTotalPrice());

        logger.info(String.format("Price per unit: %s", pricePerUnitFormatted));
        logger.info(String.format("Sub total: %s", subTotalFormatted));
        logger.info(String.format("Shipping: %s", shippingPriceFormatted));
        logger.info(String.format("Warranty: %s", warrantyPriceFormatted));
        logger.info(String.format("Discount price: %s", discountPriceFormatted));
        logger.info(String.format("Total price: %s", totalPriceFormatted));

        Assert.assertEquals(basePriceValue_id.getText(), pricePerUnitFormatted, "Price per unit is incorrect");
        Assert.assertEquals(breakdownQuantityValue_id.getText(), quantityFormatted, "Quantity is incorrect");
        Assert.assertEquals(breakdownSubtotalValue_id.getText(), subTotalFormatted, "Sub total is incorrect");
        Assert.assertEquals(breakdownWarrantyValue_id.getText(), warrantyPriceFormatted, "Warranty price is incorrect");
        Assert.assertEquals(breakdownShippingValue_id.getText(), shippingPriceFormatted, "Shipping price is incorrect");

        if (item.getDiscountPrice() > 0)
            Assert.assertEquals(breakdownDiscountValue_id.getText(), discountPriceFormatted, "Discount is incorrect");

        Assert.assertEquals(breakdownTotalValue_id.getText(), totalPriceFormatted, "Total price is incorrect");

        javascriptExecutorUtils.scrollToView(basePriceValue_id);

        screenshotUtils.captureAndAttach(driver, "Verifying price on Review page");
    }
    // </editor-fold>

    // <editor-fold desc="Formatting Currency">
    private String formatCurrency(int currency) {
        if (currency >= 0)
            return "R" + currency + ".00";
        else
            return "- R" + Math.abs(currency) + ".00";
    }

    private String formatCurrency(double currency) {
        if (currency >= 0)
            return "R" + String.format(Locale.US, "%.2f", currency);
        else
            return "- R" + String.format(Locale.US, "%.2f", Math.abs(currency));
    }
    // </editor-fold>

    // <editor-fold desc="Discount Codes">
    private void enterDiscount(String discountCode) {
        logger.info(String.format("Enter discount code: %s", discountCode));
        this.typeDiscountCode(discountCode);

        screenshotUtils.captureAndAttach(driver, String.format("Entered discount code %s", discountCode));

        this.submitApplyDiscountForm();

        if (discountCode.isEmpty()) {
            logger.info("No discount code entered...");
            return;
        }

        logger.info("Validating discount");
        String expectedDiscountMessage = validateDiscount(discountCode);
        Assert.assertEquals(discountFeedback_id.getText(), expectedDiscountMessage, "Discount feedback is incorrect");

        screenshotUtils.captureAndAttach(driver, "Validating discount");
    }

    private String validateDiscount(String discountCode) {
        return switch (discountCode) {
            case "" -> "";
            case "SAVE10" -> "Code SAVE10 applied: -10%";
            case "SAVE20" -> "Code SAVE20 applied: -20%";
            default -> "Invalid code";
        };
    }
    // </editor-fold>

    // <editor-fold desc="Valid Inventory Form Submission">
    private void validInventoryFormSubmission(InventoryItem inventoryItem) {
        logger.info(String.format("Submit a valid inventory form with Device Type (%s), Brand (%s), Storage (%s), Quantity (%s), Color (%s), and Address (%s)\n" +
                        "with Shipping (%s), Warranty (%s), Discount Code (%s)",
                inventoryItem.getDeviceType(), inventoryItem.getBrand(), inventoryItem.getStorage(), inventoryItem.getQuantity(), inventoryItem.getColor(), inventoryItem.getAddress(),
                inventoryItem.getShippingMethod(), inventoryItem.getWarranty(), inventoryItem.getDiscountCode()));

        this.enterInventoryFormData(inventoryItem);

        this.validatePrice(inventoryItem);

        this.validateImage(inventoryItem);

        this.submitInventoryForm();

        // wait until Step 2 is displayed
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(inventoryReviewStep_id));

        screenshotUtils.captureAndAttach(driver, "Navigating to Next step");

        this.validateInventoryDetails(inventoryItem);

        // enter extra details
        this.enterExtraDetails(inventoryItem);

        screenshotUtils.captureAndAttach(driver, "Verifying shipping method, warranty and discount");

        this.validatePriceOnReviewPage(inventoryItem);
    }
    // </editor-fold>

    // <editor-fold desc="Cart Methods">
    private List<WebElement> getCartItems() {
        return cartItems.stream()
                .filter(e -> e.getAttribute("id").matches("cart-item-\\d+(\\.\\d+)?"))
                .toList();
    }

    private double getGrandTotal(List<InventoryItem> inventoryItems) {
        double grandTotal = 0;

        for (InventoryItem inventoryItem : inventoryItems) {
            grandTotal += inventoryItem.getTotalPrice();
        }

        return grandTotal;
    }

    private String getCartHeading(int expectedNumberOfItems) {
        return String.format("Cart (%s)", getItemCountStr(expectedNumberOfItems));
    }

    private String getItemCountStr(int expectedNumberOfItems) {
        String itemStr = expectedNumberOfItems == 1 ? "item" : "items";
        return String.format("%s %s", expectedNumberOfItems, itemStr);
    }
    // </editor-fold>

    // <editor-fold desc="Generic Methods">
    private boolean isDisplayed(WebElement element) {
        boolean isPresent;
        try {
            isPresent = element.isDisplayed();
        } catch (NoSuchElementException e) {
            isPresent = false;
        }

        return isPresent;
    }
    // </editor-fold>

    // <editor-fold desc="Invoice Methods">
    private void clickViewInvoiceAndVerify(WebElement viewInvoiceButton, Invoice invoice) {
        logger.info(String.format("Clicking View Invoice button for %s", invoice.getInvoiceNumber()));

        // save current window handle
        String originalWindow = driver.getWindowHandle();
        Set<String> existingHandles = driver.getWindowHandles();

        // click button
        viewInvoiceButton.click();

        logger.info("Waiting until new tab is opened");
        new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(d -> driver.getWindowHandles().size() > existingHandles.size());

        screenshotUtils.captureAndAttach(driver, "Clicked on View Invoice button");

        // find the new tab (difference between before/after)
        String newWindow = driver.getWindowHandles().stream()
                .filter(handle -> !existingHandles.contains(handle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New window not found"));

        logger.info("Switch to new tab");
        driver.switchTo().window(newWindow);

        screenshotUtils.captureAndAttach(driver, "Opened Invoice tab");

        Dimension windowSize = driver.manage().window().getSize();
        int midX = windowSize.getWidth() / 2;
        int midY = windowSize.getHeight() / 2;

        logger.info("Verifying tab title");
        Assert.assertEquals(driver.getTitle(), "Invoice " + invoice.getInvoiceNumber(), "Invoice title is incorrect");

        logger.info("Verifying company logo");
        String logoSrc = invoiceCompanyLogo.getAttribute("src");
        Assert.assertTrue(logoSrc.startsWith("data:image/png;base64,"), "Logo is missing or not base64 encoded");

        logger.info("Verifying that the company logo is on left");
        Point companyLogoPosition = invoiceCompanyLogo.getLocation();
        Assert.assertTrue(companyLogoPosition.getX() < midX, "Company logo is not on left side of the page");
        Assert.assertTrue(companyLogoPosition.getY() < midY, "Company logo is not on top side of the page");

        logger.info("Verifying that the company name is on right");
        Point companyNamePosition = invoiceCompanyName.getLocation();
        Assert.assertTrue(companyNamePosition.getX() > midX, "Company name is not on right side of the page");
        Assert.assertTrue(companyNamePosition.getY() < midY, "Company name is not on top side of the page");

        logger.info("Verifying customer name");
        Assert.assertEquals(invoiceCustomerName.getText(), String.format("%s %s", UserTestData.getFirstName(), UserTestData.getLastName()), "Customer name on invoice is incorrect");

        logger.info("Verifying customer email");
        Assert.assertEquals(invoiceCustomerEmail.getText(), UserTestData.getUniqueEmail(), "Customer email on invoice is incorrect");

        logger.info("Verifying customer address");
        Assert.assertEquals(invoiceCustomerAddress.getText(), invoice.getAddress(), "Address on invoice is incorrect");

        logger.info("Verifying invoice number");
        Assert.assertEquals(invoiceDetailsNumber.getText(), "Invoice #: " + invoice.getInvoiceNumber(), "Invoice number on invoice is incorrect");

        logger.info("Verifying invoice date");
        Assert.assertEquals(invoiceDetailsDate.getText(), "Date: " + invoice.getDate(), "Invoice date on invoice is incorrect");

        logger.info("Verifying invoice time");
        Assert.assertEquals(invoiceDetailsTime.getText(), "Time: " + invoice.getTime(), "Invoice time on invoice is incorrect");

        logger.info("Verifying items in invoice");
        int itemNumber = 0;
        for (WebElement row : invoiceItems) {
            InventoryItem item = invoice.getInventoryItems().get(itemNumber);

            logger.info("Verifying invoice item: " + item.getItemDescription());
            logger.info(String.format("Item details: unit (%s), subtotal (%s), shipping (%s), warranty(%s), discount (%s), total (%s)",
                    item.getUnitPrice(), item.getSubTotalPrice(), item.getShippingPrice(), item.getWarrantyPrice(), item.getDiscountPrice(), item.getTotalPrice()));

            String description = row.findElement(By.cssSelector("td:nth-of-type(1) strong")).getText().toLowerCase();
            String details = row.findElement(By.cssSelector("td:nth-of-type(1) small")).getText().toLowerCase();
            String qty = row.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String unitPrice = row.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String totalPrice = row.findElement(By.cssSelector("td:nth-of-type(4)")).getText();

            String itemDescription = String.format("%s %s", item.getBrand(), item.getDeviceType()).toLowerCase();
            Assert.assertEquals(description, itemDescription, "Item description is incorrect");

            Assert.assertTrue(details.contains(item.getStorage().toLowerCase()), "Item details does not contain storage");
            Assert.assertTrue(details.contains(item.getColor().toLowerCase()), "Item details does not contain color");
            Assert.assertTrue(details.contains(item.getWarrantyOnPurchaseToast().toLowerCase()), "Item details does not contain warranty");
            Assert.assertTrue(details.contains(item.getShippingMethod().toLowerCase()), "Item details does not contain shipping");

            Assert.assertEquals(qty, Integer.toString(item.getQuantity()), "Item quantity is incorrect");
            Assert.assertEquals(unitPrice, this.formatCurrency(item.getUnitPrice()), "Item unit price is incorrect");

            this.softAssert.assertEquals(totalPrice, this.formatCurrency(item.getTotalPrice()), "Item total price is incorrect");

            itemNumber++;
        }

        logger.info("Verifying invoice sub total");
        softAssert.assertEquals(invoiceSubTotal.getText(), "Subtotal: " + this.formatCurrency(invoice.getSubTotalPrice()), "Invoice subtotal is incorrect");

        if (invoice.getShippingPrice() > 0) {
            logger.info("Verifying invoice shipping");
            softAssert.assertEquals(invoiceShipping.getText(), "Shipping: " + this.formatCurrency(invoice.getShippingPrice()), "Invoice shipping is incorrect");
        }

        if (invoice.getWarrantyPrice() > 0) {
            logger.info("Verifying invoice warranty");
            softAssert.assertEquals(invoiceWarranty.getText(), "Warranty: " + this.formatCurrency(invoice.getWarrantyPrice()), "Invoice warranty is incorrect");
        }

        if (invoice.getDiscountPrice() > 0) {
            logger.info("Verifying invoice discount");
            softAssert.assertEquals(invoiceDiscount.getText(), "Discount: " + this.formatCurrency(invoice.getDiscountPrice()), "Invoice discount is incorrect");
        }

        logger.info("Verifying invoice total");
        softAssert.assertEquals(invoiceTotal.getText(), "Total: " + this.formatCurrency(invoice.getTotalPrice()), "Invoice total is incorrect");

        logger.info("Verifying the thank you message");
        Assert.assertEquals(thankYouMessage.getText(), "Thank you for your business!", "Thank you message is incorrect");

        // Close invoice tab
        logger.info("Closing invoice tab");
        driver.close();

        // Switch back to main tab
        logger.info("Switching back to main tab");
        driver.switchTo().window(originalWindow);

        screenshotUtils.captureAndAttach(driver, "Back to website tab");
    }

    private void clickDownloadInvoiceAndVerify(WebElement downloadInvoiceButton, Invoice invoice) {
        logger.info(String.format("Clicking Download Invoice button for %s", invoice.getInvoiceNumber()));

        downloadInvoiceButton.click();

        logger.info("File downloaded");

        String filename = "Invoice-" + invoice.getInvoiceNumber() + ".pdf";

        logger.info("Getting invoice");
        String downloadPath = fileUtils.getDownloadPath();
        String pathname = downloadPath + File.separator + filename;

        File downloadedInvoice = new File(pathname);

        // Wait until file exists
        new WebDriverWait(driver, Duration.ofSeconds(200))
                .until(d -> downloadedInvoice.exists());

        Assert.assertTrue(downloadedInvoice.exists(), "Invoice PDF was not downloaded!");

        // Parse PDF text
        logger.info("Parsing the pdf");
        String pdfText;
        try (PDDocument document = Loader.loadPDF(downloadedInvoice)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfText = pdfStripper.getText(document);
        } catch (IOException e) {
            logger.info("An exception occurred while parsing the pdf");
            throw new RuntimeException(e);
        }

        logger.info("Attaching invoice to test");
        fileUtils.addFileToTest(filename);

        System.out.println(pdfText);

        // Verifying expected details
        logger.info("Verifying customer name");
        Assert.assertTrue(pdfText.contains(String.format("%s %s", UserTestData.getFirstName(), UserTestData.getLastName())), "Customer name on downloaded invoice is incorrect");

        logger.info("Verifying customer email");
        Assert.assertTrue(pdfText.contains(UserTestData.getUniqueEmail()), "Customer email on downloaded invoice is incorrect");

        logger.info("Verifying customer address");
        Assert.assertTrue(pdfText.contains(invoice.getAddress()), "Customer address on downloaded invoice is incorrect");

        logger.info("Verifying invoice number");
        Assert.assertTrue(pdfText.contains("Invoice #: " + invoice.getInvoiceNumber()), "Invoice number on downloaded invoice is incorrect");

        logger.info("Verifying invoice date");
        Assert.assertTrue(pdfText.contains("Date: " + invoice.getDate()), "Invoice date on downloaded invoice is incorrect");

        logger.info("Verifying invoice time");
        Assert.assertTrue(pdfText.contains("Time: " + invoice.getTime()), "Invoice time on downloaded invoice is incorrect");

        logger.info("Verifying items in invoice");
        for (InventoryItem item : invoice.getInventoryItems()) {
            logger.info("Verifying invoice item: " + item.getItemDescription());

            Assert.assertTrue(pdfText.toLowerCase().contains(String.format("%s %s", item.getBrand(), item.getDeviceType()).toLowerCase()), "Item description on downloaded invoice is incorrect");
            Assert.assertTrue(pdfText.toLowerCase().contains(item.getStorage().toLowerCase()), "Item details on downloaded invoice does not contain storage");
            Assert.assertTrue(pdfText.toLowerCase().contains(item.getColor().toLowerCase()), "Item details on downloaded invoice does not contain color");
            Assert.assertTrue(pdfText.toLowerCase().contains(item.getWarrantyOnPurchaseToast().toLowerCase()), "Item details on downloaded invoice does not contain warranty");
            Assert.assertTrue(pdfText.toLowerCase().contains(item.getShippingMethod().toLowerCase()), "Item details on downloaded invoice does not contain shipping");
            Assert.assertTrue(pdfText.contains(Integer.toString(item.getQuantity())), "Item quantity on downloaded invoice is incorrect");
            Assert.assertTrue(pdfText.contains(this.formatCurrency(item.getUnitPrice())), "Item unit price on downloaded invoice is incorrect");

            this.softAssert.assertTrue(pdfText.contains(this.formatCurrency(item.getTotalPrice())), "Item total price on downloaded invoice is incorrect");
        }

        logger.info("Creating list with the expected text on invoice");

        logger.info("Verifying invoice sub total");
        softAssert.assertTrue(pdfText.contains("Subtotal: " + this.formatCurrency(invoice.getSubTotalPrice())), "Invoice subtotal on downloaded invoice is incorrect");

        if (invoice.getShippingPrice() > 0) {
            logger.info("Verifying invoice shipping");
            softAssert.assertTrue(pdfText.contains(this.formatCurrency(invoice.getShippingPrice())), "Invoice shipping on downloaded invoice is incorrect");
        }

        if (invoice.getWarrantyPrice() > 0) {
            logger.info("Verifying invoice warranty");
            softAssert.assertTrue(pdfText.contains("Warranty: " + this.formatCurrency(invoice.getWarrantyPrice())), "Invoice warranty on downloaded invoice is incorrect");
        }

        if (invoice.getDiscountPrice() > 0) {
            logger.info("Verifying invoice discount");
            softAssert.assertTrue(pdfText.contains("Discount: " + this.formatCurrency(invoice.getDiscountPrice())), "Invoice discount on downloaded invoice is incorrect");
        }

        logger.info("Verifying invoice total");
        softAssert.assertTrue(pdfText.contains("Total: " + this.formatCurrency(invoice.getTotalPrice())), "Invoice total on downloaded invoice is incorrect");

        logger.info("Verifying the thank you message");
        Assert.assertTrue(pdfText.contains("Thank you for your business!"), "Thank you message on downloaded invoice is incorrect");
    }
    // </editor-fold>
    // </editor-fold>

}
