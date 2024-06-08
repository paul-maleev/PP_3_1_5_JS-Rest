package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDao;

    @Autowired
    public RoleServiceImpl(RoleDaoImpl roleDaoImpl) {
        this.roleDao = roleDaoImpl;
    }

    @Transactional(readOnly = true)
    @Override
    public Role findByRole(String role) {
        return roleDao.findByRole(role);
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> listRoles() {
        return roleDao.listRoles();
    }
}
