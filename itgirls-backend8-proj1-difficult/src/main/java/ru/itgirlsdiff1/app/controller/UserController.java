package ru.itgirlsdiff1.app.controller;

import ru.itgirlsdiff1.app.model.entity.User;
import ru.itgirlsdiff1.app.model.service.UserService;
import ru.itgirlsdiff1.app.view.View;

import java.util.List;

public class UserController {
    private UserService userService;
    private View userView;

    public UserController(UserService userService, View userView) {

        this.userService = userService;
        this.userView = userView;
    }

    public void addUser(User userAdmin, User user) {
        if (userAdmin.getRole().equalsIgnoreCase("admin")) {
            userService.addUser(user);
        } else {
            System.out.println("You are not admin. You can't add user");
        }
    }

    public List<User> readAllUsers(User userAdmin) {
        if (userAdmin.getRole().equalsIgnoreCase("admin")) {
            return userService.readAllUsers();
        } else {
            System.out.println("Only Admin is able to read users");
            return null;
        }
    }

    public User readUser(User userAdmin, Long userId) {
        if (userAdmin.getRole().equalsIgnoreCase("admin")) {
            return userService.readUser(userId);
        } else {
            System.out.println("Only Admin is able to read users");
            return null;
        }
    }

    public User readUser(User userAdmin, String userName) {
        if (userAdmin.getRole().equalsIgnoreCase("admin")) {
            return userService.readUser(userName);
        } else {
            System.out.println("Only Admin is able to read users");
            return null;
        }
    }

    public void deleteUserById(User userAdmin, long id) {
        if (userAdmin.getRole().equalsIgnoreCase("admin")) {
            userService.deleteUserById(id);
        } else {
            System.out.println("You are not admin. You can't delete user");
        }
    }

    public void updateUser(User user, User loggedInUser, String newName, String newEmail, String newBirthday,
                           String newGender, String newRole) {
        if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
            userService.updateUser(user.getUserId(), newName, newEmail, newBirthday, newGender, newRole);
        } else {
            System.out.println("Error! User " + loggedInUser.getFullName()
                    + " does not have permissions to update user information. ");
        }
    }

    public void getUsers() {
        userService.readAllUsers();
        userView.writeAllUsers(userService.readAllUsers());
    }

    public User createUser(String fullName, String email, String gender, String birthday, String role) {
        return new User(fullName, email, gender, birthday, role);
    }

    public void addProductToBasket(User user, String catalogName, String productName, int quantity) {
        userService.addProductToBasket(user, catalogName, productName, quantity);
    }

    public void showProductsInBasket(User user) {
        if (user != null) {
            userService.showProductsInBasket(user);
        } else {
            System.out.println("User does not exist.");
        }
    }

    public void showBasketTotalPrice(User user) {
        if (user != null) {
            userService.showBasketTotalPrice(user);
        } else {
            System.out.println("User does not exist.");
        }

    }
}