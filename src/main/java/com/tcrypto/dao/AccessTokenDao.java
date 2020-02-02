package com.tcrypto.dao;

import com.tcrypto.models.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenDao extends CrudRepository<AccessToken, Integer> {
    public AccessToken findAccessTokenByUserId(int id);
}
