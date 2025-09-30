package tests.cart;

import model.InventoryItem;
import model.Invoice;
import org.testng.annotations.Test;
import tests.TestsBase;
import utils.UserTestData;

import java.util.ArrayList;
import java.util.List;

public class PurchaseTests extends TestsBase {

    // <editor-fold desc="Set Up">
    @Test(description = "Navigate to Login Page", groups = "10. Purchase Items Tests")
    public void navigateToLoginPage() {
        homePage.verifyHomePageIsDisplayed();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();
    }

    @Test(description = "Login user to start Inventory Form testing", groups = "10. Purchase Items Tests", dependsOnMethods = "navigateToLoginPage")
    public void validLoginCredentialsToStartInventoryFormTesting() {
        loginPage.validCredentialsLogin();

        learningMaterialsPage.verifyLearningMaterialsPageIsDisplayed();
    }

    @Test(description = "Verify that the Web Automation Tab opens", groups = "10. Purchase Items Tests", dependsOnMethods = "validLoginCredentialsToStartInventoryFormTesting")
    public void verifyWebAutomationTabOpens() {
        learningMaterialsPage.clickWebAutomationTab();

        inventoryPage.verifyWebAutomationTabOpened();

        inventoryPage.validateBlankInventoryForm();
    }
    // </editor-fold>

