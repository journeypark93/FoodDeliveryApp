package com.spring.fooddeliveryapp.repository;

import com.spring.fooddeliveryapp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long restaurantId);
}
