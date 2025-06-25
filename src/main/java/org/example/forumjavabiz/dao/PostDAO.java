package org.example.forumjavabiz.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.forumjavabiz.entity.Post;

import java.util.List;

@Stateless
public class PostDAO {
    @PersistenceContext
    private EntityManager em;

    public Post saveOrUpdate(Post post) {
        if(post.getId()==null) {
            em.persist(post);
        }
        else {
            post = em.merge(post);
        }
        return post;
    }

    public void save(Post post) {
        em.persist(post);
    }

    public List<Post> findAll() {
        return em.createQuery("SELECT p FROM Post p ORDER BY p.createdAt DESC", Post.class).getResultList();
    }

    // Edycja istniejącego posta
    public void edit(Post post) {
        em.merge(post);
    }

    // Usunięcie posta
    public void delete(Post post) {
        if (!em.contains(post)) {
            post = em.merge(post);
        }
        em.remove(post);
    }

    // Pobranie posta po ID
    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    // Lista postów po ID tematu
    public List<Post> findByTopicId(Long topicId) {
        return em.createQuery("SELECT p FROM Post p WHERE p.topic.id = :topicId ORDER BY p.createdAt DESC", Post.class)
                .setParameter("topicId", topicId)
                .getResultList();
    }

    // Lista postów po ID użytkownika (autora)
    public List<Post> findByCreatorId(Long userId) {
        return em.createQuery("SELECT p FROM Post p WHERE p.author.user_id = :userId ORDER BY p.createdAt DESC", Post.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
