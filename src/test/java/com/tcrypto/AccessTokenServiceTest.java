package com.tcrypto;

import com.tcrypto.models.AccessToken;
import com.tcrypto.models.User;
import com.tcrypto.services.AccessTokenService;
import com.tcrypto.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccessTokenServiceTest {

    @Autowired
    private AccessTokenService accessTokenService;

    @Test
    void createTokenShouldReturnNewToken() {
        AccessToken token = (AccessToken) accessTokenService.createToken();
        assertNotSame(null, token);
        assertNotSame(null, token.getRefreshToken());
        assertSame(null, token.getUser());
    }
}
