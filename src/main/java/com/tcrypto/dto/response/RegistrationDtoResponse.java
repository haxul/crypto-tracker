package com.tcrypto.dto.response;

public class RegistrationDtoResponse {
    private int userId;
    private String accessToken;
    private String refreshToken;

    public RegistrationDtoResponse() {}
    public RegistrationDtoResponse(int userId, String accessToken, String refreshToken ) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
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
