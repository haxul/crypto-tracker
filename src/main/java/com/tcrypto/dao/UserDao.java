package com.tcrypto.dao;

import com.tcrypto.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findUserByPhone(String phone);
}
