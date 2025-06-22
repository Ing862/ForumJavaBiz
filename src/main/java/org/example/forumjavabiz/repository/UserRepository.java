package org.example.forumjavabiz.repository;

import org.example.forumjavabiz.models.User;
import java.util.List;

public interface UserRepository {
    void add(String username, String password);
    void edit(User user);
    void remove(User user);
    User findByUsername(String username);
    User findById(Long id);
    List<User> findAll();
}
