package com.tcrypto.services;

import com.tcrypto.dao.AccessTokenDao;
import com.tcrypto.models.AccessToken;
import com.tcrypto.models.RefreshToken;
import com.tcrypto.models.User;
import com.tcrypto.models.impl.TokenAble;
import com.tcrypto.services.impl.TokenHandleAble;
import com.tcrypto.utils.StringRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService implements TokenHandleAble {
    private final int TOKEN_LENGTH = 64;
    private final AccessTokenDao accessTokenDao;
    private final RefreshTokenService refreshTokenService;
    public static long TIME_TO_LIVE = 1800000; // 30 minute

    @Autowired
    public AccessTokenService(AccessTokenDao accessTokenDao, RefreshTokenService refreshTokenService) {
        this.accessTokenDao = accessTokenDao;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public TokenAble createToken() {
        String randomString = StringRandom.generate(TOKEN_LENGTH);
        RefreshToken refreshToken = (RefreshToken) refreshTokenService.createToken();
        AccessToken token = new AccessToken(randomString, refreshToken);
        refreshToken.setAccessToken(token);
        accessTokenDao.save(token);
        return token;
    }

    public void removeToken(User user) {
        AccessToken token = accessTokenDao.findAccessTokenByUserId(user.getId());
        user.setAccessToken(null);
        accessTokenDao.delete(token);
    }
    public void removeToken(AccessToken accessToken) {
        User user = accessToken.getUser();
        user.setAccessToken(null);
        accessTokenDao.delete(accessToken);
    }
}
