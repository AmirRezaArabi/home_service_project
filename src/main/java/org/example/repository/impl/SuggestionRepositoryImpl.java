package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import lombok.Data;
import org.example.domain.Suggestion;
import org.example.repository.SuggestionRepository;

import java.util.List;
import java.util.Optional;
@Data
public class SuggestionRepositoryImpl implements SuggestionRepository {
    private EntityManager entityManager;

    public SuggestionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Suggestion save(Suggestion suggestion) {
        return entityManager.merge(suggestion);
    }

    @Override
    public Suggestion update(Suggestion suggestion) {
        return entityManager.merge(suggestion);
    }

    @Override
    public Long delete(Suggestion suggestion) {
        entityManager.remove(suggestion);
        return suggestion.getId();
    }

    @Override
    public Optional<Suggestion> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Suggestion.class, id));
    }

    @Override
    public List<Suggestion> findAll() {
        return entityManager.createQuery("from Suggestion ").getResultList();
    }
}
