package com.spring.fooddeliveryapp.service;

import com.spring.fooddeliveryapp.model.Restaurant;
import com.spring.fooddeliveryapp.repository.RestaurantRepository;
import com.spring.fooddeliveryapp.requestDto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant registeredRestaurants(RestaurantDto restaurantRequest) {
        String name = restaurantRequest.getName();
        int minOrderPrice = restaurantRequest.getMinOrderPrice();
        int deliveryFee = restaurantRequest.getDeliveryFee();

        //minOrderPrice 유효성 검사
        if (1_000 > minOrderPrice) {
            throw new IllegalArgumentException("1000원 이상으로 입력해주세요.");
        } else if (minOrderPrice > 100_000) {
            throw new IllegalArgumentException("100000원 이하로 입력해주세요.");
        } else if (minOrderPrice % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로 입력해주세요.");
        }

        //DeliveryFee 유효성 검사
        if (0 > deliveryFee) {
            throw new IllegalArgumentException("0원 이상으로 입력해주세요.");
        } else if (deliveryFee > 10_000) {
            throw new IllegalArgumentException("10000원 이하로 입력해주세요.");
        } else if (deliveryFee % 500 != 0) {
            throw new IllegalArgumentException("500원 단위로 입력해주세요.");
        }

        Restaurant restaurant = new Restaurant(name, minOrderPrice, deliveryFee);
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}



