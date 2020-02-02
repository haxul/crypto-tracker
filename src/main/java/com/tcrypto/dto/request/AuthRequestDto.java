package com.tcrypto.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AuthRequestDto {
    @NotNull
    @Pattern(regexp = "^\\+\\d{7,25}$")
    private String phone;
    @NotNull
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
