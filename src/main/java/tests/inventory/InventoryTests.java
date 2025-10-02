package tests.inventory;

import enums.Enums;
import model.InventoryItem;
import org.testng.annotations.Test;
import tests.TestsBase;
import utils.UserTestData;

public class InventoryTests extends TestsBase {

    // <editor-fold desc="Set Up">
    @Test(description = "Navigate to Login Page", groups = "5. Inventory Form")
    public void navigateToLoginPage() {
        homePage.verifyHomePageIsDisplayed();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();
    }

    @Test(description = "Login user to start Inventory Form testing", groups = "5. Inventory Form", dependsOnMethods = "navigateToLoginPage")
    public void validLoginCredentialsToStartInventoryFormTesting() {
        loginPage.validCredentialsLogin();

        learningMaterialsPage.verifyLearningMaterialsPageIsDisplayed();
    }

    @Test(description = "Verify that the Web Automation Tab opens", groups = "5. Inventory Form", dependsOnMethods = "validLoginCredentialsToStartInventoryFormTesting")
    public void verifyWebAutomationTabOpens() {
        learningMaterialsPage.clickWebAutomationTab();

        inventoryPage.verifyWebAutomationTabOpened();

        inventoryPage.validateBlankInventoryForm();
    }
    // </editor-fold>

    // <editor-fold desc="Negative Tests">
    @Test(description = "Blank inventory form submission", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 2)
    public void blankInventoryFormSubmission() {
        inventoryPage.blankInventoryFormSubmission();
    }

    @Test(description = "Submit inventory form with valid device type", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 3)
    public void validDeviceTypeSubmission() {
        inventoryPage.validDeviceTypeSubmission();
    }

    @Test(description = "Change selection of device type", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 4)
    public void changeSelectionOfDeviceType() {
        inventoryPage.changeSelectionOfDeviceType();
    }

    @Test(description = "Submit inventory form with valid device type and brand", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 5)
    public void validDeviceTypeAndBrandSubmission() {
        inventoryPage.validDeviceTypeAndBrandSubmission();
    }

    @Test(description = "Submit inventory form with valid device type, brand and storage", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 6)
    public void validDeviceTypeAndBrandAndStorageSubmission() {
        inventoryPage.validDeviceTypeAndBrandAndStorageSubmission();
    }

    @Test(description = "Submit inventory form with valid device type, brand, storage, quantity, but invalid address", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 7)
    public void invalidAddressSubmission() {
        inventoryPage.invalidAddressSubmission();
    }

    @Test(description = "Submit inventory form with valid device type, brand, storage, but invalid quantity", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 8)
    public void invalidQuantitySubmission() {
        inventoryPage.invalidQuantitySubmission();
    }

    @Test(description = "Submit inventory form with valid device type, brand, storage, address, but invalid quantity amount", groups = "6. Inventory Form Negative Tests", dependsOnMethods = {"verifyWebAutomationTabOpens", "invalidQuantitySubmission"}, priority = 9)
    public void invalidQuantityAmountSubmission() {
        inventoryPage.invalidQuantityAmountSubmission(11);
    }
    // </editor-fold>

    // <editor-fold desc="Additional Coverage Tests">
    @Test(description = "Partial form reset", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 10)
    public void partialFormReset() {
        inventoryPage.partiallyFillInInventoryForm();

        learningMaterialsPage.clickApiTestingTab();

        learningMaterialsPage.clickWebAutomationTab();

        inventoryPage.verifyWebAutomationTabOpened();

        inventoryPage.validateBlankInventoryForm();
    }

    @Test(description = "Submit inventory form with 999 quantity", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 11)
    public void largeQuantityAmountSubmission() {
        inventoryPage.invalidQuantityAmountSubmission(999);
    }
    // </editor-fold>

