package ru.itgirlsdiff1.app.model.entity;

import java.util.Objects;

public class User {
    private static long generator = 0L;

    private long userId;
    private String fullName;
    private String email;
    private String gender;
    private String birthday;
    private String role;
    private Basket basket;

    public User(String fullName, String email, String gender, String birthday, String role) {
        this.userId = ++generator;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.role = role;
        this.basket = new Basket();
    }

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Basket getBasket(){
        return basket;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", role='" + role + '\'' +
                '}'+ '\n';
    }
}
