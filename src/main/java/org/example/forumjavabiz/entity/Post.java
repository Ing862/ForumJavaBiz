package org.example.forumjavabiz.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne(optional = false) // foreign key do User
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;
    private Long topicId;

    public Post() {
        this.createdAt = LocalDateTime.now();
    }

    public Post(Long id, String title, String content, User creator, Long topicId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.creator = creator;
        this.topicId = topicId;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setTimeOfCreation() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}

