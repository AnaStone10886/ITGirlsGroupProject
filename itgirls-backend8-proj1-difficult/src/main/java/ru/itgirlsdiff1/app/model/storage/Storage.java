package ru.itgirlsdiff1.app.model.storage;

import ru.itgirlsdiff1.app.model.entity.Catalog;
import ru.itgirlsdiff1.app.model.entity.User;

import java.util.ArrayList;

public class Storage {
    private static Storage instance;
    private ArrayList<User> userList;
    private ArrayList<Catalog> catalogList;


    private Storage() {
        this.userList = new ArrayList<>();
        this.catalogList = new ArrayList<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ArrayList<Catalog> getCatalogList() {
        return catalogList;
    }
}
