package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.domain.Customer;
import org.example.domain.CustomerRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerRequestRepository {


    CustomerRequest save (CustomerRequest customerRequest);
    CustomerRequest update (CustomerRequest customerRequest);
    Long delete (CustomerRequest customerRequest);
    Optional<CustomerRequest> findById (Long id);
    List<CustomerRequest> findAll();
}
