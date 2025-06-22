package org.example.forumjavabiz.repository;

import org.example.forumjavabiz.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private ArrayList<User> accounts = new ArrayList<>();
    private long idgen = 0;

    private Long nextId() {
        return idgen++;
    }

    public void add(String username, String password) {
        if (username == null || password == null) {
            throw new NullPointerException();
        }
        // co z rolami? -> na początku każdy jest zwykłym użytkownikiem,
        // później w edycji można zmienić na moderatora
        String role = "user";

        User a = new User(nextId(), username, role, password);
        accounts.add(a);
    }

    public void edit(Long id, String username, String password, String role){
        if (id == null || username == null || password == null || role == null) {
            throw new NullPointerException();
        }

        User a = findById(id);

        a.setUsername(username);
        a.setPassword(password);
        a.setRole(role);
    }

    public void remove(User user) {
        accounts.remove(user);
    }

    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username is null");

        return accounts.stream()
                .filter(a -> a.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User findById(Long id) {
        if (id == null) throw new NullPointerException();

        return accounts.stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<User> findAll() {
        return new ArrayList<>(accounts);
    }
}
