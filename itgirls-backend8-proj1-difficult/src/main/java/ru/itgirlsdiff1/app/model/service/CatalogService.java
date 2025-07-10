package ru.itgirlsdiff1.app.model.service;

import ru.itgirlsdiff1.app.model.entity.Catalog;
import ru.itgirlsdiff1.app.model.entity.Product;
import ru.itgirlsdiff1.app.model.entity.User;
import ru.itgirlsdiff1.app.model.storage.StorageManager;

import java.util.List;

public class CatalogService {
    private final StorageManager storageManager;

    public CatalogService(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    public void addCatalog(Catalog catalog) {
        storageManager.addCatalog(catalog);
    }


    public void deleteCatalog(User userAdmin, long id) {
        if (id != 0) {
            if (storageManager.checkUser(userAdmin)) {
                storageManager.deleteCatalog(id);
            } else {
                System.out.println("You cannot delete a catalog. . User with id " + userAdmin.getUserId() + " is not in the list of users");
            }
        } else {
            System.out.println("Id cannot be equal to 0");
        }
    }


    public List<Catalog> getCatalogList() {
        return storageManager.getCatalogList();
    }

    public void updateCatalogName(String oldCatalogName, String newCatalogName) {
        storageManager.updateCatalogName(oldCatalogName, newCatalogName);
    }

    public void readCatalog(long catalogID) {
        try {
            Catalog catalog = storageManager.getCatalog(catalogID);
            System.out.println("Catalog ID: " + catalog.getCatalogID());
            System.out.println("Name: " + catalog.getCatalogName());
            System.out.println("Products:");
            catalog.getProductsList().forEach(product -> {
                System.out.println("\t" + product.getId() + ": " + product.getProductName() + " - " + product.getPrice());
            });
        } catch (RuntimeException e) {
            System.out.println("Error! Catalog with ID " + catalogID + " not found.");
        }
    }

    public void addProductToCatalog(Product product, long catalogID) {
        Catalog catalog = storageManager.getCatalog(catalogID);
        if (product != null && catalog != null) {
            if (catalog.getProductsList().stream().anyMatch(p -> p.getProductName().equalsIgnoreCase(product.getProductName()))) {
                System.out.println("Error! Product with name " + product.getProductName() + " already exists in catalog.");
            } else {
                storageManager.addProductToCatalog(product, catalogID);
                System.out.println("Product " + product.getProductName() + " added successfully to catalog " + catalog.getCatalogName() + ".");
            }
        } else {
            System.out.println("Error! Catalog or product are not found.");
        }
    }
}

