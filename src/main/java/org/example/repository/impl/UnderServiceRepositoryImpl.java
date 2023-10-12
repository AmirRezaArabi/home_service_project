package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.Data;
import org.example.domain.Service;
import org.example.domain.UnderService;
import org.example.repository.UnderServiceRepository;

import java.util.List;
import java.util.Optional;
@Data
public class UnderServiceRepositoryImpl implements UnderServiceRepository {
    private EntityManager entityManager;

    public UnderServiceRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UnderService save(UnderService underService) {
        return entityManager.merge(underService);
    }

    @Override
    public UnderService update(UnderService underService) {
        return entityManager.merge(underService);
    }

    @Override
    public Long delete(UnderService underService) {
        entityManager.remove(underService);
        return underService.getId();
    }

    @Override
    public Optional<UnderService> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UnderService.class, id));
    }

    @Override
    public List<UnderService> findAll() {
        return entityManager.createQuery("from UnderService").getResultList();
    }

    @Override
    public Optional<UnderService> findByName(String name) {
        Query query = entityManager.createQuery("from UnderService c where c.name =: name");
        query.setParameter("name",name);
        UnderService underService = (UnderService) query.getSingleResult();
        return Optional.ofNullable(underService);
    }

    @Override
    public Optional<Service> findByUnderServiceName(String name) {
        Query query = entityManager.createQuery("from UnderService c where c.service.name =: name");
        query.setParameter("name",name);
        Service service = (Service) query.getSingleResult();
        return Optional.ofNullable(service);
    }
}
