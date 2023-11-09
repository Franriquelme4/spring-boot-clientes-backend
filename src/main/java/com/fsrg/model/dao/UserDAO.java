package com.fsrg.model.dao;

import com.fsrg.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User,Integer> {
    User findByUsername(String username);
}
