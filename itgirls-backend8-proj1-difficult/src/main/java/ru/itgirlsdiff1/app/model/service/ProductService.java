package ru.itgirlsdiff1.app.model.service;

import ru.itgirlsdiff1.app.model.entity.Catalog;
import ru.itgirlsdiff1.app.model.entity.Product;
import ru.itgirlsdiff1.app.model.entity.User;
import ru.itgirlsdiff1.app.model.storage.StorageManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductService {
    private final StorageManager storageManager;

    public ProductService(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    public void deleteProduct(User userAdmin, long id) {
        if (storageManager.checkUser(userAdmin)) {
            Iterator<Catalog> iteratorCatalog = storageManager.getCatalogList().iterator();
            while (iteratorCatalog.hasNext()) {
                Catalog catalog = iteratorCatalog.next();
                Iterator<Product> iteratorProduct = catalog.getProductsList().iterator();
                while (iteratorProduct.hasNext()) {
                    Product productDelete = iteratorProduct.next();
                    if (productDelete.getId() == id) {
                        iteratorProduct.remove();
                        System.out.println("Product " + productDelete.getProductName() + " with id" + productDelete.getId() + " has been deleted");
                        return;
                    }
                }
            }
            System.out.println("The product with the id is not in the list ");
        } else {
            System.out.println("You cannot delete a catalog. User with id " + userAdmin.getUserId() + " is not in the list of users");
        }
    }

    public void createProduct(String productName, double price, int quantityInStock, long catalogID) {
        if (!productName.equals(null) && price > 0 && quantityInStock >= 0) {
            storageManager.createProduct(productName, price, quantityInStock, catalogID);
        } else {
            System.out.println("Error! Invalid product data.");
        }
    }

    public ArrayList<Product> readAllProducts() {
        return storageManager.readAllProducts();
    }

    public Product readProduct(String productName) {
        return storageManager.readProduct(productName);
    }

    public ArrayList<Product> readProductListInCatalog(String catalogName) {
        return storageManager.readProductListInCatalog(catalogName);
    }

    public void updateProduct(String catalogName, String oldProductName, String newProductName, double oldPrice, double newPrice, int oldQuantity, int newQuantity) {
        storageManager.updateProduct(catalogName, oldProductName, newProductName, oldPrice, newPrice, oldQuantity, newQuantity);
    }

    public void addProductToBasket(User user, String catalogName, String productName, int quantity) {
        Product product = storageManager.getProductFromCatalog(catalogName, productName);
        if (product != null && quantity > 0) {
            storageManager.addProductToBasket(catalogName, productName, quantity);
            user.getBasket().addProduct(product, quantity);
            System.out.println(user.getFullName() + " put " + product.getProductName()  + " in quantity " + quantity
                    + " into basket. Total price of basket is: " + user.getBasket().getTotalPrice());
        } else {
            System.out.println("The product does not exist or quantity of the product in stock is not enough.");
        }
    }

    public List<Product> filterProdAscByPrice(String catalogName, double minPrice, double maxPrice){
        return storageManager.filterProdAscByPrice(catalogName, minPrice, maxPrice);
    }

    public List<Product>filterProdDescByPrice(String catalogName, double minPrice, double maxPrice){
        return storageManager.filterProdDescByName(catalogName, minPrice, maxPrice);
    }
}
