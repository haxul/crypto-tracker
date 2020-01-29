package com.tcrypto.services;

import com.tcrypto.dao.UserDao;
import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(UserSignupDto userSignupDto) {
        String phone = userSignupDto.getPhone();
        String name = userSignupDto.getName();
        String email = userSignupDto.getEmail();
        String password = userSignupDto.getPassword();
        String surname = userSignupDto.getSurname();
        User user = new User(name, email, phone, password, surname);
        userDao.save(user);
        return user;
    }
}
