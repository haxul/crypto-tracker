package com.tcrypto.services;

import com.tcrypto.dao.UserDao;
import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.exceptions.IncorrectUserPhoneToRegister;
import com.tcrypto.exceptions.UserAlreadyExistsException;
import com.tcrypto.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserDao userDao;
    private final DaDataService daDataService;

    @Autowired
    public UserService(UserDao userDao, DaDataService daDataService) {
        this.daDataService = daDataService;
        this.userDao = userDao;
    }

    public User register(final UserSignupDto userSignupDto, final HttpServletRequest httpServletRequest) throws IOException {
        String phone = userSignupDto.getPhone();
        checkUserAlreadyExists(phone);
        checkPhoneValidity(phone);
        String name = userSignupDto.getName();
        String email = userSignupDto.getEmail();
        String password = userSignupDto.getPassword();
        String surname = userSignupDto.getSurname();
        String clientIp = getClientIp(httpServletRequest);
        String country = daDataService.defineCountry(clientIp);
        User user = new User(name, email, phone, password, surname, country);
        userDao.save(user);
        return user;
    }

    private void checkPhoneValidity(final String phone) {
        Pattern pattern = Pattern.compile("^\\+\\d{7,25}$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.find())throw new IncorrectUserPhoneToRegister("not valid phone number");
    }

    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) ipAddress = request.getRemoteAddr();
        return ipAddress;
    }

    private void checkUserAlreadyExists(final String phone) {
        boolean doesUserExist = userDao.findUserByPhone(phone) != null;
        if (doesUserExist) throw new UserAlreadyExistsException("the user is already registered");
    }
}
