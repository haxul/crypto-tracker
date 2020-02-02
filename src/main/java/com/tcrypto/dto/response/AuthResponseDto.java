package com.tcrypto.dto.response;

import javax.validation.constraints.NotNull;

public class AuthResponseDto {
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
