package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("select distinct u from User u JOIN FETCH u.roles", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        User userToBeUpdated = entityManager.find(User.class, updatedUser.getId());
        if (userToBeUpdated != null) {
            userToBeUpdated.setFirstName(updatedUser.getFirstName());
            userToBeUpdated.setLastName(updatedUser.getLastName());
            userToBeUpdated.setAge(updatedUser.getAge());
            userToBeUpdated.setEmail(updatedUser.getEmail());
            userToBeUpdated.setRoles(updatedUser.getRoles());
        }
    }

    @Override
    public void delete(int id) {
        User userToDelete = entityManager.find(User.class, id);
        if (userToDelete != null) {
            entityManager.remove(userToDelete);
        }
    }

    @Override
    public User findByEmail(String email) throws EntityNotFoundException {
        Query query;
        User result;
        try {
            query = entityManager.createQuery("SELECT distinct u FROM User u JOIN FETCH u.roles WHERE u.email = : e");
            query.setParameter("e", email);
            result = (User) query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("User with email " + email + " not found");
        }
        return result;
    }
}
