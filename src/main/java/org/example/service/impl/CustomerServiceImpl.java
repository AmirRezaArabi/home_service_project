package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.config.ApplicationContext;
import org.example.domain.Customer;
import org.example.exception.NotValiedPasswordException;
import org.example.exception.TheInputInformationIsNotValidException;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private Customer returnObject;
    private Long returnId;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        returnObject = null;
        returnId = -1L;
    }

    @Override
    public Customer save(Customer customer) {
        try {
            if (isValid(customer)) {
                customer.setRegisterDate(LocalDate.now());
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = customerRepository.save(customer);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Customer update(Customer customer) {
        try {
            if (isValid(customer)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = customerRepository.save(customer);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Long delete(Customer customer) {
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            returnId = customerRepository.delete(customer);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnId;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Optional<Customer> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = customerRepository.findById(id);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public Optional<Customer> findByUserName(String userName) {
        Optional<Customer> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = customerRepository.findByUserName(userName);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public Optional<Customer> chngePassword(String oldPassword, String userName, String newPassword) {
        Optional<Customer> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            Optional<Customer> byUserName = customerRepository.findByUserName(userName);
            Customer customer = byUserName.get();
            if (!byUserName.get().getPassword().equals(oldPassword))
                throw new NotValiedPasswordException("the input password is not true");
            customer.setPassword(newPassword);
            customerRepository.update(customer);
            ApplicationContext.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> all = new ArrayList<>();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            all = customerRepository.findAll();
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return all;
    }

    @Override
    public boolean isValid(Customer customer) {
        Set<ConstraintViolation<Customer>> violations = ApplicationContext.getEntityValidator().validate(customer);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Customer> p : violations) {
                System.out.println(p.getMessage());
                throw new TheInputInformationIsNotValidException("the input information is not correct");
            }
            return false;
        }
        return true;
    }
}
