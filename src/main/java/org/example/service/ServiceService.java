package org.example.service;

import org.example.domain.CustomerRequest;
import org.example.domain.Expert;
import org.example.domain.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    Service save(Service service);

    Service update(Service service);

    Long delete(Service service);

    Optional<Service> findById(Long id);
    Optional<Service> findByName (String name);


    List<Service> findAll();
    boolean isValid(Service service);
}
