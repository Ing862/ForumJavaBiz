package org.example.Models;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long creatorId;
    private Long topicId;

    public Post(Long id, String title, String content, Long creatorId, Long topicId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.creatorId = creatorId;
        this.topicId = topicId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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

