package com.tcrypto.controllers;

import com.tcrypto.dao.AccessTokenDao;
import com.tcrypto.dao.UserDao;
import com.tcrypto.dto.request.AuthRequestDto;
import com.tcrypto.dto.response.AuthResponseDto;
import com.tcrypto.exceptions.UserIsNotFoundException;
import com.tcrypto.models.AccessToken;
import com.tcrypto.models.User;
import com.tcrypto.services.AccessTokenService;
import com.tcrypto.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authorize")
public class Authorization {

    private final UserService userService;
    private final UserDao userDao;
    private final AccessTokenService accessTokenService;
    private final AccessTokenDao accessTokenDao;

    @Autowired
    public Authorization(UserService userService, UserDao userDao, AccessTokenService accessTokenService, AccessTokenDao accessTokenDao) {
        this.userService = userService;
        this.userDao = userDao;
        this.accessTokenService = accessTokenService;
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
        accessTokenService.removeTokenByUser(user);
        AccessToken accessToken =(AccessToken) accessTokenService.createToken();
        accessToken.setUser(user);
        user.setAccessToken(accessToken);
        userDao.save(user);
        AuthResponseDto authResponseDto = new AuthResponseDto(user.getId(), accessToken.getToken(), accessToken.getRefreshToken().getToken());
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }
}
