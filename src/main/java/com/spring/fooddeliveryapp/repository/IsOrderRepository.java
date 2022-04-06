package com.spring.fooddeliveryapp.repository;

import com.spring.fooddeliveryapp.model.IsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IsOrderRepository extends JpaRepository<IsOrder, Long> {
    List<IsOrder> findAll();
    Optional<IsOrder> findById(Long id);
}
