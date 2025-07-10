package ru.itgirlsdiff1.app.model.storage.util;

import ru.itgirlsdiff1.app.model.entity.User;
import ru.itgirlsdiff1.app.model.storage.StorageManager;

import java.util.*;

public class HashMapFromArrayListUsers {
    private int passportNumber() {
        Set <Integer> numbersStorage = new HashSet<>();
        Random rand = new Random();

        int number;
        do {
            number = Math.abs(rand.nextInt(1000000));
        } while (!numbersStorage.add(number));
        return number;
    }

    public LinkedHashMap<Integer, User> convertArrayListToHashMap(StorageManager storageManager, LinkedHashMap<Integer, User> linkedHashMap)
    {
        ArrayList <User> userList = storageManager.readAllUsers();

        for (User user : userList) {
            linkedHashMap.put(passportNumber(), user);
        }
        return linkedHashMap;
    }
}
