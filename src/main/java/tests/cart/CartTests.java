package tests.cart;

import enums.Enums;
import model.InventoryItem;
import org.testng.annotations.Test;
import tests.TestsBase;
import utils.UserTestData;

import java.util.ArrayList;
import java.util.List;

public class CartTests extends TestsBase {

    // <editor-fold desc="Set Up">
    @Test(description = "Navigate to Login Page", groups = "9. Cart Tests")
    public void navigateToLoginPage() {
        homePage.verifyHomePageIsDisplayed();

        homePage.clickLearningMaterial();

        loginPage.verifyLoginPageIsDisplayed();
    }

    @Test(description = "Login user to start Inventory Form testing", groups = "9. Cart Tests", dependsOnMethods = "navigateToLoginPage")
    public void validLoginCredentialsToStartInventoryFormTesting() {
        loginPage.validCredentialsLogin();

        learningMaterialsPage.verifyLearningMaterialsPageIsDisplayed();
    }

    @Test(description = "Verify that the Web Automation Tab opens", groups = "9. Cart Tests", dependsOnMethods = "validLoginCredentialsToStartInventoryFormTesting")
    public void verifyWebAutomationTabOpens() {
        learningMaterialsPage.clickWebAutomationTab();

        inventoryPage.verifyWebAutomationTabOpened();

        inventoryPage.validateBlankInventoryForm();
    }
    // </editor-fold>

    // <editor-fold desc="Cart Tests">
    @Test(description = "Add items to cart", groups = "9. Cart Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 2)
    public void addItemsToCart() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(2);

        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem3);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(3);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));
        inventoryPage.verifyCartItems(inventoryItemList);
        inventoryPage.removeAllItemsInCart();
    }

    @Test(description = "Add items to cart and remove first item", groups = "9. Cart Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 3)
    public void addItemsToCartAndRemoveFirst() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(2);

        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem3);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(3);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));
        inventoryPage.verifyCartItems(inventoryItemList);
        inventoryPage.removeFirstItemInCart(inventoryItemList);

        inventoryPage.removeAllItemsInCart();
    }

    @Test(description = "Add items to cart and remove last item", groups = "9. Cart Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 4)
    public void addItemsToCartAndRemoveLast() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(2);

        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem3);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(3);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));
        inventoryPage.verifyCartItems(inventoryItemList);
        inventoryPage.removeLastItemInCart(inventoryItemList);

        inventoryPage.removeAllItemsInCart();
    }

    @Test(description = "Add items to cart and remove discounted item", groups = "9. Cart Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 5)
    public void addItemsToCartAndRemoveDiscountedItem() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "SAVE20");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.TABLET.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem2);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(2);

        InventoryItem inventoryItem3 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "64GB", 2, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem3);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.verifyNumberOfItemsInCart(3);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1, inventoryItem2, inventoryItem3));
        inventoryPage.verifyCartItems(inventoryItemList);
        inventoryPage.removeFirstItemInCart(inventoryItemList);

        inventoryPage.removeAllItemsInCart();
    }

    @Test(description = "Add items to cart and reduce quantity", groups = "9. Cart Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 6)
    public void addItemsToCartAndReduceQuantity() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "64GB", 10, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1));
        inventoryPage.verifyCartItems(inventoryItemList);

        inventoryPage.reduceQuantityOfItem(inventoryItem1);

        inventoryPage.removeAllItemsInCart();
    }

    @Test(description = "Add items to cart and increase quantity", groups = "9. Cart Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 7)
    public void addItemsToCartAndIncreaseQuantity() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), "Apple", "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);

        List<InventoryItem> inventoryItemList = new ArrayList<>(List.of(inventoryItem1));
        inventoryPage.verifyCartItems(inventoryItemList);

        inventoryPage.increaseQuantityOfItem(inventoryItem1);

        inventoryPage.removeAllItemsInCart();
    }
    // </editor-fold>

    // <editor-fold desc="Cart Tests Additional">
    @Test(description = "Add multiple items to cart", groups = "9. Cart Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 7)
    public void multiDevice() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);


        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 1, "Gold", UserTestData.address, "express", "1yr", "SAVE10");
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

        // Clean up
        inventoryPage.removeAllItemsInCart();
    }

    @Test(description = "Remove item on cart review step", groups = "9. Cart Tests", dependsOnMethods = "verifyWebAutomationTabOpens", priority = 8)
    public void removeItemOnCartReview() {
        InventoryItem inventoryItem1 = new InventoryItem(Enums.DeviceType.PHONE.getDisplayName(), Enums.Brand.APPLE.getDisplayName(), "64GB", 1, "Gold", UserTestData.address, "standard", "none", "SAVE10");
        inventoryPage.addItemToCart(inventoryItem1);
        inventoryPage.validateBlankInventoryForm();

        inventoryPage.isCartVisible();

        inventoryPage.verifyNumberOfItemsInCart(1);


        InventoryItem inventoryItem2 = new InventoryItem(Enums.DeviceType.LAPTOP.getDisplayName(), "Macbook pro", "256GB", 1, "Gold", UserTestData.address, "express", "1yr", "SAVE10");

        inventoryPage.addItemAndRemoveExistingItemOnReviewStep(inventoryItem2, new ArrayList<>(List.of(inventoryItem1)));

        // Clean up
        inventoryPage.removeAllItemsInCart();
    }
    // </editor-fold>

}
