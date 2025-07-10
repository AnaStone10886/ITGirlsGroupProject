package ru.itgirlsdiff1.app.model.service;

import ru.itgirlsdiff1.app.model.entity.Product;
import ru.itgirlsdiff1.app.model.entity.User;
import ru.itgirlsdiff1.app.model.storage.StorageManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private final StorageManager storageManager;

    public UserService(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    public void addUser(User user) {
        String nameRegex = "^[А-яа-я]{2,25} [А-яа-я]{2,25} [А-яа-я]{2,25}$";
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (user.getFullName() != null) {
            if (user.getFullName().matches(nameRegex)) {
                if (user.getEmail().matches(emailRegex)) {
                    storageManager.addUser(user);
                } else {
                    System.out.println("You input wrong email");
                }
            } else {
                System.out.println("You input wrong name");
            }
        } else {
            System.out.println("You can't add empty user");
        }


    }

    public List<User> readAllUsers() {
        return storageManager.readAllUsers();
    }

    public User readUser(Long userId) {
        return storageManager.readUser(userId);
    }

    public User readUser(String userName) {
        return storageManager.readUser(userName);
    }


    public void deleteUserById(long id) {
        if (id != 0) {
            storageManager.deleteUserById(id);
        } else {
            System.out.println("The id cannot be equal to 0");
        }
    }

    public void updateUser(long userId, String newName, String newEmail, String newBirthday, String newGender, String newRole) {
        try {

            User user = storageManager.getUser(userId);
            if (user != null) {
                if (!newName.equals(null)) {
                    boolean isValidName = checkUserName(newName);
                    if (isValidName) {
                        storageManager.updateUser(userId, newName, user.getEmail(), user.getBirthday(), user.getGender(), user.getRole());
                    } else {
                        System.out.println("Provided name is invalid.");
                    }
                }
                if (!newEmail.equals(null)) {
                    boolean isValidEmail = checkUserEmail(newEmail);
                    if (isValidEmail) {
                        storageManager.updateUser(userId, newName, user.getEmail(), user.getBirthday(), user.getGender(), user.getRole());
                    } else {
                        System.out.println("Provided email is invalid.");
                    }
                }
                if (!newBirthday.equals(null)) {
                    boolean isValidBirthday = checkUserBirthday(newBirthday);
                    if (isValidBirthday) {
                        storageManager.updateUser(userId, newName, user.getEmail(), user.getBirthday(), user.getGender(), user.getRole());
                    } else {
                        System.out.println("Provided birthday is invalid.");
                    }
                }
                if (!newGender.equals(null)) {
                    boolean isValidGender = checkUserGender(newGender);
                    if (isValidGender) {
                        storageManager.updateUser(userId, newName, user.getEmail(), user.getBirthday(), user.getGender(), user.getRole());
                    } else {
                        System.out.println("Provided gender is invalid.");
                    }
                }
                if (!newRole.equals(null)) {
                    boolean isValidRole = checkUserRole(newRole);
                    if (isValidRole) {
                        storageManager.updateUser(userId, newName, user.getEmail(), user.getBirthday(), user.getGender(), user.getRole());
                    } else {
                        System.out.println("Provided role is invalid.");
                    }
                }
                System.out.println("User information updated successfully.");
            } else {
                System.out.println("Invalid user Id.");
            }
        } catch (RuntimeException e) {
            System.out.println("Invalid user Id.");
        }
    }

    private boolean checkUserName(String newName) {
        Pattern pattern = Pattern.compile("^[A-ЯЁ][а-яё]{2,25}\\s[A-ЯЁ][а-яё]{2,25}\\s[A-ЯЁ][а-яё]{2,25}$");
        Matcher matcher = pattern.matcher(newName);
        return matcher.matches();
    }


    private boolean checkUserEmail(String newEmail) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
        Matcher matcher = pattern.matcher(newEmail);
        return matcher.matches();
    }

    private boolean checkUserBirthday(String newBirthday) {
        Pattern pattern = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$");
        Matcher matcher = pattern.matcher(newBirthday);
        return matcher.matches();
    }

    private boolean checkUserGender(String newGender) {
        return newGender.equals("женщина") || newGender.equals("мужчина") || newGender.equals("не подан");
    }

    private boolean checkUserRole(String newRole) {
        return newRole.equals("vendor") || newRole.equals("client");
    }

    public void addProductToBasket(User user, String catalogName, String productName, int quantity) {
        Product product = storageManager.getProductFromCatalog(catalogName, productName);
        if (product != null && quantity > 0) {
            storageManager.addProductToBasket(catalogName, productName, quantity);
            user.getBasket().addProduct(product, quantity);
            System.out.println(user.getFullName() + " put " + product.getProductName() + " from catalog "
                    + catalogName + " in quantity " + quantity
                    + " into basket. Total price of basket is: "
                    + user.getBasket().getTotalPrice());
        } else {
            System.out.println("The product does not exist or quantity of the product in stock is not enough.");
        }
    }

    public void showProductsInBasket(User user) {
        List<Product> basket = user.getBasket().getProducts();
        if (basket.isEmpty()) {
            System.out.println("The basket of " + user.getFullName() + " is empty.");
        } else {
            System.out.println("Product in basket for user " + user.getFullName() + ":");
            Map<Product, Integer> productsInBasketMap = new HashMap<>();
            for (Product product : basket) {
                productsInBasketMap.put(product, productsInBasketMap.getOrDefault(product, 0) + 1);
            }
            for (Map.Entry<Product, Integer> entry : productsInBasketMap.entrySet()) {
                System.out.println(entry.getKey() + " - quantity: " + entry.getValue());
            }
        }
    }

    public void showBasketTotalPrice(User user) {
        List<Product> basket = user.getBasket().getProducts();
        if (basket.isEmpty()) {
            System.out.println("The basket of " + user.getFullName() + " is empty.");
        } else {
            System.out.println("Basket price for " + user.getFullName() + ":" + user.getBasket().getTotalPrice());
        }
    }

}
