package ru.itgirlsdiff1.app.model.entity;

import java.util.ArrayList;

public class Catalog {
    private static long idCounter = 1L;
    private long catalogID;
    private String catalogName;
    private ArrayList<Product> productsList;

    public Catalog() {
    }

    public Catalog(String catalogName) {
        this.catalogID = idCounter++;
        this.catalogName = catalogName;
        productsList = new ArrayList<>();
    }

    public long getCatalogID() {
        return catalogID;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String newCatalogName){
        this.catalogName = newCatalogName;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    public String toString() {
        return "Catalog: id: " + catalogID + ", name: " + catalogName + '\n'
                + ", products in catalog: " + productsList + '\n';
    }
}
