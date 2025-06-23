package org.example.forumjavabiz.dao;

import org.example.forumjavabiz.entity.Post;
import java.util.List;

// interfejs DAO
public interface PostDAO {
    void add(String title, String content, Long creatorId, Long topicId);
    void delete(Post post);
    void edit(Post post);
    Post findById(Long id);
    List<Post> findAll();
}
