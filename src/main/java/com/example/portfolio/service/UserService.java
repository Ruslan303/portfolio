package com.example.portfolio.service;

import com.example.portfolio.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> findUserById(Long id);
    List<User> GetAllUser ();
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}
