package com.codingsession.tbd.repository;


import com.codingsession.tbd.model.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDto, Integer> {
}
