package com.fsrg.service.impl;

import com.fsrg.model.dao.UserDAO;
import com.fsrg.model.entity.User;
import com.fsrg.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserImplService implements IUserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> listAll() {
        return (List<User>) userDAO.findAll();
    }
    @Transactional
    @Override
    public User save(User user) {
       return userDAO.save(user);
    }
    @Transactional(readOnly = true)

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(User userDTO) {
        User user = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .country(userDTO.getCountry())
                .password(userDTO.getPassword())
                .build();
         userDAO.delete(user);

    }

    @Override
    public boolean existsById(Integer id) {
        return userDAO.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
