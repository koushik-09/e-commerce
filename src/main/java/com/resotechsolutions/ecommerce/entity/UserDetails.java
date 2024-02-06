package com.resotechsolutions.ecommerce.entity;


import javax.persistence.*;


@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;

    @Transient
    private String token;
    @Column(name = "gender")
    private char gender;

    @Column(name = "phone_num")
    private long phno;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Column(name = "isUser")
    private boolean isUser;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public UserDetails(){

    }

    public UserDetails(String email, String name, char gender, long phno) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.phno = phno;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
    public void setUserDetails(UserHandler userHandler){
        this.email = userHandler.getEmail();
        this.name = userHandler.getName();
        this.gender = userHandler.getGender();
        this.phno = userHandler.getPhno();
        this.isActive = userHandler.isActive();
        this.isUser = userHandler.isUser();
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phno=" + phno +
                ", token=" + token +
                ", isActive=" + isActive +
                ", isAdmin=" + isAdmin +
                ", isUser=" + isUser +
                '}';
    }
}
