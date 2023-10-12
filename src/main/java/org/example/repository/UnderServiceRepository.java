package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.domain.Customer;
import org.example.domain.Service;
import org.example.domain.UnderService;

import java.util.List;
import java.util.Optional;

public interface UnderServiceRepository {



    UnderService save(UnderService underService);

    UnderService update(UnderService underService);

    Long delete(UnderService underService);

    Optional<UnderService> findById(Long id);

    List<UnderService> findAll();
    Optional<UnderService> findByName(String name);
}
