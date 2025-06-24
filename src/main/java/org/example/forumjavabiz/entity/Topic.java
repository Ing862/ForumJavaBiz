package org.example.forumjavabiz.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;
    private LocalDateTime creationDate;

    public Topic() {
        this.creationDate = LocalDateTime.now();
    }

    public Topic(String title, User creator, LocalDateTime creationDate) {
        this.title = title;
        this.creator = creator;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
