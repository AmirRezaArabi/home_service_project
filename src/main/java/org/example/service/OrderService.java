package org.example.service;

import org.example.domain.CustomerRequest;
import org.example.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order save(Order order);

    Order update(Order order);

    Long delete(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();
    boolean isValid(Order order);
}
