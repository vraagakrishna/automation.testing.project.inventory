package model;

import java.util.List;

public class Invoice {

    // <editor-fold desc="Class Fields">
    private final List<InventoryItem> inventoryItems;

    private final int numberOfItems;

    private double subTotalPrice = 0;

    private double shippingPrice = 0;

    private double warrantyPrice = 0;

    private double discountPrice = 0;

    private double totalPrice = 0;

    private String invoiceNumber = "";

    private String timeStamp = "";

    private String address = "";
    // </editor-fold>

    // <editor-fold desc="Ctor">
    public Invoice(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;

        this.numberOfItems = inventoryItems.size();

        this.address = inventoryItems.get(0).getAddress();

        this.calculateSubTotalPrice();
        this.calculateShippingPrice();
        this.calculateWarrantyPrice();
        this.calculateDiscountPrice();
        this.calculateTotalPrice();
    }
    // </editor-fold>

    // <editor-fold desc="Getters and Setters">
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public double getWarrantyPrice() {
        return warrantyPrice;
    }

    public String getDate() {
        return this.timeStamp.split("at")[0].trim();
    }

    public String getTime() {
        return this.timeStamp.split("at")[1].trim();
    }
    // </editor-fold>

    // <editor-fold desc="Calculating Price">
    private void calculateSubTotalPrice() {
        this.subTotalPrice = 0;

        for (InventoryItem inventoryItem : this.inventoryItems) {
            this.subTotalPrice += inventoryItem.getSubTotalPrice();
        }
    }

    private void calculateShippingPrice() {
        this.shippingPrice = 0;

        for (InventoryItem inventoryItem : this.inventoryItems) {
            this.shippingPrice += inventoryItem.getShippingPrice();
        }
    }

    private void calculateWarrantyPrice() {
        this.warrantyPrice = 0;

        for (InventoryItem inventoryItem : this.inventoryItems) {
            this.warrantyPrice += inventoryItem.getWarrantyPrice();
        }
    }

    private void calculateDiscountPrice() {
        this.discountPrice = 0;

        for (InventoryItem inventoryItem : this.inventoryItems) {
            this.discountPrice += inventoryItem.getDiscountPrice();
        }
    }

    private void calculateTotalPrice() {
        this.totalPrice = 0;

        for (InventoryItem inventoryItem : this.inventoryItems) {
            this.totalPrice += inventoryItem.getTotalPrice();
        }
    }
    // </editor-fold>

}
