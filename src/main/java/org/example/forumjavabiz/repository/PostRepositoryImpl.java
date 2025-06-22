package org.example.forumjavabiz.repository;

import org.example.forumjavabiz.models.Post;
import org.example.forumjavabiz.models.User;
import org.example.forumjavabiz.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {
    private ArrayList<Post> posts = new ArrayList<>();
    private long idgen = 0;

    private Long nextId() {
        return idgen++;
    }

    public void add(String title, String content, Long creatorId, Long topicId) {
        if (title == null || content == null || creatorId == null) {
            throw new NullPointerException();
        }

        Post a = new Post(nextId(), title, content, creatorId, topicId);
        posts.add(a);
    }

    public void delete(Post post) {
        posts.remove(post);
    }

    public void edit(Post post) {
        // do dokończenia
    }

    public Post findById(Long id) {
        if (id == null) throw new NullPointerException();

        return posts.stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst()
                .orElse(null);
    }
    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

}
