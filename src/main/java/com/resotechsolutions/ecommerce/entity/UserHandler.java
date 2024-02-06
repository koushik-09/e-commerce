package com.resotechsolutions.ecommerce.entity;

import org.springframework.stereotype.Component;

@Component
public class UserHandler {


    private String email;

    private String password;

    private String name;

    private char gender;

    private long phno;

    private boolean isActive;

    private boolean isAdmin;

    private boolean isUser;

    private String token;

    public UserHandler(){

    }

    public UserHandler(String email, String password, String name, char gender, long phno) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phno = phno;
        this.isActive = true;
        this.isUser = true;
        this.isAdmin=false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public long getPhno() {
        return phno;
    }

    public void setPhno(long phno) {
        this.phno = phno;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserHandler{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phno=" + phno +
                ", isActive=" + isActive +
                ", isAdmin=" + isAdmin +
                ", isUser=" + isUser +
                ", token='" + token + '\'' +
                '}';
    }
}
