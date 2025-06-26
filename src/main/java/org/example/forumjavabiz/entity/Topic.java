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
    private String description;
    @ManyToOne()
    @JoinColumn(name = "author", nullable = false)
    private User author;
    private LocalDateTime creationDate;

    public Topic() {
        this.creationDate = LocalDateTime.now();
    }

    public Topic(String title, String description, User author) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.creationDate = LocalDateTime.now();
    }

    public Topic(Long id, String title, String description, User author, LocalDateTime creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.creationDate = LocalDateTime.now();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
