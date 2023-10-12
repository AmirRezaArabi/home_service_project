package org.example.repository;

import jakarta.persistence.EntityManager;
import lombok.Data;
import org.example.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Customer save (Customer customer);
    Customer update (Customer customer);
    Long delete (Customer customer);
    Optional<Customer> findById (Long id);
    Optional<Customer> findByUserName (String userName);


    List<Customer> findAll();

}
