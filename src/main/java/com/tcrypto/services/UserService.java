package com.tcrypto.services;

import com.tcrypto.dao.AccessTokenDao;
import com.tcrypto.dao.UserDao;
import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.dto.response.AuthResponseDto;
import com.tcrypto.exceptions.IncorrectUserPhoneToRegister;
import com.tcrypto.exceptions.UnAuthorizedException;
import com.tcrypto.exceptions.UserAlreadyExistsException;
import com.tcrypto.models.AccessToken;
import com.tcrypto.models.User;
import com.tcrypto.utils.StringRandom;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    public static String STATIC_SALT = "pOpr4MIucrTEVIFkJQLbwXbudrB1Hbed43c4RaLe6Y0cgN6aNggK9xBejldUcArQlGqMnxil1CLyusIK";
    private static int DYNAMIC_SALT_SIZE = 45;
    private final UserDao userDao;
    private final DaDataService daDataService;
    private final AccessTokenService accessTokenService;
    private final AccessTokenDao accessTokenDao;

    @Autowired
    public UserService(
            UserDao userDao,
            DaDataService daDataService,
            AccessTokenService accessTokenService,
            AccessTokenDao accessTokenDao) {
        this.daDataService = daDataService;
        this.userDao = userDao;
        this.accessTokenService = accessTokenService;
        this.accessTokenDao = accessTokenDao;
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
        String dynamicSalt = StringRandom.generate(DYNAMIC_SALT_SIZE);
        String hashedPassword = DigestUtils.sha256Hex(password + STATIC_SALT + dynamicSalt);
        String country = daDataService.defineCountry(clientIp);
        AccessToken token = (AccessToken) accessTokenService.createToken();
        User user = new User(name, email, phone, hashedPassword, dynamicSalt, surname, country, token);
        token.setUser(user);
        userDao.save(user);
        return user;
    }

    public void checkPhoneValidity(final String phone) {
        Pattern pattern = Pattern.compile("^\\+\\d{7,25}$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.find()) throw new IncorrectUserPhoneToRegister("not valid phone number");
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

    public void checkAccessToken(HttpServletRequest request) {
        String headerValue = request.getHeader("Authorization");
        if (headerValue == null) throw new UnAuthorizedException();
        AccessToken accessToken = accessTokenDao.findAccessTokenByToken(headerValue);
        if (accessToken == null) throw new UnAuthorizedException();
        long createDate = accessToken.getCreated().getTime();
        long difference = new Date().getTime() - createDate;
        if (difference > AccessTokenService.TIME_TO_LIVE) throw new UnAuthorizedException();
    }
}
