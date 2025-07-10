package ru.itgirlsdiff1.app.controller;

import ru.itgirlsdiff1.app.model.entity.Product;
import ru.itgirlsdiff1.app.model.entity.User;
import ru.itgirlsdiff1.app.model.service.ProductService;
import ru.itgirlsdiff1.app.view.View;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private final ProductService productService;
    private final View productView;

    public ProductController(ProductService productService, View productView) {
        this.productService = productService;
        this.productView = productView;
    }

    public void deleteProduct(User userAdmin, long id) {
        if (userAdmin.getRole().equalsIgnoreCase("admin") || userAdmin.getRole().equalsIgnoreCase("vendor")) {
            productService.deleteProduct(userAdmin, id);
        } else {
            System.out.println("You are not admin or vendor. You can't delete product");
        }
    }

    public void createProduct(User loggedInUser, String productName, double price, int quantityInStock, long catalogID) {
        if (loggedInUser.getRole().equalsIgnoreCase("admin") || loggedInUser.getRole().equalsIgnoreCase("vendor")) {
            productService.createProduct(productName, price, quantityInStock, catalogID);
        } else {
            System.out.println("Error! User " + loggedInUser.getFullName() + " does not have permissions to add products.");
        }
    }

    public ArrayList<Product> readAllProducts() {
        return productService.readAllProducts();
    }

    public Product readProduct(String productName) {
        return productService.readProduct(productName);
    }

    public ArrayList<Product> readProductListInCatalog(String catalogName) {
        return productService.readProductListInCatalog(catalogName);
    }

    public void updateProduct(User userAdmin, String catalogName, String oldProductName, String newProductName, double oldPrice, double newPrice, int oldQuantity, int newQuantity) {
        if (userAdmin.getRole().equalsIgnoreCase("admin") || userAdmin.getRole().equalsIgnoreCase("vendor")) {
            if (!oldProductName.equalsIgnoreCase(newProductName) && oldPrice != newPrice && oldQuantity != newQuantity) {
                productService.updateProduct(catalogName, oldProductName, newProductName, oldPrice, newPrice, oldQuantity, newQuantity);
            } else if (!oldProductName.equalsIgnoreCase(newProductName) && oldPrice != newPrice && oldQuantity == newQuantity) {
                productService.updateProduct(catalogName, oldProductName, newProductName, oldPrice, newPrice, oldQuantity, oldQuantity);
            } else if (!oldProductName.equalsIgnoreCase(newProductName) && oldPrice == newPrice && oldQuantity != newQuantity) {
                productService.updateProduct(catalogName, oldProductName, newProductName, oldPrice, oldPrice, oldQuantity, newQuantity);
            } else if (!oldProductName.equalsIgnoreCase(newProductName) && oldPrice == newPrice && oldQuantity == newQuantity) {
                productService.updateProduct(catalogName, oldProductName, newProductName, oldPrice, oldPrice, oldQuantity, oldQuantity);
            } else if (oldProductName.equalsIgnoreCase(newProductName) && oldPrice != newPrice && oldQuantity != newQuantity) {
                productService.updateProduct(catalogName, oldProductName, oldProductName, oldPrice, newPrice, oldQuantity, newQuantity);
            } else if (oldProductName.equalsIgnoreCase(newProductName) && oldPrice != newPrice && oldQuantity == newQuantity) {
                productService.updateProduct(catalogName, oldProductName, oldProductName, oldPrice, newPrice, oldQuantity, oldQuantity);
            } else if (oldProductName.equalsIgnoreCase(newProductName) && oldPrice == newPrice && oldQuantity != newQuantity) {
                productService.updateProduct(catalogName, oldProductName, oldProductName, oldPrice, oldPrice, oldQuantity, newQuantity);
            } else if (oldProductName.equalsIgnoreCase(newProductName) && oldPrice == newPrice && oldQuantity == newQuantity) {
                productService.updateProduct(catalogName, oldProductName, oldProductName, oldPrice, oldPrice, oldQuantity, oldQuantity);
            }
        } else {
            System.out.println("Only Admin or Vendor is able to update ProductName");
        }
    }

    public void addProductToBasket(User user, String catalogName, String productName, int quantity) {
        productService.addProductToBasket(user, catalogName, productName, quantity);
    }

    public Product createProduct(String productName, double price, int quantityInStock) {
        return new Product(productName, price, quantityInStock);
    }

    public List<Product> filterProdAscByPrice(String catalogName, double minPrice, double maxPrice) {
        return productService.filterProdAscByPrice(catalogName, minPrice, maxPrice);
    }

    public List<Product> filterProdDescByPrice(String catalogName, double minPrice, double maxPrice) {
        return productService.filterProdDescByPrice(catalogName, minPrice, maxPrice);
    }

    public void printAscFilteredPrice(List<Product> prodsAsc){
        productView.printProdsByPriceAsc(prodsAsc);
    }

    public void printDescFilteredPrice(List<Product> prodsDesc){
        productView.printProdsByPriceDesc(prodsDesc);
    }
}
