package com.spring.fooddeliveryapp.repository;

import com.spring.fooddeliveryapp.model.Food;
import com.spring.fooddeliveryapp.model.FoodOrder;
import com.spring.fooddeliveryapp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findAll();
}
