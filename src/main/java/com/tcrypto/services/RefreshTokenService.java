package com.tcrypto.services;

import com.tcrypto.dao.RefreshTokenDao;
import com.tcrypto.models.RefreshToken;
import com.tcrypto.models.impl.TokenAble;
import com.tcrypto.services.impl.TokenHandleAble;
import com.tcrypto.utils.StringRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RefreshTokenService implements TokenHandleAble {

    private final int REFRESH_TOKEN_SIZE = 128;
    private final RefreshTokenDao refreshTokenDao;
    public static long TIME_TO_LIVE = 7776000000L; // 3 month

    public RefreshTokenService(RefreshTokenDao refreshTokenDao) {
        this.refreshTokenDao = refreshTokenDao;
    }

    @Override
    public TokenAble createToken() {
        String randomString = StringRandom.generate(REFRESH_TOKEN_SIZE);
        RefreshToken token = new RefreshToken(randomString);
        refreshTokenDao.save(token);
        return token;
    }
}
