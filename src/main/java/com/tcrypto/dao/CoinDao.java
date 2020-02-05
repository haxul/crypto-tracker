package com.tcrypto.dao;

import com.tcrypto.models.Coin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinDao extends CrudRepository<Coin, Integer> {
    Coin findCoinByCoinmarketcapId(int id);
    Coin findCoinBySymbol(String symbol);
}