    // <editor-fold desc="Price Verification Tests">
    @Test(description = "Inventory form phone price verification", groups = "7. Inventory Form Price Verification Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 12)
    public void inventoryFormPhonePriceVerification() {
        inventoryPage.inventoryFormDevicePriceVerification(Enums.DeviceType.PHONE.getDisplayName());
    }

    @Test(description = "Inventory form tablet price verification", groups = "7. Inventory Form Price Verification Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 13)
    public void inventoryFormTabletPriceVerification() {
        inventoryPage.inventoryFormDevicePriceVerification(Enums.DeviceType.TABLET.getDisplayName());
    }

    @Test(description = "Inventory form laptop price verification", groups = "7. Inventory Form Price Verification Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 14)
    public void inventoryFormLaptopPriceVerification() {
        inventoryPage.inventoryFormDevicePriceVerification(Enums.DeviceType.LAPTOP.getDisplayName());
    }

    @Test(description = "Inventory form price verification on changing device type", groups = "7. Inventory Form Price Verification Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 15)
    public void inventoryFormPriceVerificationOnChangingDeviceType() {
        inventoryPage.inventoryFormDevicePriceVerificationOnChangingDeviceType(
                Enums.DeviceType.PHONE.getDisplayName(), Enums.DeviceType.TABLET.getDisplayName()
        );
    }
    // </editor-fold>

    // <editor-fold desc="Positive Tests">
    @Test(description = "Select 256GB storage with keyboard", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 16)
    public void select256GBStorageWithKeyboard() {
        String selectedStorage = "256GB";
        inventoryPage.selectRadioButtonWithKeyboard(selectedStorage);
    }

    @Test(description = "Select 64GB storage with keyboard", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 17)
    public void select64GBStorageWithKeyboard() {
        String selectedStorage = "64GB";
        inventoryPage.selectRadioButtonWithKeyboard(selectedStorage);
    }

    @Test(description = "Submit a valid inventory form with one phone", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 18)
    public void validInventoryFormSubmissionWithOnePhone() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with one tablet", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 19)
    public void validInventoryFormSubmissionWithOneTablet() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with one laptop", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 20)
    public void validInventoryFormSubmissionWithOneLaptop() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with two phone", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 21)
    public void validInventoryFormSubmissionWithTwoPhones() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "64GB", 2, "Blue", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with two tablets", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 22)
    public void validInventoryFormSubmissionWithTwoTablet() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with two laptops", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 23)
    public void validInventoryFormSubmissionWithTwoLaptop() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with two 128GB phone", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 24)
    public void validInventoryFormSubmissionWithTwo128GBPhones() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "128GB", 2, "Blue", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with two 128GB tablets", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 25)
    public void validInventoryFormSubmissionWithTwo128GBTablets() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "128GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with two 128GB laptops", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 26)
    public void validInventoryFormSubmissionWithTwo128GBLaptops() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "128GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with two 256GB phone", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 27)
    public void validInventoryFormSubmissionWithTwo256GBPhones() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "256GB", 2, "Blue", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with two 256GB tablets", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 28)
    public void validInventoryFormSubmissionWithTwo256GBTablets() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "256GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with two 256GB laptops", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 29)
    public void validInventoryFormSubmissionWithTwo256GBLaptops() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with phone in express shipping", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 30)
    public void validInventoryFormSubmissionWithPhoneExpressShipping() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "256GB", 2, "Blue", UserTestData.address, "express", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with tablet in express shipping", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 31)
    public void validInventoryFormSubmissionWithTabletExpressShipping() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "256GB", 2, "Gold", UserTestData.address, "express", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with laptop in express shipping", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 32)
    public void validInventoryFormSubmissionWithLaptopExpressShipping() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 2, "Gold", UserTestData.address, "express", "none", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with phone in 1 year warranty", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 33)
    public void validInventoryFormSubmissionWithPhoneOneYearWarranty() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "256GB", 2, "Blue", UserTestData.address, "standard", "1yr", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with tablet in 1 year warranty", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 34)
    public void validInventoryFormSubmissionWithTabletOneYearWarranty() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "256GB", 2, "Gold", UserTestData.address, "express", "1yr", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with laptop in 1 year warranty", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 35)
    public void validInventoryFormSubmissionWithLaptopOneYearWarranty() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 2, "Gold", UserTestData.address, "express", "1yr", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with phone in 2 year warranty", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 36)
    public void validInventoryFormSubmissionWithPhoneTwoYearWarranty() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "256GB", 2, "Blue", UserTestData.address, "standard", "2yr", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with tablet in 2 year warranty", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 37)
    public void validInventoryFormSubmissionWithTabletTwoYearWarranty() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "256GB", 2, "Gold", UserTestData.address, "express", "2yr", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with laptop in 2 year warranty", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 38)
    public void validInventoryFormSubmissionWithLaptopTwoYearWarranty() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 2, "Gold", UserTestData.address, "express", "2yr", "");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with phone with 10 percent discount", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 39)
    public void validInventoryFormSubmissionWithPhoneTenPercentDiscount() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "256GB", 2, "Blue", UserTestData.address, "standard", "2yr", "SAVE10");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with tablet with 10 percent discount", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 40)
    public void validInventoryFormSubmissionWithTabletTenPercentDiscount() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "256GB", 2, "Gold", UserTestData.address, "express", "2yr", "SAVE10");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with laptop with 10 percent discount", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 41)
    public void validInventoryFormSubmissionWithLaptopTenPercentDiscount() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 2, "Gold", UserTestData.address, "express", "2yr", "SAVE10");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with phone with 20 percent discount", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 42)
    public void validInventoryFormSubmissionWithPhoneTwentyPercentDiscount() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "256GB", 2, "Blue", UserTestData.address, "standard", "2yr", "SAVE20");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with tablet with 20 percent discount", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 43)
    public void validInventoryFormSubmissionWithTabletTwentyPercentDiscount() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "256GB", 2, "Gold", UserTestData.address, "express", "2yr", "SAVE20");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit a valid inventory form with laptop with 20 percent discount", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 44)
    public void validInventoryFormSubmissionWithLaptopTwentyPercentDiscount() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 2, "Gold", UserTestData.address, "express", "2yr", "SAVE20");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }

    @Test(description = "Submit inventory form with invalid discount", groups = "8. Inventory Form Positive Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 45)
    public void inventoryFormSubmissionWithInvalidDiscount() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 2, "Gold", UserTestData.address, "express", "2yr", "SAVE50");
        inventoryPage.validAndResetInventoryFormSubmission(inventoryItem);
    }
    // </editor-fold>

    // <editor-fold desc="Additional Coverage Tests 2">
    @Test(description = "Discount removal", groups = "6. Inventory Form Negative Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 46)
    public void discountRemoval() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 2, "Gold", UserTestData.address, "express", "2yr", "SAVE20");
        inventoryPage.discountRemoval(inventoryItem);
    }
    // </editor-fold>

}
