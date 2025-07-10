package ru.itgirlsdiff1.app.controller;

import ru.itgirlsdiff1.app.model.entity.Catalog;
import ru.itgirlsdiff1.app.model.entity.Product;
import ru.itgirlsdiff1.app.model.entity.User;
import ru.itgirlsdiff1.app.model.service.CatalogService;
import ru.itgirlsdiff1.app.view.View;

import java.util.List;

public class CatalogController {
    private final CatalogService catalogService;
    private final View catalogView;

    public CatalogController(CatalogService catalogService, View catalogView) {
        this.catalogService = catalogService;
        this.catalogView = catalogView;
    }

    public List<Catalog> getCatalogList() {
        return catalogService.getCatalogList();
    }

    public void addCatalog(User userAdmin, Catalog catalog) {
        if (userAdmin.getRole().equalsIgnoreCase("admin")) {
            catalogService.addCatalog(catalog);
        } else {
            System.out.println("Only Admin is able to add Catalogs");
        }
    }

    public void deleteCatalog(User userAdmin, long id) {
        if (userAdmin.getRole().equalsIgnoreCase("admin") || userAdmin.getRole().equalsIgnoreCase("vendor")) {
            catalogService.deleteCatalog(userAdmin, id);
        } else {
            System.out.println("You are not admin or vendor. You can't delete catalog");
        }
    }

    public void readCatalog(long catalogID) {
        catalogService.readCatalog(catalogID);
    }

    public void updateCatalogName(User userAdmin, String oldCatalogName, String newCatalogName) {
        if (userAdmin.getRole().equalsIgnoreCase("admin")) {
            catalogService.updateCatalogName(oldCatalogName, newCatalogName);
        } else {
            System.out.println("Only Admin is able to add Catalogs");
        }
    }

    public void printCatalogs (){
        if (catalogService.getCatalogList().isEmpty()){
            System.out.println("No catalogs to display");
        }
        else {
            catalogView.printAllCatalogs(catalogService.getCatalogList());
        }
    }
    public void addProductToCatalog(Product product, User loggedInUser, long catalogID) {
        if (loggedInUser.getRole().equalsIgnoreCase("admin")||loggedInUser.getRole().equalsIgnoreCase("vendor")) {
            catalogService.addProductToCatalog(product, catalogID);
        } else {
            System.out.println("Error! User " + loggedInUser.getFullName()
                    + " does not have permissions to add products.");
        }
    }

    public Catalog createCatalog (String catalogName){
        return  new Catalog(catalogName);
    }
}
