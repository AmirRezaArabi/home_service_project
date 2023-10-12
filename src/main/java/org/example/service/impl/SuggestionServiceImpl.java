package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.config.ApplicationContext;
import org.example.domain.Customer;
import org.example.domain.CustomerRequest;
import org.example.domain.Service;
import org.example.domain.Suggestion;
import org.example.exception.TheInputInformationIsNotValidException;
import org.example.repository.SuggestionRepository;
import org.example.service.SuggestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SuggestionServiceImpl implements SuggestionService {
    private SuggestionRepository suggestionRepository;
    private Suggestion returnObject;
    private Long returnId;

    public SuggestionServiceImpl(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
        returnObject = null;
        returnId = -1L;
    }

    @Override
    public Suggestion save(Suggestion suggestion) {
        try {
            if (isValid(suggestion)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = suggestionRepository.save(suggestion);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Suggestion update(Suggestion suggestion) {
        try {
            if (isValid(suggestion)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = suggestionRepository.save(suggestion);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Long delete(Suggestion suggestion) {
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            returnId = suggestionRepository.delete(suggestion);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnId;
    }

    @Override
    public Optional<Suggestion> findById(Long id) {
        Optional<Suggestion> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = suggestionRepository.findById(id);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public List<Suggestion> findAll() {
        List<Suggestion> all = new ArrayList<>();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            all = suggestionRepository.findAll();
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return all;
    }

    @Override
    public boolean isValid(Suggestion suggestion) {
        Set<ConstraintViolation<Suggestion>> violations = ApplicationContext.getEntityValidator().validate(suggestion);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Suggestion> p : violations) {
                System.out.println(p.getMessage());
                throw new TheInputInformationIsNotValidException("the input information is not correct");
            }
            return false;
        }
        return true;
    }
}

