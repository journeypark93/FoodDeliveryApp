package com.spring.fooddeliveryapp.controller;

import com.spring.fooddeliveryapp.model.Restaurant;
import com.spring.fooddeliveryapp.repository.RestaurantRepository;
import com.spring.fooddeliveryapp.requestDto.RestaurantDto;
import com.spring.fooddeliveryapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {


    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;


    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantDto restaurantRequest) {
        return restaurantService.registeredRestaurants(restaurantRequest);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){
        return restaurantRepository.findAll();
    }

}
