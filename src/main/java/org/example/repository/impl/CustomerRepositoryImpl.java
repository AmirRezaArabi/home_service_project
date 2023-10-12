package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.Customer;
import org.example.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
@Data

public class CustomerRepositoryImpl implements CustomerRepository {
    private final EntityManager entityManager;

    public CustomerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Customer save(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public Long delete(Customer customer) {
        entityManager.remove(customer);
        return customer.getId();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Customer customer = entityManager.find(Customer.class,id);
        return Optional.ofNullable(customer) ;
    }

    @Override
    public Optional<Customer> findByUserName(String userName) {
        Query query = entityManager.createQuery("from Customer c where c.userName =: userName");
        query.setParameter("userName",userName);
        Customer customer = (Customer)query.getSingleResult();
        return Optional.ofNullable(customer);
    }



    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("from Customer").getResultList().stream().map(e->new Customer()).toList();
    }
}
