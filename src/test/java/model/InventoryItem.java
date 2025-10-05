package model;

import java.util.Set;

public class InventoryItem {

    // <editor-fold desc="Class Fields">
    private final String storage;

    private String deviceType;

    private String brand = "Select device type first";

    private String color = "Black";

    private String address = "";

    private int quantity = 1;

    private String shippingMethod = "";

    private String warranty = "";

    private String discountCode = "";

    // <editor-fold desc="Calculated Values">
    private int devicePrice;

    private int storagePrice;

    private int unitPrice;

    private int subTotalPrice;

    private int shippingPrice;

    private int warrantyPrice;

    private int discountPercentage;

    private int unDiscountPrice;

    private double discountPrice;

    private double totalPrice;

    private String discountPercentageAsString;
    // </editor-fold>
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public InventoryItem(String deviceType, String brand, String storage, int quantity, String color, String address, String shippingMethod, String warranty, String discountCode) {
        this.validateInputData(shippingMethod, warranty);

        this.deviceType = deviceType;
        this.brand = brand;
        this.storage = storage;
        this.quantity = quantity;
        this.color = color;
        this.address = address;
        this.shippingMethod = shippingMethod;
        this.warranty = warranty;
        this.discountCode = discountCode;

        this.calculatePrices();
    }

    public InventoryItem(String deviceType, String brand, String storage, int quantity, String color, String address) {
        this.deviceType = deviceType;
        this.brand = brand;
        this.storage = storage;
        this.quantity = quantity;
        this.color = color;
        this.address = address;

        this.calculatePrices();
    }
    // </editor-fold>

    // <editor-fold desc="Getters and Setters">
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        this.calculatePrices();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStorage() {
        return storage;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getColor() {
        return color;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
        this.calculatePrices();
    }

    public int getDevicePrice() {
        return devicePrice;
    }

    public int getStoragePrice() {
        return storagePrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getSubTotalPrice() {
        return subTotalPrice;
    }

    public int getShippingPrice() {
        return shippingPrice;
    }

    public int getWarrantyPrice() {
        return warrantyPrice;
    }

    public int getUnDiscountPrice() {
        return unDiscountPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getDiscountPercentageAsString() {
        return discountPercentageAsString;
    }
    // </editor-fold>

    // <editor-fold desc="Validate Input Data">
    private void validateInputData(String selectedShippingMethod, String selectedWarranty) {
        Set<String> allowedShippingMethods = Set.of("standard", "express");
        Set<String> allowedWarranties = Set.of("none", "1yr", "2yr");

        if (!allowedShippingMethods.contains(selectedShippingMethod))
            throw new IllegalArgumentException("Invalid shipping method: " + selectedShippingMethod);

        if (!allowedWarranties.contains(selectedWarranty))
            throw new IllegalArgumentException("Invalid warranty: " + selectedShippingMethod);
    }
    // </editor-fold>

    // <editor-fold desc="Calculate Price">
    private void calculatePrices() {
        this.devicePrice = this.getPriceForDevice();
        this.storagePrice = this.getPriceForStorage();

        this.unitPrice = this.calculatePricePerUnit();
        this.subTotalPrice = this.calculateSubTotal();
        this.shippingPrice = this.getPriceForShippingMethod();
        this.warrantyPrice = this.getPriceForWarranty();
        this.discountPercentage = this.getDiscountPercentage();
        this.discountPercentageAsString = this.getStringDiscountPercentage();
        this.unDiscountPrice = this.calculateUnDiscountedPrice();
        this.discountPrice = this.calculateDiscountPrice();
        this.totalPrice = this.calculateTotalPrice();
    }

    private int calculatePricePerUnit() {
        return this.getPriceForDevice() + this.getPriceForStorage();
    }

    private int calculateSubTotal() {
        return this.unitPrice * quantity;
    }

    private int calculateUnDiscountedPrice() {
        return this.subTotalPrice + this.warrantyPrice + this.shippingPrice;
    }

    private double calculateDiscountPrice() {
        if (this.discountPercentage == 0)
            return 0;

        return this.unDiscountPrice * (this.discountPercentage / 100.0);
    }

    private double calculateTotalPrice() {
        // Note: the discountPrice is already negative
        return this.unDiscountPrice + this.discountPrice;
    }
    // </editor-fold>

    // <editor-fold desc="Master Data For Price">
    private int getPriceForDevice() {
        int price;
        switch (this.deviceType) {
            case "Phone":
                price = 400;
                break;
            case "Tablet":
                price = 600;
                break;
            case "Laptop":
                price = 1200;
                break;
            default:
                price = 0;
        }
        return price;
    }

    private int getPriceForStorage() {
        int price;
        switch (this.storage) {
            case "64GB":
                price = 0;
                break;
            case "128GB":
                price = 80;
                break;
            case "256GB":
                price = 160;
                break;
            default:
                price = 0;
        }
        return price;
    }

    private int getPriceForShippingMethod() {
        int price;
        switch (this.shippingMethod) {
            case "standard":
                price = 0;
                break;
            case "express":
                price = 25;
                break;
            default:
                price = 0;
        }
        return price;
    }

    private int getPriceForWarranty() {
        int price;
        switch (this.warranty) {
            case "none":
                price = 0;
                break;
            case "1yr":
                price = 49;
                break;
            case "2yr":
                price = 89;
                break;
            default:
                price = 0;
        }
        return price;
    }

    private int getDiscountPercentage() {
        int discountAmount;
        switch (this.discountCode) {
            case "":
                discountAmount = 0;
                break;
            case "SAVE10":
                discountAmount = -10;
                break;
            case "SAVE20":
                discountAmount = -20;
                break;
            default:
                discountAmount = 0;
        }
        return discountAmount;
    }

    private String getStringDiscountPercentage() {
        String discountPercentageString;
        switch (this.discountCode) {
            case "":
                discountPercentageString = "";
                break;
            case "SAVE10":
                discountPercentageString = "10%";
                break;
            case "SAVE20":
                discountPercentageString = "20%";
                break;
            default:
                discountPercentageString = "";
        }
        return discountPercentageString;
    }
    // </editor-fold>

    // <editor-fold desc="Additional Values">
    public String getItemDescription() {
        return String.format(
                "%s x %s %s %s (%s)",
                this.quantity, this.brand.toLowerCase(), this.deviceType.toLowerCase(),
                this.storage, this.color.toLowerCase()
        );
    }

    public void reduceQuantity() {
        this.quantity -= 1;

        // trigger calculation again
        this.calculatePrices();
    }

    public void increaseQuantity() {
        this.quantity += 1;

        // trigger calculation again
        this.calculatePrices();
    }

    public String orderDetailsForPlaceOrderPurchaseToast() {
        return String.format(
                "%sx %s %s %s",
                this.quantity, this.brand.toLowerCase(), this.deviceType.toLowerCase(),
                this.storage
        );
    }

    public String orderDetailsForPlacePurchaseToast() {
        return String.format(
                "%s x %s, %s, %s, %s, %s ship, %s",
                this.quantity, this.brand, this.deviceType.toLowerCase(),
                this.storage, this.color.toLowerCase(), this.shippingMethod, this.getWarrantyOnPurchaseToast()
        );
    }

    public String getWarrantyOnPurchaseToast() {
        String warrantyString;
        switch (this.warranty) {
            case "no":
                warrantyString = "no warranty";
                break;
            case "1yr":
                warrantyString = "1yr warranty";
                break;
            case "2yr":
                warrantyString = "2yr warranty";
                break;
            default:
                warrantyString = "no warranty";
        }
        return warrantyString;
    }
    // </editor-fold>

}
