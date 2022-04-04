package com.spring.fooddeliveryapp.controller;

import com.spring.fooddeliveryapp.model.Food;
import com.spring.fooddeliveryapp.model.Restaurant;
import com.spring.fooddeliveryapp.repository.RestaurantRepository;
import com.spring.fooddeliveryapp.requestDto.FoodDto;
import com.spring.fooddeliveryapp.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final RestaurantRepository restaurantRepository;
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")

    public List<Food> createFood(@RequestBody List<FoodDto> foodsRequests, @PathVariable Long restaurantId){
        Restaurant restaurant = restaurantRepository.getById(restaurantId);

        return foodService.registerFoods(foodsRequests, restaurant);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantRepository.getById(restaurantId);

        return foodService.getFoods(restaurant);
    }
}
