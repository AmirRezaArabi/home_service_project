package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.config.ApplicationContext;
import org.example.domain.*;
import org.example.exception.TheInputInformationIsNotValidException;
import org.example.repository.UnderServiceRepository;
import org.example.service.UnderServiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UnderServiceServiceImpl implements UnderServiceService {
    private UnderServiceRepository underServiceRepository;
    private UnderService returnObject;
    private Long returnId;

    public UnderServiceServiceImpl(UnderServiceRepository underServiceRepository) {
        this.underServiceRepository = underServiceRepository;
        returnObject = null;
        returnId = -1L;

    }

    @Override
    public UnderService save(UnderService underService) {
        try {
            if (isValid(underService)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = underServiceRepository.save(underService);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public UnderService update(UnderService underService) {
        try {
            if (isValid(underService)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = underServiceRepository.save(underService);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Long delete(UnderService underService) {
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            returnId = underServiceRepository.delete(underService);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnId;
    }

    @Override
    public Optional<UnderService> findById(Long id) {
        Optional<UnderService> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = underServiceRepository.findById(id);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public Optional<UnderService> findByName(String name) {
        Optional<UnderService> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = underServiceRepository.findByName(name);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId ;
    }

    @Override
    public List<UnderService> findAll() {
        List<UnderService> all = new ArrayList<>();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            all = underServiceRepository.findAll();
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return all;
    }

    @Override
    public boolean isValid(UnderService underService) {
        Set<ConstraintViolation<UnderService>> violations = ApplicationContext.getEntityValidator().validate(underService);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<UnderService> p : violations) {
                System.out.println(p.getMessage());
                throw new TheInputInformationIsNotValidException("the input information is not correct");
            }
            return false;
        }
        return true;
    }

    @Override
    public Optional<Service> findByUnderServiceName(String name) {
        Optional<Service> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = underServiceRepository.findByUnderServiceName(name);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId ;
    }
}
