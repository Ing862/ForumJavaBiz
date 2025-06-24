package org.example.forumjavabiz.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.forumjavabiz.entity.Post;
import org.example.forumjavabiz.entity.Topic;

import java.util.List;

@Stateless
public class TopicDAO {
    @PersistenceContext
    private EntityManager em;

    public Topic saveOrUpdate(Topic topic) {
        if(topic.getId()==null) {
            em.persist(topic);
        }
        else {
            topic = em.merge(topic);
        }
        return topic;
    }


    // Zapis nowego tematu
    public void createTopic(Topic topic) {
        em.persist(topic);
    }

    // Aktualizacja istniejącego tematu
    public void updateTopic(Topic topic) {
        em.merge(topic);
    }

    // Usunięcie tematu
    public void deleteTopic(Topic topic) {
        Topic managed = em.contains(topic) ? topic : em.merge(topic);
        em.remove(managed);
    }

    // Pobranie tematu po ID
    public Topic findById(Long id) {
        return em.find(Topic.class, id);
    }

    // Lista wszystkich tematów
    public List<Topic> findAll() {
        TypedQuery<Topic> query = em.createQuery(
                "SELECT t FROM Topic t ORDER BY t.creationDate DESC", Topic.class);
        return query.getResultList();
    }

    // Lista tematów po autorze
    public List<Topic> findByCreatorId(Long userId) {
        return em.createQuery(
                        "SELECT t FROM Topic t WHERE t.creator.user_id = :userId ORDER BY t.creationDate DESC", Topic.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
