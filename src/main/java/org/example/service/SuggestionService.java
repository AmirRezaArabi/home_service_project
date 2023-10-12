package org.example.service;

import org.example.domain.CustomerRequest;
import org.example.domain.Suggestion;

import java.util.List;
import java.util.Optional;

public interface SuggestionService {
    Suggestion save(Suggestion suggestion);

    Suggestion update(Suggestion suggestion);

    Long delete(Suggestion suggestion);

    Optional<Suggestion> findById(Long id);

    List<Suggestion> findAll();
    boolean isValid(Suggestion suggestion);
}
