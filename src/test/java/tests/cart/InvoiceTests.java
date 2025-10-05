package tests.cart;

import enums.Enums;
import model.InventoryItem;
import model.Invoice;
import org.testng.annotations.Test;
import tests.TestsBase;
import utils.UserTestData;

import java.util.ArrayList;
import java.util.List;

public class InvoiceTests extends TestsBase {

    // <editor-fold desc="Set Up">
    @Test(description = "Navigate to Login Page", groups = "11. Invoice Tests")
    public void navigateToLoginPage() {
        homePage.verifyHomePageIsDisplayed();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();
    }

    @Test(description = "Login user to start Inventory Form testing", groups = "11. Invoice Tests", dependsOnMethods = "navigateToLoginPage")
    public void validLoginCredentialsToStartInventoryFormTesting() {
        loginPage.validCredentialsLogin();

        learningMaterialsPage.verifyLearningMaterialsPageIsDisplayed();
    }

    @Test(description = "Verify that the Web Automation Tab opens", groups = "11. Invoice Tests", dependsOnMethods = "validLoginCredentialsToStartInventoryFormTesting")
    public void verifyWebAutomationTabOpens() {
        learningMaterialsPage.clickWebAutomationTab();

        inventoryPage.verifyWebAutomationTabOpened();

        inventoryPage.validateBlankInventoryForm();
    }
    // </editor-fold>

    // <editor-fold desc="Invoice Tests">
    @Test(description = "Place order for single item and close toast", groups = "11. Invoice Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 2)
    public void placeOrderForSingleItemAndCloseToast() {
        // Defining inventory items
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1));

        Invoice invoice = inventoryPage.orderMultipleItems(0, inventoryItemList, true);

