package com.resotechsolutions.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {


    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetails userDetails;

    public User() {
    }

    public User(String email,String password){
        this.email = email;
        this.password =password;
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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void setUser(UserHandler userHandler){
        this.email = userHandler.getEmail();
        this.password = userHandler.getPassword();
    }
    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }

}
