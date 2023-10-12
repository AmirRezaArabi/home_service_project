package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.config.ApplicationContext;
import org.example.domain.Customer;
import org.example.domain.CustomerRequest;
import org.example.domain.Order;
import org.example.domain.Service;
import org.example.exception.TheInputInformationIsNotValidException;
import org.example.repository.ServiceRepository;
import org.example.service.ServiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;
    private Service returnObject;
    private Long returnId;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
        returnObject = null;
        returnId = -1L ;
    }

    @Override
    public Service save(Service service) {
        try {
            if (isValid(service)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = serviceRepository.save(service);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }} catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Service update(Service service) {
        try {
            if (isValid(service)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = serviceRepository.save(service);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }} catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Long delete(Service service) {
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            returnId = serviceRepository.delete(service);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnId;
    }

    @Override
    public Optional<Service> findById(Long id) {
        Optional<Service> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = serviceRepository.findById(id);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId ;
    }

    @Override
    public Optional<Service> findByName(String name) {
        Optional<Service> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = serviceRepository.findByName(name);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId ;
    }

    @Override
    public List<Service> findAll() {
        List<Service> all = new ArrayList<>();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            all = serviceRepository.findAll();
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return all;
    }

    @Override
    public boolean isValid(Service service) {
        Set<ConstraintViolation<Service>> violations = ApplicationContext.getEntityValidator().validate(service);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Service> p : violations) {
                System.out.println(p.getMessage());
                throw new TheInputInformationIsNotValidException("the input information is not correct");
            }
            return false;
        }
        return true;
    }
}
