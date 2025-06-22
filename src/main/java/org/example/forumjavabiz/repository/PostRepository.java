package org.example.forumjavabiz.repository;

import org.example.forumjavabiz.models.Post;
import java.util.List;

// interfejs DAO
public interface PostRepository {
    void add(String title, String content, Long creatorId, Long topicId);
    void delete(Post post);
    void edit(Post post);
    Post findById(Long id);
    List<Post> findAll();
}
