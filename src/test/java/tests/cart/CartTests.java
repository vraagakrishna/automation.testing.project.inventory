package tests.cart;

import enums.Enums;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import model.InventoryItem;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.TestsBase;
import utils.UserTestData;

import java.util.ArrayList;
import java.util.List;

public class CartTests extends TestsBase {

    // <editor-fold desc="Set Up">
    @Override
    protected void setUpPage() {
        homePage.verifyHomePageIsDisplayed();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();

        loginPage.validCredentialsLogin();

        learningMaterialsPage.verifyLearningMaterialsPageIsDisplayed();

        learningMaterialsPage.clickWebAutomationTab();

        inventoryPage.verifyWebAutomationTabOpened();

        inventoryPage.validateBlankInventoryForm();
    }
    // </editor-fold>

    // <editor-fold desc="Cart Tests">
    @Test(description = "Add item to cart", groups = "8. Cart Tests", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void addItemToCart() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);


        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1));
        inventoryPage.verifyCartItems(inventoryItemList);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Add items to cart", groups = "8. Cart Tests", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void addItemsToCart() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(2);

        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem3);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(3);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));
        inventoryPage.verifyCartItems(inventoryItemList);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Add items to cart and remove first item", groups = "8. Cart Tests", priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    public void addItemsToCartAndRemoveFirst() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(2);

        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem3);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(3);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));
        inventoryPage.verifyCartItems(inventoryItemList);
        inventoryPage.removeFirstItemInCart(inventoryItemList);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Add items to cart and remove last item", groups = "8. Cart Tests", priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    public void addItemsToCartAndRemoveLast() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(2);

        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem3);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(3);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));
        inventoryPage.verifyCartItems(inventoryItemList);
        inventoryPage.removeLastItemInCart(inventoryItemList);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Add items to cart and remove discounted item", groups = "8. Cart Tests", priority = 6)
    @Severity(SeverityLevel.BLOCKER)
    public void addItemsToCartAndRemoveDiscountedItem() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "SAVE20");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(2);

        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem3);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(3);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));
        inventoryPage.verifyCartItems(inventoryItemList);
        inventoryPage.removeFirstItemInCart(inventoryItemList);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Add items to cart and reduce quantity", groups = "8. Cart Tests", priority = 7)
    @Severity(SeverityLevel.CRITICAL)
    public void addItemsToCartAndReduceQuantity() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 10, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1));
        inventoryPage.verifyCartItems(inventoryItemList);

        inventoryPage.reduceQuantityOfItem(inventoryItem1);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Add items to cart and increase quantity", groups = "8. Cart Tests", priority = 8)
    @Severity(SeverityLevel.CRITICAL)
    public void addItemsToCartAndIncreaseQuantity() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1));
        inventoryPage.verifyCartItems(inventoryItemList);

        inventoryPage.increaseQuantityOfItem(inventoryItem1);

        inventoryPage.checkSoftAssertion();
    }
    // </editor-fold>

    // <editor-fold desc="Cart Tests Additional">
    @Test(description = "Add multiple items to cart", groups = "8. Cart Tests", priority = 9)
    @Severity(SeverityLevel.BLOCKER)
    public void multiDevice() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);


        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "256GB", 1, "Gold", UserTestData.address, "express", "1yr", "SAVE10");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(2);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2));
        inventoryPage.verifyCartItems(inventoryItemList);

        // Removes Laptop
        inventoryPage.removeLastItemInCart(inventoryItemList);
        inventoryPage.verifyNumberOfItemsInCart(1);
        inventoryItemList.remove(inventoryItem2);
        inventoryPage.verifyCartItems(inventoryItemList);

        // Add Laptop back
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.verifyNumberOfItemsInCart(2);
        inventoryItemList.add(inventoryItem2);
        inventoryPage.verifyCartItems(inventoryItemList);

        // Remove Phone
        inventoryPage.removeFirstItemInCart(inventoryItemList);
        inventoryPage.verifyNumberOfItemsInCart(1);
        inventoryItemList.remove(inventoryItem1);
        inventoryPage.verifyCartItems(inventoryItemList);

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Remove item on cart review step", groups = "8. Cart Tests", priority = 10)
    @Severity(SeverityLevel.CRITICAL)
    public void removeItemOnCartReview() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "SAVE10");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);


        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "256GB", 1, "Gold", UserTestData.address, "express", "1yr", "SAVE10");

        inventoryPage.addItemAndRemoveExistingItemOnReviewStep(inventoryItem2, new ArrayList<>(List.of(inventoryItem1)));

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Double click on Place Order button", groups = "8. Cart Tests", priority = 11)
    @Severity(SeverityLevel.CRITICAL)
    public void doubleClickOnPlaceOrder() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);


        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(2);


        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2));
        inventoryPage.verifyCartItems(inventoryItemList);


        // click Review Cart
        inventoryPage.clickReviewCartOrder();


        // click Confirm Order
        inventoryPage.clickConfirmOrderDoubleClick();

        inventoryPage.verifyPurchaseSuccessAfterConfirmOrder(inventoryItemList);

        inventoryPage.closePurchaseSuccessToast();

        Assert.assertFalse(inventoryPage.isCartSummaryVisible(), "Cart summary is still visible");

        inventoryPage.checkSoftAssertion();
    }

    @Test(description = "Place order with empty cart", groups = "8. Cart Tests", priority = 12)
    @Severity(SeverityLevel.MINOR)
    public void placeOrderWithEmptyCart() {
        InventoryItem inventoryItem = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), Enums.Brand.MACBOOK_PRO.getDisplayName(), "256GB", 1, "Gold", UserTestData.address, "express", "1yr", "SAVE10");
        inventoryPage.addItemToCart(inventoryItem);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);


        // click Review Cart
        inventoryPage.clickReviewCartOrder();

        // get cart item as html
        String cartItemHtml = inventoryPage.getCartAsHtml();

        // remove all the items in the cart
        inventoryPage.removeAllItemsInCart();

        // add the cart item using DOM tweak
        inventoryPage.insertCartItem(cartItemHtml);

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);


        // click Confirm Order
        inventoryPage.clickConfirmOrder();

        // no toast should be shown
        Assert.assertFalse(inventoryPage.isPurchaseSuccessToastVisible(), "Purchase success toast is visible");

        inventoryPage.checkSoftAssertion();
    }
    // </editor-fold>

}
