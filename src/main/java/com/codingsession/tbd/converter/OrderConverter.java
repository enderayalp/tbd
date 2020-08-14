package com.codingsession.tbd.converter;

import com.codingsession.tbd.api.model.Order;
import com.codingsession.tbd.model.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    public Order convertToOrder(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setProduct(orderDto.getProduct());
        return order;
    }

    public OrderDto convertToOrderDto(Order order) {
        if (order == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setProduct(order.getProduct());
        return orderDto;
    }
}
