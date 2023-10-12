package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import lombok.Data;
import org.example.domain.Order;
import org.example.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
@Data
public class OrderRepositoryImpl implements OrderRepository {
    private EntityManager entityManager;

    public OrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Order save(Order order) {
        return entityManager.merge(order);
    }

    @Override
    public Order update(Order order) {
        return entityManager.merge(order);
    }

    @Override
    public Long delete(Order order) {
        entityManager.remove(order);
        return order.getId() ;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("from Order").getResultList();
    }
}
