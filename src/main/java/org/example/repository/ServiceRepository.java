package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.domain.Customer;
import org.example.domain.Order;
import org.example.domain.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {


    Service save(Service service);

    Service update(Service service);

    Long delete(Service service);

    Optional<Service> findById(Long id);


    Optional<Service> findByName(String name);

    List<Service> findAll();
}
