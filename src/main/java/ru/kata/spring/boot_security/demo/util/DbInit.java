package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbInit {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        List<Role> listAdmin = new ArrayList<>();
        listAdmin.add(admin);
        List<Role> listUser = new ArrayList<>();
        listAdmin.add(user);
        List<Role> listUserAndAdmin = new ArrayList<>();
        listUserAndAdmin.add(user);
        listUserAndAdmin.add(admin);
        try {
            roleService.findByRole(admin.getRole());
        } catch (EntityNotFoundException e) {
            roleService.save(admin);
        }
        try {
            roleService.findByRole(user.getRole());
        } catch (EntityNotFoundException e) {
            roleService.save(user);
        }

        User userAdmin = new User("admin", "admin", 42, "admin@mail.com","admin",listAdmin);
        User userUser = new User("user", "user", 19, "user@mail.com","user",listUser);
        User userUserAndAdmin = new User("useradmin", "useradmin", 50, "useradmin@mail.com","useradmin",listUserAndAdmin);
        String[] rolesAdmin = {"ROLE_ADMIN"};
        String[] rolesUserAndAdmin = {"ROLE_ADMIN", "ROLE_USER"};
        String[] rolesUser = {"ROLE_USER"};
        try {
            userService.findByEmail(userAdmin.getEmail());
        } catch (EntityNotFoundException e) {
            userService.addUser(userAdmin, rolesAdmin, "admin");
        }
        try {
            userService.findByEmail(userUser.getEmail());
        } catch (EntityNotFoundException e) {
            userService.addUser(userUser, rolesUser, "user");
        }
        try {
            userService.findByEmail(userUserAndAdmin.getEmail());
        } catch (EntityNotFoundException e) {
            userService.addUser(userUserAndAdmin, rolesUserAndAdmin, "useradmin");
        }


    }
}