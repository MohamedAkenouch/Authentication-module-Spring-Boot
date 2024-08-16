package com.app.auth.service;

import com.app.auth.model.User;

import java.util.Optional;

public interface UserService {
    User getUserById(Long id);
    User getUserByUsername(String username);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}