        List<Invoice> invoices = new ArrayList<>(List.of(invoice));

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Place order and close toast", groups = "11. Invoice Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 3)
    public void placeOrderAndCloseToast() {
        // Defining inventory items
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));

        Invoice invoice = inventoryPage.orderMultipleItems(0, inventoryItemList, true);

        List<Invoice> invoices = new ArrayList<>(List.of(invoice));

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Place order and view invoice", groups = "11. Invoice Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 4)
    public void placeOrderAndViewInvoice() {
        // Defining inventory items
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));

        Invoice invoice = inventoryPage.orderMultipleItems(0, inventoryItemList, false);

        List<Invoice> invoices = new ArrayList<>(List.of(invoice));

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Place order with discount", groups = "11. Invoice Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 5)
    public void placeOrderWithDiscount() {
        // Defining inventory items
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "SAVE10");
        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "SAVE10");
        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "SAVE10");

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));

        Invoice invoice = inventoryPage.orderMultipleItems(0, inventoryItemList, true);

        List<Invoice> invoices = new ArrayList<>(List.of(invoice));

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Place order with more than 10 items", groups = "11. Invoice Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 6)
    public void placeOrderWithMoreThanTenItems() {
        // Defining inventory items
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "White", UserTestData.address, "standard", "none", "SAVE50");
        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem4 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.SAMSUNG.getDisplayName(), "128GB", 10, "Black", UserTestData.address, "express", "none", "random");
        InventoryItem inventoryItem5 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.SAMSUNG.getDisplayName(), "128GB", 10, "Blue", UserTestData.address, "standard", "1yr", "");
        InventoryItem inventoryItem6 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_AIR.getDisplayName(), "128GB", 8, "Black", UserTestData.address, "standard", "none", "SAVE10");
        InventoryItem inventoryItem7 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.XIAOMI.getDisplayName(), "128GB", 6, "White", UserTestData.address, "express", "2yr", "SAVE50");
        InventoryItem inventoryItem8 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 10, "Black", UserTestData.address, "standard", "1yr", "");
        InventoryItem inventoryItem9 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "128GB", 8, "Black", UserTestData.address, "standard", "none", "SAVE20");
        InventoryItem inventoryItem10 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.OTHER.getDisplayName(), "256GB", 6, "Blue", UserTestData.address, "express", "2yr", "");
        InventoryItem inventoryItem11 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.SAMSUNG.getDisplayName(), "256GB", 10, "Gold", UserTestData.address, "standard", "1yr", "");
        InventoryItem inventoryItem12 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.OTHER.getDisplayName(), "128GB", 8, "White", UserTestData.address, "standard", "none", "SAVE10");

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3, inventoryItem4, inventoryItem5, inventoryItem6, inventoryItem7, inventoryItem8, inventoryItem9, inventoryItem10, inventoryItem11, inventoryItem12));

        Invoice invoice = inventoryPage.orderMultipleItems(0, inventoryItemList, true);

        List<Invoice> invoices = new ArrayList<>(List.of(invoice));

        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);

        // clear all invoices
        inventoryPage.clearAllInvoices();

        inventoryPage.verifyInvoiceButton(0);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Delete single invoice", groups = "11. Invoice Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 7)
    public void deleteSingleInvoice() {
        int orderNumber = 0;
        List<Invoice> invoices = new ArrayList<>();

        // Defining inventory items for order 1
        InventoryItem inventoryItem1_1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem1_2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "White", UserTestData.address, "standard", "none", "SAVE50");
        InventoryItem inventoryItem1_3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem1_4 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.SAMSUNG.getDisplayName(), "128GB", 10, "Black", UserTestData.address, "express", "none", "random");
        InventoryItem inventoryItem1_5 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.SAMSUNG.getDisplayName(), "128GB", 10, "Blue", UserTestData.address, "standard", "1yr", "");

        List<InventoryItem> inventoryItemList1 = new ArrayList<>(List.of(inventoryItem1_1, inventoryItem1_2, inventoryItem1_3, inventoryItem1_4, inventoryItem1_5));

        Invoice invoice1 = inventoryPage.orderMultipleItems(orderNumber, inventoryItemList1, true);
        orderNumber++;
        invoices.add(invoice1);


        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);


        // Deleting first invoice from Invoice History
        inventoryPage.deleteInvoiceFromInvoiceHistory(invoice1);
        invoices.remove(invoice1);
        orderNumber--;

        // Closing Invoice History Panel
        inventoryPage.closeInvoiceHistoryPanel();

        // Verify Invoice Button
        inventoryPage.verifyInvoiceButton(orderNumber);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Order multiple items in separate orders", groups = "11. Invoice Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 8)
    public void orderMultipleItemsInSeparateOrders() {
        int orderNumber = 0;
        List<Invoice> invoices = new ArrayList<>();

        // Defining inventory items for order 1
        InventoryItem inventoryItem1_1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem1_2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "White", UserTestData.address, "standard", "none", "SAVE50");
        InventoryItem inventoryItem1_3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        InventoryItem inventoryItem1_4 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.SAMSUNG.getDisplayName(), "128GB", 10, "Black", UserTestData.address, "express", "none", "random");
        InventoryItem inventoryItem1_5 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.SAMSUNG.getDisplayName(), "128GB", 10, "Blue", UserTestData.address, "standard", "1yr", "");

        List<InventoryItem> inventoryItemList1 = new ArrayList<>(List.of(inventoryItem1_1, inventoryItem1_2, inventoryItem1_3, inventoryItem1_4, inventoryItem1_5));

        Invoice invoice1 = inventoryPage.orderMultipleItems(orderNumber, inventoryItemList1, true);
        orderNumber++;
        invoices.add(invoice1);

        inventoryPage.closeInvoiceHistoryPanel();


        // Defining inventory items for order 2
        InventoryItem inventoryItem2_1 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_AIR.getDisplayName(), "128GB", 8, "Black", UserTestData.address, "standard", "none", "SAVE10");
        InventoryItem inventoryItem2_2 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.XIAOMI.getDisplayName(), "128GB", 6, "White", UserTestData.address, "express", "2yr", "SAVE50");
        InventoryItem inventoryItem2_3 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 10, "Black", UserTestData.address, "standard", "1yr", "");
        InventoryItem inventoryItem2_4 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "128GB", 8, "Black", UserTestData.address, "standard", "none", "SAVE20");
        InventoryItem inventoryItem2_5 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.OTHER.getDisplayName(), "256GB", 6, "Blue", UserTestData.address, "express", "2yr", "");

        List<InventoryItem> inventoryItemList2 = new ArrayList<>(List.of(inventoryItem2_1, inventoryItem2_2, inventoryItem2_3, inventoryItem2_4, inventoryItem2_5));
        Invoice invoice2 = inventoryPage.orderMultipleItems(orderNumber, inventoryItemList2, true);
        orderNumber++;
        invoices.add(invoice2);

        inventoryPage.closeInvoiceHistoryPanel();


        // Defining inventory items for order 3
        InventoryItem inventoryItem3_1 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.SAMSUNG.getDisplayName(), "256GB", 10, "Gold", UserTestData.address, "standard", "1yr", "");
        InventoryItem inventoryItem3_2 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.OTHER.getDisplayName(), "128GB", 8, "White", UserTestData.address, "standard", "none", "SAVE10");

        // Verifying the items in the cart
        List<InventoryItem> inventoryItemList3 = new ArrayList<>(List.of(inventoryItem3_1, inventoryItem3_2));
        Invoice invoice3 = inventoryPage.orderMultipleItems(orderNumber, inventoryItemList3, true);
        orderNumber++;
        invoices.add(invoice3);

        inventoryPage.closeInvoiceHistoryPanel();


        // Defining inventory items for order 4
        InventoryItem inventoryItem4_1 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_AIR.getDisplayName(), "128GB", 8, "Blue", UserTestData.address, "standard", "none", "SAVE10");
        InventoryItem inventoryItem4_2 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.XIAOMI.getDisplayName(), "128GB", 6, "White", UserTestData.address, "express", "2yr", "SAVE50");
        InventoryItem inventoryItem4_3 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 10, "Gold", UserTestData.address, "standard", "1yr", "");
        InventoryItem inventoryItem4_4 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "128GB", 8, "Black", UserTestData.address, "standard", "none", "SAVE20");
        InventoryItem inventoryItem4_5 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.SAMSUNG.getDisplayName(), "256GB", 6, "Blue", UserTestData.address, "express", "2yr", "");

        List<InventoryItem> inventoryItemList4 = new ArrayList<>(List.of(inventoryItem4_1, inventoryItem4_2, inventoryItem4_3, inventoryItem4_4, inventoryItem4_5));
        Invoice invoice4 = inventoryPage.orderMultipleItems(orderNumber, inventoryItemList4, true);
        orderNumber++;
        invoices.add(invoice4);


        // Verifying all the invoices
        inventoryPage.verifyingInvoices(invoices);


        // Deleting first invoice from Invoice History
        inventoryPage.deleteInvoiceFromInvoiceHistory(invoice1);
        invoices.remove(invoice1);
        orderNumber--;

        // Closing Invoice History Panel
        inventoryPage.closeInvoiceHistoryPanel();

        // Verify Invoice Button
        inventoryPage.verifyInvoiceButton(orderNumber);


        // Click Invoice Button
        inventoryPage.clickInvoiceButton();

        // Deleting second invoice from Invoice History
        inventoryPage.deleteInvoiceFromInvoiceHistory(invoice2);
        invoices.remove(invoice2);
        orderNumber--;

        // Closing Invoice History Panel
        inventoryPage.closeInvoiceHistoryPanel();

        // Verify Invoice Button
        inventoryPage.verifyInvoiceButton(orderNumber);


        // Click Invoice Button
        inventoryPage.clickInvoiceButton();

        // Deleting second invoice from Invoice History
        inventoryPage.deleteInvoiceFromInvoiceHistory(invoice3);
        invoices.remove(invoice3);
        orderNumber--;

        // Closing Invoice History Panel
        inventoryPage.closeInvoiceHistoryPanel();

        // Verify Invoice Button
        inventoryPage.verifyInvoiceButton(orderNumber);


        // Click Invoice Button
        inventoryPage.clickInvoiceButton();

        // Deleting second invoice from Invoice History
        inventoryPage.deleteInvoiceFromInvoiceHistory(invoice4);
        invoices.remove(invoice4);
        orderNumber--;

        // Closing Invoice History Panel
        inventoryPage.closeInvoiceHistoryPanel();

        // Verify Invoice Button
        inventoryPage.verifyInvoiceButton(orderNumber);

        inventoryPage.checkSoftAssertion();
    }
    // </editor-fold>

}
