package com.tcrypto.controllers;

import com.tcrypto.dao.UserDao;
import com.tcrypto.dto.request.AuthRequestDto;
import com.tcrypto.dto.response.AuthResponseDto;
import com.tcrypto.exceptions.UserIsNotFoundException;
import com.tcrypto.models.User;
import com.tcrypto.services.UserService;
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

    @Autowired
    public Authorization(UserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    @PostMapping
    public ResponseEntity<AuthResponseDto> authorize(@Valid @RequestBody AuthRequestDto authRequestDto) {
        String phone = authRequestDto.getPhone();
        User user = userDao.findUserByPhone(phone);
        if (user == null) throw new UserIsNotFoundException();
        String password = authRequestDto.getPassword();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
