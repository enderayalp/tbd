package com.codingsession.tbd.controller;

import com.codingsession.tbd.model.Order;
import com.codingsession.tbd.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders/{ordernumber}")
    @ResponseBody
    public ResponseEntity<Order> getOrderByOrdernumber(@PathVariable String ordernumber) {
        Optional<Order> orderByOrdernumber = orderService.findOrderByOrdernumber(ordernumber);
        return orderByOrdernumber
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/orders")
    @ResponseBody
    public ResponseEntity<Order> submitOrder(@RequestBody Order order) {
        try {
            return new ResponseEntity<>(orderService.submitOrder(order), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
