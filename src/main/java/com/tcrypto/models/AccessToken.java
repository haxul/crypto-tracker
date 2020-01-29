package com.tcrypto.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;

    @Column
    @Length(max = 32)
    private String token;

    @OneToOne
    private User user;

    @OneToOne
    private RefreshToken refreshTokens;

    public RefreshToken getRefreshTokens() {
        return refreshTokens;
    }

    public void setRefreshTokens(RefreshToken refreshTokens) {
        this.refreshTokens = refreshTokens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
