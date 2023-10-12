package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.Data;
import org.example.domain.Customer;
import org.example.domain.Expert;
import org.example.repository.ExpertRepository;

import java.util.List;
import java.util.Optional;
@Data
public class ExpertRepositoryImpl implements ExpertRepository {
    private EntityManager entityManager;

    public ExpertRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Expert save(Expert expert) {
        return entityManager.merge(expert);
    }

    @Override
    public Expert update(Expert expert) {
        return entityManager.merge(expert);
    }

    @Override
    public Long delete(Expert expert) {
        entityManager.remove(expert);
        return expert.getId();
    }

    @Override
    public Optional<Expert> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Expert.class, id));
    }

    @Override
    public List<Expert> findAll() {
        return entityManager.createQuery("from Expert").getResultList();
    }

    @Override
    public Optional<Expert> findByUserName(String userName) {
        Query query = entityManager.createQuery("from Expert c where c.userName =: userName");
        query.setParameter("userName",userName);
        Expert expert = (Expert) query.getSingleResult();
        return Optional.ofNullable(expert);
    }
}
