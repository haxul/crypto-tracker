package com.tcrypto.models;

import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @Length(max = 120, message = "max 120 chars")
    private String name;

    @Column
    @Length(max = 120, message = "max 120 chars")
    private String surname;

    @Column
    @Length(max = 120, message = "max 120 chars")
    private String country;

    @Column
    @NotNull
    private String phone;

    @Column
    @Email
    @NotNull
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String dynamicSalt;

    @Embedded
    private SocialNetworks socialNetworks;

    @OneToOne
    private AccessToken accessToken;

    @ManyToMany
    private Set<Coin> coins;

    public User() {}

    public User(String name, String email, String phone, String password, String dynamicSalt, String surname, String country, AccessToken token) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.country = country;
        this.accessToken = token;
        this.dynamicSalt = dynamicSalt;
    }

    public Set<Coin> getCoins() {
        return coins;
    }

    public void setCoins(Set<Coin> coins) {
        this.coins = coins;
    }

    public String getDynamicSalt() {
        return dynamicSalt;
    }

    public void setDynamicSalt(String dynamicSalt) {
        this.dynamicSalt = dynamicSalt;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public SocialNetworks getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(SocialNetworks socialNetworks) {
        this.socialNetworks = socialNetworks;
    }
}
