package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.dao.UserDAOImpl;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAOImpl userDAOImpl, RoleServiceImpl roleServiceImpl, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDAOImpl;
        this.roleService = roleServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addUser(User user, String[] roles, String pass) {
        System.out.println("password before encryption = "+pass);
        user.setPassword(passwordEncoder.encode(pass));
        System.out.println("password after  encryption = "+user.getPassword());
        user.setRoles(Arrays.stream(roles)
                .map(role -> roleService.findByRole(role))
                .collect(Collectors.toList()));
        userDao.save(user);
        System.out.println(user.toString()+" pass: "+pass);
    }

    @Override
    @Transactional
    public void save(User user) {
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        userDao.update(user.getId(), user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userDao.show(id);
    }

    @Override
    @Transactional
    public void updateUser(User user, String[] roles, String pass) {
        user.setPassword(passwordEncoder.encode(pass));
        user.setRoles(Arrays.stream(roles)
                .map(role -> roleService.findByRole(role))
                .collect(Collectors.toList()));
        userDao.update(user.getId(), user);
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        userDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