    // <editor-fold desc="Purchase Items Tests">
    @Test(description = "Purchase item and close toast", groups = "10. Purchase Items Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 2)
    public void purchaseItemAndCloseToast() {
        inventoryPage.verifyInvoiceButton(0);

        // Purchase item
        InventoryItem inventoryItem1 = new InventoryItem("Phone", "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.purchaseItem(inventoryItem1);

        inventoryPage.verifyPurchaseSuccessAfterPurchase(inventoryItem1);

        inventoryPage.closePurchaseSuccessToast();

        // Verifying number of Invoice
        inventoryPage.verifyInvoiceButton(1);

        // Clicking Invoice Button
        inventoryPage.clickInvoiceButton();

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1));
        Invoice invoice1 = new Invoice(inventoryItemList);

        // Verifying Invoice and Getting Invoice Number
        invoice1 = inventoryPage.verifyInvoiceAndGetInvoiceNumber(invoice1);

        List<Invoice> invoices = new ArrayList<>(List.of(invoice1));

        inventoryPage.closeInvoiceHistoryPanel();

        // Click Invoice Button
        inventoryPage.clickInvoiceButton();

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);
    }

    @Test(description = "Purchase item and view invoice", groups = "10. Purchase Items Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 3)
    public void purchaseItemAndViewInvoice() {
        inventoryPage.verifyInvoiceButton(0);

        // Purchase item
        InventoryItem inventoryItem1 = new InventoryItem("Phone", "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.purchaseItem(inventoryItem1);

        inventoryPage.verifyPurchaseSuccessAfterPurchase(inventoryItem1);

        inventoryPage.viewInvoiceOnPurchaseSuccessToast();

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1));
        Invoice invoice1 = new Invoice(inventoryItemList);

        // Verifying Invoice and Getting Invoice Number
        invoice1 = inventoryPage.verifyInvoiceAndGetInvoiceNumber(invoice1);

        List<Invoice> invoices = new ArrayList<>(List.of(invoice1));

        inventoryPage.closeInvoiceHistoryPanel();

        // Click Invoice Button
        inventoryPage.clickInvoiceButton();

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);
    }

    @Test(description = "Purchase multiple items", groups = "10. Purchase Items Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 4)
    public void purchaseMultipleItems() {
        InventoryItem inventoryItem1 = new InventoryItem("Phone", "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem2 = new InventoryItem("Tablet", "Apple", "256GB", 1, "Black", UserTestData.address, "express", "none", "SAVE10");
        InventoryItem inventoryItem3 = new InventoryItem("Laptop", "Other", "64GB", 10, "Blue", UserTestData.address, "standard", "1yr", "SAVE20");
        InventoryItem inventoryItem4 = new InventoryItem("Phone", "Samsung", "128GB", 1, "White", UserTestData.address, "standard", "2yr", "");

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3, inventoryItem4));
        List<Invoice> invoices = inventoryPage.purchaseMultipleItems(inventoryItemList);

        // Click Invoice Button
        inventoryPage.clickInvoiceButton();

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);
    }

    @Test(description = "Purchase 10 items", groups = "10. Purchase Items Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 5)
    public void purchaseTenItems() {
        InventoryItem inventoryItem1 = new InventoryItem("Phone", "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem2 = new InventoryItem("Tablet", "Apple", "256GB", 1, "Black", UserTestData.address, "express", "none", "SAVE10");
        InventoryItem inventoryItem3 = new InventoryItem("Laptop", "Other", "64GB", 10, "Blue", UserTestData.address, "standard", "1yr", "SAVE20");
        InventoryItem inventoryItem4 = new InventoryItem("Phone", "Samsung", "128GB", 1, "White", UserTestData.address, "standard", "2yr", "");
        InventoryItem inventoryItem5 = new InventoryItem("Tablet", "Other", "64GB", 1, "Gold", UserTestData.address, "express", "none", "SAVE10");
        InventoryItem inventoryItem6 = new InventoryItem("Laptop", "Macbook pro", "256GB", 10, "Blue", UserTestData.address, "standard", "1yr", "SAVE20");
        InventoryItem inventoryItem7 = new InventoryItem("Phone", "Xiaomi", "128GB", 1, "White", UserTestData.address, "standard", "2yr", "");
        InventoryItem inventoryItem8 = new InventoryItem("Tablet", "Samsung", "64GB", 6, "Gold", UserTestData.address, "express", "none", "SAVE10");
        InventoryItem inventoryItem9 = new InventoryItem("Laptop", "Macbook air", "256GB", 10, "White", UserTestData.address, "standard", "1yr", "SAVE20");
        InventoryItem inventoryItem10 = new InventoryItem("Tablet", "Other", "64GB", 1, "Gold", UserTestData.address, "express", "none", "SAVE10");

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3, inventoryItem4, inventoryItem5, inventoryItem6, inventoryItem7, inventoryItem8, inventoryItem9, inventoryItem10));
        List<Invoice> invoices = inventoryPage.purchaseMultipleItems(inventoryItemList);

        // Click Invoice Button
        inventoryPage.clickInvoiceButton();

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);
    }

    @Test(description = "Purchase more than 10 items", groups = "10. Purchase Items Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 6)
    public void purchaseMoreThanTenItems() {
        InventoryItem inventoryItem1 = new InventoryItem("Phone", "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem2 = new InventoryItem("Tablet", "Apple", "256GB", 1, "Black", UserTestData.address, "express", "none", "SAVE10");
        InventoryItem inventoryItem3 = new InventoryItem("Laptop", "Other", "64GB", 10, "Blue", UserTestData.address, "standard", "1yr", "SAVE20");
        InventoryItem inventoryItem4 = new InventoryItem("Phone", "Samsung", "128GB", 1, "White", UserTestData.address, "standard", "2yr", "");
        InventoryItem inventoryItem5 = new InventoryItem("Tablet", "Other", "64GB", 1, "Gold", UserTestData.address, "express", "none", "SAVE10");
        InventoryItem inventoryItem6 = new InventoryItem("Laptop", "Macbook pro", "256GB", 10, "Blue", UserTestData.address, "standard", "1yr", "SAVE20");
        InventoryItem inventoryItem7 = new InventoryItem("Phone", "Xiaomi", "128GB", 1, "White", UserTestData.address, "standard", "2yr", "");
        InventoryItem inventoryItem8 = new InventoryItem("Tablet", "Samsung", "64GB", 6, "Gold", UserTestData.address, "express", "none", "SAVE10");
        InventoryItem inventoryItem9 = new InventoryItem("Laptop", "Macbook air", "256GB", 10, "White", UserTestData.address, "standard", "1yr", "SAVE20");
        InventoryItem inventoryItem10 = new InventoryItem("Tablet", "Other", "64GB", 1, "Gold", UserTestData.address, "express", "none", "SAVE10");
        InventoryItem inventoryItem11 = new InventoryItem("Phone", "Other", "128GB", 8, "Black", UserTestData.address, "standard", "2yr", "");
        InventoryItem inventoryItem12 = new InventoryItem("Tablet", "Other", "64GB", 1, "Gold", UserTestData.address, "express", "none", "SAVE10");
        InventoryItem inventoryItem13 = new InventoryItem("Laptop", "Macbook pro", "256GB", 10, "Blue", UserTestData.address, "standard", "1yr", "SAVE20");

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3, inventoryItem4, inventoryItem5, inventoryItem6, inventoryItem7, inventoryItem8, inventoryItem9, inventoryItem10, inventoryItem11, inventoryItem12, inventoryItem13));
        List<Invoice> invoices = inventoryPage.purchaseMultipleItems(inventoryItemList);

        // Click Invoice Button
        inventoryPage.clickInvoiceButton();

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);
    }
    // </editor-fold>

}
