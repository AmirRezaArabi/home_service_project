package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.domain.Customer;
import org.example.domain.Order;


import java.util.List;
import java.util.Optional;

public interface OrderRepository {


    Order save(Order order);

    Order update(Order order);

    Long delete(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();
}
