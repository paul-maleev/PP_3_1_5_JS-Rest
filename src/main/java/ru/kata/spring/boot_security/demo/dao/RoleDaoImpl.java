package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByRole(String role) throws EntityNotFoundException {
        Query query;
        Role result;
        try {
            query = entityManager.createQuery("SELECT r FROM Role r WHERE r.role = : role");
            query.setParameter("role", role);
            result = (Role) query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Role " + role + " not found");
        }
        return result;
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> listRoles() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }
}
