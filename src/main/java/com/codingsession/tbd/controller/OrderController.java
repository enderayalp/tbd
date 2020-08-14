package com.codingsession.tbd.controller;

import com.codingsession.tbd.api.model.Order;
import com.codingsession.tbd.api.server.OrdersApi;
import com.codingsession.tbd.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController implements OrdersApi {

    private final OrderService orderService;

    @Override
    public ResponseEntity<Order> findOrderById(Integer orderid) {
        Optional<Order> orderByOrdernumber = orderService.findOrderByOrderId(orderid);
        return orderByOrdernumber
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> submitOrder(Order order) {
        try {
            Optional<Order> submittedOrder = orderService.submitOrder(order);
            if (submittedOrder.isPresent()) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
