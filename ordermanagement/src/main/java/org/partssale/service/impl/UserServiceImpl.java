package org.partssale.service.impl;


import org.partssale.domain.dto.User;
import org.partssale.mapper.UserMapper;
import org.partssale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }


    @Override
    public void createUser(User user) {
        user.setAddtime(new Timestamp(System.currentTimeMillis())); // Set current timestamp
        userMapper.insertUser(user);
    }
//
//    @Override
//    public boolean resetPassword(String username, String newPassword) {
//        try {
//            int updatedRows = userMapper.resetPassword(username, newPassword);
//            return updatedRows > 0;
//        } catch (Exception e) {
//            // Log the exception to help diagnose the issue
//            System.out.println("Error resetting password: " + e.getMessage());
//            return false;
//        }


}