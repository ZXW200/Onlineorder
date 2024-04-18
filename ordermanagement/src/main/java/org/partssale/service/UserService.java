package org.partssale.service;


import org.partssale.domain.dto.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    User findByUsername(String username);

//    boolean resetPassword(String username, String newPassword);

    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);

    List<User> findAllUsers();
}
