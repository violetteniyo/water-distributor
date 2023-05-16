package com.example.inyange.service;

import com.example.inyange.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findUserById(int code);
    List<User> displayUsers();
    User updateUser(User user);
    void deleteUser(int code);

    User findUserUsername(User name);
}
