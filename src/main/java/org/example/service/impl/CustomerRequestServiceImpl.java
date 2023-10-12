package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.config.ApplicationContext;
import org.example.domain.CustomerRequest;
import org.example.exception.TheInputInformationIsNotValidException;
import org.example.repository.CustomerRequestRepository;
import org.example.service.CustomerRequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CustomerRequestServiceImpl implements CustomerRequestService {
    private final CustomerRequestRepository customerRequestRepository;

    private CustomerRequest returnObject;
    private Long returnId;

    public CustomerRequestServiceImpl(CustomerRequestRepository customerRequestRepository) {
        this.customerRequestRepository = customerRequestRepository;
        returnObject = null;
        returnId = -1L;
    }

    @Override
    public CustomerRequest save(CustomerRequest customerRequest) {
        try {
            if (isValid(customerRequest)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = customerRequestRepository.save(customerRequest);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public CustomerRequest update(CustomerRequest customerRequest) {
        try {
            if (isValid(customerRequest)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = customerRequestRepository.save(customerRequest);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Long delete(CustomerRequest customerRequest) {
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            returnId = customerRequestRepository.delete(customerRequest);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnId;
    }

    @Override
    public Optional<CustomerRequest> findById(Long id) {
        Optional<CustomerRequest> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = customerRequestRepository.findById(id);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public List<CustomerRequest> findAll() {
        List<CustomerRequest> all = new ArrayList<>();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            all = customerRequestRepository.findAll();
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return all;
    }

    @Override
    public boolean isValid(CustomerRequest customerRequest) {
        Set<ConstraintViolation<CustomerRequest>> violations = ApplicationContext.getEntityValidator().validate(customerRequest);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<CustomerRequest> p : violations) {
                System.out.println(p.getMessage());
                throw new TheInputInformationIsNotValidException("the input information is not correct");
            }
            return false;
        }
        return true;
    }
}
