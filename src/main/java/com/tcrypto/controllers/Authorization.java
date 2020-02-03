package com.tcrypto.controllers;

import com.tcrypto.dao.AccessTokenDao;
import com.tcrypto.dao.RefreshTokenDao;
import com.tcrypto.dao.UserDao;
import com.tcrypto.dto.request.AuthRequestDto;
import com.tcrypto.dto.response.AuthResponseDto;
import com.tcrypto.exceptions.AccessTokenIsNotFoundException;
import com.tcrypto.exceptions.RefreshTokenUnprocessableEntity;
import com.tcrypto.exceptions.UserIsNotFoundException;
import com.tcrypto.models.AccessToken;
import com.tcrypto.models.RefreshToken;
import com.tcrypto.models.User;
import com.tcrypto.services.AccessTokenService;
import com.tcrypto.services.RefreshTokenService;
import com.tcrypto.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/authorization")
public class Authorization {

    private final UserDao userDao;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenDao refreshTokenDao;
    private final AccessTokenDao accessTokenDao;


    @Autowired
    public Authorization(UserDao userDao,
                         AccessTokenService accessTokenService,
                         RefreshTokenDao refreshTokenDao,
                         AccessTokenDao accessTokenDao) {
        this.userDao = userDao;
        this.accessTokenService = accessTokenService;
        this.refreshTokenDao = refreshTokenDao;
        this.accessTokenDao = accessTokenDao;
    }

    @PostMapping
    public ResponseEntity<AuthResponseDto> authorize(@Valid @RequestBody final AuthRequestDto authRequestDto) {
        String phone = authRequestDto.getPhone();
        User user = userDao.findUserByPhone(phone);
        if (user == null) throw new UserIsNotFoundException();
        String dynamicSalt = user.getDynamicSalt();
        String inputHashedPassword = DigestUtils.sha256Hex(authRequestDto.getPassword() + UserService.STATIC_SALT + dynamicSalt);
        String hashedPassword = user.getPassword();
        if (!inputHashedPassword.equals(hashedPassword)) throw new UserIsNotFoundException();
        if (user.getAccessToken() != null) accessTokenService.removeToken(user);
        AccessToken accessToken =(AccessToken) accessTokenService.createToken();
        accessToken.setUser(user);
        user.setAccessToken(accessToken);
        userDao.save(user);
        AuthResponseDto authResponseDto = new AuthResponseDto(user.getId(), accessToken.getToken(), accessToken.getRefreshToken().getToken());
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @GetMapping("/refreshToken/{refreshTokenValue}/update")
    public ResponseEntity<String> updateAccessToken(@PathVariable String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokenDao.findRefreshTokenByToken(refreshTokenValue);
        if (refreshToken == null) throw new RefreshTokenUnprocessableEntity();
        long createDate = refreshToken.getCreated().getTime();
        long difference = new Date().getTime() - createDate;
        AccessToken accessToken = refreshToken.getAccessToken();
        if (difference > RefreshTokenService.TIME_TO_LIVE) {
            accessTokenService.removeToken(accessToken);
            throw new RefreshTokenUnprocessableEntity();
        }
        accessToken.setCreated(new Date());
        accessTokenDao.save(accessToken);
        String message = accessToken.getToken() + " is updated";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/accessToken/{accessTokenValue}/revoke")
    public ResponseEntity<String> revokeAccessToken(@PathVariable String accessTokenValue) {
        AccessToken accessToken= accessTokenDao.findAccessTokenByToken(accessTokenValue);
        if (accessToken == null) throw new AccessTokenIsNotFoundException();
        accessTokenService.removeToken(accessToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
