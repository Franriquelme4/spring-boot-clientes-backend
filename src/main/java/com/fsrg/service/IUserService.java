package com.fsrg.service;

import com.fsrg.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService {
    List<User> listAll();

    @Transactional
    User save(User user);

    User findById(Integer id);
    void delete(User userDTO);

    boolean existsById(Integer id);

    User findByUsername(String username);


}
