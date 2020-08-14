package com.codingsession.tbd.repository;

import com.codingsession.tbd.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrdernumber(String ordernumber);
}
