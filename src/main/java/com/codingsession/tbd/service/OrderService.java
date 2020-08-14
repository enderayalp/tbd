package com.codingsession.tbd.service;


import com.codingsession.tbd.model.Order;
import com.codingsession.tbd.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Optional<Order> findOrderByOrdernumber(String ordernumber) {
        return Optional.ofNullable(orderRepository.findByOrdernumber(ordernumber));
    }

    public Order submitOrder(Order order) {
        try {
            return orderRepository.save(order);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException();
        }
    }


}
