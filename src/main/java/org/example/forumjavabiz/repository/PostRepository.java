package org.example.Repository;

import org.example.Models.Post;
import java.util.List;

// interfejs DAO
public interface PostRepository {
    void add(Post post);
    void delete(Post post);
    void edit(Post post);
    Post findById(Long id);
    List<Post> findAll();
}
