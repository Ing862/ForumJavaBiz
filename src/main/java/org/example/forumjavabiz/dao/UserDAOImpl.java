package org.example.forumjavabiz.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.forumjavabiz.entity.User;

import java.util.List;

@Stateless
public class UserDAOImpl {
    @PersistenceContext
    private EntityManager em;

    public void register(User user) {
        em.persist(user);
    }

    public User findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public void remove(User user) {
        em.remove(em.merge(user)); // merge() dla bezpieczeństwa
    }

    public void edit(User user) {
        em.merge(user);
    }

    public User findByUsernameAndPassword(String username, String password) {
        try {
            return em.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
