package ru.itgirlsdiff1.app.view;

import ru.itgirlsdiff1.app.model.entity.Catalog;
import ru.itgirlsdiff1.app.model.entity.Product;
import ru.itgirlsdiff1.app.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class View {

    public void writeAllUsers (List<User> usersList){
        System.out.println(usersList.toString());
    }

    public void printProductList(List<Product> productList){
        if (productList.isEmpty()) {
            System.out.println("No products to display.");
        } else {
            System.out.println("Product List:");
            for (Product product : productList) {
                System.out.println("Name: " + product.getProductName());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println();
            }
        }
    }
    public void printAllCatalogs (List <Catalog> catalogsList){
        System.out.println( catalogsList.toString());
    }

    public void printProdsByPriceAsc(List<Product> prodsAsc){
        System.out.println("Продукты сортированы по возрастанию цены: " + prodsAsc);
    }
    public void printProdsByPriceDesc(List<Product> prodsDesc){
        System.out.println("Продукты сортированы по убыванию цены: " + prodsDesc);
    }
}
