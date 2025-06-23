package org.example.forumjavabiz.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.forumjavabiz.entity.User;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAOImpl {
    @PersistenceContext
    private ArrayList<User> accounts = new ArrayList<>();
//    private long idgen = 0;
//
//    private Long nextId() {
//        return idgen++;
//    }

    private EntityManager em;

    public void register(User user) {
        em.persist(user);
    }
//    public void add(String username, String password) {
//        if (username == null || password == null) {
//            throw new NullPointerException();
//        }
//        // co z rolami? -> na początku każdy jest zwykłym użytkownikiem,
//        // później w edycji można zmienić na moderatora
//        String role = "user";
//
//        User a = new User(nextId(), username, role, password);
//        accounts.add(a);
//    }
//
//    public void edit(User a){
//        // do dokończenia
//    }
//
//    public void remove(User user) {
//        accounts.remove(user);
//    }
//
//    public User findByUsername(String username) {
//        if (username == null) throw new IllegalArgumentException("Username is null");
//
//        return accounts.stream()
//                .filter(a -> a.getUsername().equals(username))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public User findById(Long id) {
//        if (id == null) throw new NullPointerException();
//
//        return accounts.stream()
//                .filter(u -> id.equals(u.getId()))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<User> findAll() {
//        return new ArrayList<>(accounts);
//    }

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
