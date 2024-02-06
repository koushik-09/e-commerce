package com.resotechsolutions.ecommerce.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "token")
    private String token;

    @Column(name = "added_on")
    private Timestamp added_on;

    @Column(name = "updated_on")
    private Timestamp updated_on;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Token() {
    }

    public Token(long UserId, String token, Timestamp added_on, Timestamp updated_on) {
        this.userId = UserId;
        this.token = token;
        this.added_on = added_on;
        this.updated_on = updated_on;
    }

    public long getId() {
        return userId;
    }

    public void setId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Timestamp updated_on) {
        this.updated_on = updated_on;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setToken(UserHandler userHandler){
        this.token = userHandler.getToken();
        this.added_on = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Token{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", added_on=" + added_on +
                ", updated_on=" + updated_on +
                ", user=" + user +
                '}';
    }
}
