package com.codingsession.tbd.service;


import com.codingsession.tbd.api.model.Order;
import com.codingsession.tbd.converter.OrderConverter;
import com.codingsession.tbd.model.OrderEntity;
import com.codingsession.tbd.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public Optional<Order> findOrderByOrderId(int orderid) {
        Optional<OrderEntity> orderById = orderRepository.findById(orderid);
        return orderById.map(orderConverter::convertToOrder);

    }

    public Optional<Order> submitOrder(Order order) {
        try {
            OrderEntity orderEntity = orderConverter.convertToOrderDto(order);
            return Optional.ofNullable(orderConverter.convertToOrder(orderRepository.save(orderEntity)));
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException();
        }
    }


}
