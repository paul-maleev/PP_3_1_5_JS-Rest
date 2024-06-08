package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    List<User> listUsers();

    User show(int id);

    void save(User user);

    void update(int id, User updatedUser);

    void delete(int id);

    User findByEmail(String email);
}