package model;

import java.util.Set;

public class InventoryItem {

    // <editor-fold desc="Class Fields">
    private final String deviceType;
    private final String storage;
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

    public String getBrand() {
        return brand;
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
        return switch (this.deviceType) {
            case "Phone" -> 400;
            case "Tablet" -> 600;
            case "Laptop" -> 1200;
            default -> 0;
        };
    }

    private int getPriceForStorage() {
        return switch (this.storage) {
            case "64GB" -> 0;
            case "128GB" -> 80;
            case "256GB" -> 160;
            default -> 0;
        };
    }

    private int getPriceForShippingMethod() {
        return switch (this.shippingMethod) {
            case "standard" -> 0;
            case "express" -> 25;
            default -> 0;
        };
    }

    private int getPriceForWarranty() {
        return switch (this.warranty) {
            case "none" -> 0;
            case "1yr" -> 49;
            case "2yr" -> 89;
            default -> 0;
        };
    }

    private int getDiscountPercentage() {
        return switch (this.discountCode) {
            case "" -> 0;
            case "SAVE10" -> -10;
            case "SAVE20" -> -20;
            default -> 0;
        };
    }

    private String getStringDiscountPercentage() {
        return switch (this.discountCode) {
            case "" -> "";
            case "SAVE10" -> "10%";
            case "SAVE20" -> "20%";
            default -> "";
        };
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
        return switch (this.warranty) {
            case "no" -> "no warranty";
            case "1yr" -> "1yr warranty";
            case "2yr" -> "2yr warranty";
            default -> "no warranty";
        };
    }
    // </editor-fold>

}
