package com.tcrypto.dao;

import com.tcrypto.models.Coin;
import com.tcrypto.models.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends CrudRepository<Log, Integer> {
}
