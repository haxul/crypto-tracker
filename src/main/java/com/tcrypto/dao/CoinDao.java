package com.tcrypto.dao;

import com.tcrypto.models.Coin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinDao extends CrudRepository<Coin, Integer> {
}
