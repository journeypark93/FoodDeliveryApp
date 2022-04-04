package com.spring.fooddeliveryapp.repository;

import com.spring.fooddeliveryapp.requestDto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDto, Long> {
    List<OrderDto> findAll();
}
