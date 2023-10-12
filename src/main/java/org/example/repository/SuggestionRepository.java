package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.domain.Customer;
import org.example.domain.Service;
import org.example.domain.Suggestion;

import java.util.List;
import java.util.Optional;

public interface SuggestionRepository {

    Suggestion save(Suggestion suggestion);

    Suggestion update(Suggestion suggestion);

    Long delete(Suggestion suggestion);

    Optional<Suggestion> findById(Long id);

    List<Suggestion> findAll();
}
