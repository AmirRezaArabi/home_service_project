package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.config.ApplicationContext;
import org.example.domain.Customer;
import org.example.domain.CustomerRequest;
import org.example.domain.Expert;
import org.example.domain.Order;
import org.example.domain.enums.EXPERT_STATUS;
import org.example.exception.NotValiedPasswordException;
import org.example.exception.TheInputInformationIsNotValidException;
import org.example.repository.ExpertRepository;
import org.example.service.ExpertService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ExpertServiceImpl implements ExpertService {

    private ExpertRepository expertRepository;
    private Expert returnObject;
    private Long returnId;

    public ExpertServiceImpl(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
        returnObject = null;
        returnId = -1L;
    }

    @Override
    public Expert save(Expert expert) {
        try {
            if (isValid(expert)) {
                expert.setRegisterDate(LocalDate.now());
                ApplicationContext.getEntityManager().getTransaction().begin();
                expert.setExpert_status(EXPERT_STATUS.NEW);
                returnObject = expertRepository.save(expert);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Expert update(Expert expert) {
        try {
            if (isValid(expert)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = expertRepository.save(expert);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Long delete(Expert expert) {

        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            returnId = expertRepository.delete(expert);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnId;
    }

    @Override
    public Optional<Expert> findById(Long id) {
        Optional<Expert> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = expertRepository.findById(id);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public Optional<Expert> findByUserName(String userName) {
        Optional<Expert> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = expertRepository.findByUserName(userName);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public Optional<Expert> chngePassword(String oldPassword, String userName, String newPassword) {
        Optional<Expert> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            Optional<Expert> byUserName = expertRepository.findByUserName(userName);
            Expert expert = byUserName.get();
            if (!byUserName.get().getPassword().equals(oldPassword))
                throw new NotValiedPasswordException("the input password is not true");
            expert.setPassword(newPassword);
            expertRepository.update(expert);
            ApplicationContext.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public List<Expert> findAll() {
        List<Expert> all = new ArrayList<>();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            all = expertRepository.findAll();
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return all;
    }

    @Override
    public boolean isValid(Expert expert) {
        Set<ConstraintViolation<Expert>> violations = ApplicationContext.getEntityValidator().validate(expert);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Expert> p : violations) {
                System.out.println(p.getMessage());
                throw new TheInputInformationIsNotValidException("the input information is not correct");
            }
            return false;
        }
        return true;
    }
}
