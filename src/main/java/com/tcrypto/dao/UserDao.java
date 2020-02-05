package com.tcrypto.dao;

import com.tcrypto.models.Coin;
import com.tcrypto.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.Set;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findUserByPhone(String phone);
}
