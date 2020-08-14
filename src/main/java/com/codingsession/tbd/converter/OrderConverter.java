package com.codingsession.tbd.converter;

import com.codingsession.tbd.api.model.Order;
import com.codingsession.tbd.model.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    public Order convertToOrder(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setProduct(orderEntity.getProduct());
        return order;
    }

    public OrderEntity convertToOrderDto(Order order) {
        if (order == null) {
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProduct(order.getProduct());
        return orderEntity;
    }
}
