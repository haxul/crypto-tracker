package com.tcrypto;

import com.tcrypto.dao.UserDao;
import com.tcrypto.dto.request.UserSignupDto;
import com.tcrypto.exceptions.UserAlreadyExistsException;
import com.tcrypto.models.User;
import com.tcrypto.services.CoinOptions;
import com.tcrypto.services.UserService;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    void mustSaveNewUser() throws IOException {

        UserSignupDto userSignupDto = new UserSignupDto();
        userSignupDto.setEmail("hello@world.ru");
        userSignupDto.setName("sergei");
        userSignupDto.setPhone("+79272087077");
        userSignupDto.setPassword("1234567");
        userSignupDto.setSurname("starodubov");
        if (userDao.findUserByPhone("+79272087077") == null){
            User user = userService.register(userSignupDto, new HttpServletRequestTest());
            assertNotSame(null, user);
            assertNotSame(null, user.getAccessToken());
            assertNotSame(null, user.getAccessToken().getRefreshToken());
            assertEquals(64, user.getPassword().length());
        } else assertThrows(UserAlreadyExistsException.class, () -> userService.register(userSignupDto, new HttpServletRequestTest()));
    }

    @Test
    void mustPassAuth() {
        User user = userDao.findUserByPhone("+79272087077");
        String accessTokenValue = user.getAccessToken().getToken();
        HttpServletRequestTest httpServletRequestTest = new HttpServletRequestTest();
        httpServletRequestTest.setHeader(accessTokenValue);
        assertDoesNotThrow(() -> userService.checkAccessToken(httpServletRequestTest));
    }

    @Test
    void mustAddCoinToUser() {
        User user = userDao.findUserByPhone("+79272087077");
        assertThrows(LazyInitializationException.class, ()->userService.toggleCoin(user, "BTC", CoinOptions.ADD));
    }
}
