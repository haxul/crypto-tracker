package com.tcrypto.dto.response;

import javax.validation.constraints.NotNull;

public class AuthResponseDto {
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;

    @NotNull
    private int userId;

    public AuthResponseDto() { }
    public AuthResponseDto(int userId, String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
