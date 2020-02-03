package com.tcrypto.dao;

import com.tcrypto.models.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenDao extends CrudRepository<RefreshToken, Integer> {
    RefreshToken findRefreshTokenByToken(String token);
}
