package com.spring.fooddeliveryapp.repository;

import com.spring.fooddeliveryapp.model.Food;
import com.spring.fooddeliveryapp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurantId(Long restaurantId);

    List<Food> findAllByRestaurant(Restaurant restaurant);
}
