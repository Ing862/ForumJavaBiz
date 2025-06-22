package org.example.Repository;

import org.example.Models.User;
import java.util.List;

public interface UserRepository {
    void add(String username, String password);
    void edit(Long id, String username, String password, String role);
    void remove(User user);
    User findByUsername(String username);
    User findById(Long id);
    List<User> findAll();
}
