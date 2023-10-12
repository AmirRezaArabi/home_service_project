package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.config.ApplicationContext;
import org.example.domain.Customer;
import org.example.domain.CustomerRequest;
import org.example.domain.Order;
import org.example.exception.TheInputInformationIsNotValidException;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private Order returnObject;
    private Long returnId;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        returnObject = null;
        returnId = -1L;
    }

    @Override
    public Order save(Order order) {
        try {
            if (isValid(order)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = orderRepository.save(order);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Order update(Order order) {
        try {
            if (isValid(order)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = orderRepository.save(order);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Long delete(Order order) {
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            returnId = orderRepository.delete(order);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnId;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Optional<Order> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = orderRepository.findById(id);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public List<Order> findAll() {
        List<Order> all = new ArrayList<>();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            all = orderRepository.findAll();
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return all;
    }

    @Override
    public boolean isValid(Order order) {
        Set<ConstraintViolation<Order>> violations = ApplicationContext.getEntityValidator().validate(order);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Order> p : violations) {
                System.out.println(p.getMessage());
                throw new TheInputInformationIsNotValidException("the input information is not correct");
            }
            return false;
        }
        return true;
    }
}

