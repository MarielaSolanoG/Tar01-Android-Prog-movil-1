package com.example.tooltracker;

public class Product {
    private int productId; // ID del producto
    private String name; // Nombre del producto
    private int quantity; // Cantidad disponible
    private float priceCost; // Precio de costo
    private float priceSale; // Precio de venta
    private int quantitySold; // Cantidad vendida

    // Constructor
    public Product(int productId, String name, int quantity, float priceCost, float priceSale, int quantitySold) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.priceCost = priceCost;
        this.priceSale = priceSale;
        this.quantitySold = quantitySold;
    }

    // Getters y setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(float priceCost) {
        this.priceCost = priceCost;
    }

    public float getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(float priceSale) {
        this.priceSale = priceSale;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String toString() {
        return "Name: " + name + ", Price Cost: $" + priceCost+ ", Price Sale: $" + priceSale+ ", Cantidad: $" + quantity+
                "Cantidad Vendido: " + quantitySold;
    }
}
