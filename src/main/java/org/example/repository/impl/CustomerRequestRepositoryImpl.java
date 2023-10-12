package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import lombok.Data;
import org.example.domain.CustomerRequest;
import org.example.repository.CustomerRequestRepository;

import java.util.List;
import java.util.Optional;
@Data

public class CustomerRequestRepositoryImpl implements CustomerRequestRepository {

    private EntityManager entityManager;

    public CustomerRequestRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CustomerRequest save(CustomerRequest customerRequest) {
        return entityManager.merge(customerRequest);
    }

    @Override
    public CustomerRequest update(CustomerRequest customerRequest) {
        return entityManager.merge(customerRequest);
    }

    @Override
    public Long delete(CustomerRequest customerRequest) {
        entityManager.remove(customerRequest);
        return customerRequest.getId();
    }

    @Override
    public Optional<CustomerRequest> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CustomerRequest.class, id));
    }

    @Override
    public List<CustomerRequest> findAll() {
        return entityManager.createQuery("from CustomerRequest").getResultList();
    }
}
