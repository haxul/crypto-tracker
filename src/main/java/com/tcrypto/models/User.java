package com.tcrypto.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @Length(max = 120, message = "max 120 chars")
    private  String name;

    @Column
    @Length(max = 120, message = "max 120 chars")
    private String surname;

    @Column
    @Length(max = 120, message = "max 120 chars")
    private String country;

    @Column
    @Pattern(regexp = "/^\\d{5,25}$/")
    @NotNull
    private String phone;

    @Column
    @Email
    @NotNull
    private String email;

    @Column
    @NotNull
    private String password;
}
