package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user, String[] roles, String pass);

    User getUser(int id);

    void updateUser(User user, String[] roles, String pass);

    void deleteUser(int id);

    List<User> listUsers();

    User findByEmail(String email);

}