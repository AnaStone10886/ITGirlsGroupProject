package ru.itgirlsdiff1.app.model.entity;

public class Product {
    private static long idCount = 0L;
    private long id;
    private String productName;
    private double price;
    private int quantityInStock;

    public Product (String productName, double price, int quantityInStock){
        this.id = ++idCount;
        this.productName = productName;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public Product () {}

    public long getId() { return id; }
    public String getProductName (){ return productName; }
    public double getPrice (){ return price; }
    public int getQuantity (){ return quantityInStock; }

    public void setProductName(String newProductName) {
        this.productName = newProductName;
    }
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }
    public void setQuantityInStock (int newQuantity) {
        this.quantityInStock = newQuantity;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantityInStock +
                '}' + '\n';
    }
}
