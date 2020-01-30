package com.tcrypto.services;

import com.tcrypto.dao.UserDao;
import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.exceptions.IncorrectUserPhoneToRegister;
import com.tcrypto.exceptions.UserAlreadyExistsException;
import com.tcrypto.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User register(final UserSignupDto userSignupDto) {
        String phone = userSignupDto.getPhone();
        boolean doesUserExist = userDao.findUserByPhone(phone) != null;
        if (doesUserExist) throw new UserAlreadyExistsException("the user is already registered");
        checkPhoneValidity(phone);
        String name = userSignupDto.getName();
        String email = userSignupDto.getEmail();
        String password = userSignupDto.getPassword();
        String surname = userSignupDto.getSurname();
        User user = new User(name, email, phone, password, surname);
        userDao.save(user);
        return user;
    }

    public void checkPhoneValidity(final String phone) {
        Pattern pattern = Pattern.compile("^\\+\\d{7,25}$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.find())throw new IncorrectUserPhoneToRegister("not valid phone number");
    }

    public void defineCountry() {}

    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) ipAddress = request.getRemoteAddr();
        return ipAddress;
    }
}
