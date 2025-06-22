package org.example.forumjavabiz.dao;

import org.example.forumjavabiz.entity.User;
import java.util.List;

public interface UserDAO {
    void add(String username, String password);
    void edit(User user);
    void remove(User user);
    User findByUsername(String username);
    User findById(Long id);
    List<User> findAll();
}
