package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.Data;
import org.example.domain.Customer;
import org.example.domain.Service;
import org.example.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;
@Data
public class ServiceRepositoryImpl implements ServiceRepository {
    private EntityManager entityManager;

    public ServiceRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Service save(Service service) {
        return entityManager.merge(service);
    }

    @Override
    public Service update(Service service)
    {
        return entityManager.merge(service);
    }

    @Override
    public Long delete(Service service) {
        entityManager.remove(service);
        return service.getId();
    }

    @Override
    public Optional<Service> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Service.class, id));

    }

    @Override
    public Optional<Service> findByName(String name) {
        Query query = entityManager.createQuery("from Service c where c.name =: name");
        query.setParameter("name",name);
        Service service = (Service) query.getSingleResult();
        return Optional.ofNullable(service);
    }

    @Override
    public List<Service> findAll() {
        return entityManager.createQuery("from Service ").getResultList();
    }
}
