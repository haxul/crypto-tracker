package com.tcrypto.dao;

import com.tcrypto.models.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.servlet.http.PushBuilder;

@Repository
public interface AccessTokenDao extends CrudRepository<AccessToken, Integer> {
    AccessToken findAccessTokenByUserId(int id);
    AccessToken findAccessTokenByToken(String value);
}
