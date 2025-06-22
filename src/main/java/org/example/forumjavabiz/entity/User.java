package org.example.forumjavabiz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String password; //?????
    private String role;

    public User() {}

    public User(Long id, String role, String password, String username) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.username = username;
    }

    public User(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.password = user.getPassword();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
