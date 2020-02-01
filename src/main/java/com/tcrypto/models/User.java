package com.tcrypto.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

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

    @Embedded
    private SocialNetworks socialNetworks;

    @OneToOne
    private AccessToken accessTokens;

    public User() {}

    public User(String name, String email, String phone, String password, String surname, String country) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.country = country;
    }

    public AccessToken getAccessTokens() {
        return accessTokens;
    }

    public void setAccessTokens(AccessToken accessTokens) {
        this.accessTokens = accessTokens;
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
