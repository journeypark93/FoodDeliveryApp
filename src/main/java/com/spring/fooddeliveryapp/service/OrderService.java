package com.spring.fooddeliveryapp.service;

import com.spring.fooddeliveryapp.model.Food;
import com.spring.fooddeliveryapp.model.Restaurant;
import com.spring.fooddeliveryapp.repository.FoodRepository;
import com.spring.fooddeliveryapp.repository.RestaurantRepository;
import com.spring.fooddeliveryapp.requestDto.FoodOrderDto;
import com.spring.fooddeliveryapp.requestDto.FoodOrderRequestDto;
import com.spring.fooddeliveryapp.requestDto.OrderDto;
import com.spring.fooddeliveryapp.requestDto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    public List<OrderDto> registerOrder(List<OrderRequestDto> orderList) {
//        List<OrderDto> orderDtos = new ArrayList<>();
        List<FoodOrderDto> foodOrderDtos = new ArrayList<>();


        for (OrderRequestDto orders : orderList) {
            Long ownerId = orders.getRestaurantId();
            List<FoodOrderRequestDto> orderRequestDtoList = orders.getFoods();
            int totalPrice = 0;

            for (FoodOrderRequestDto orderLists : orderRequestDtoList) {
                Food food = foodRepository.getById(orderLists.getId());
                String foodName = food.getName();
                int quantity = orderLists.getQuantity();
                int foodPrice = food.getPrice() * quantity;

                FoodOrderDto foodOrderDto = new FoodOrderDto(foodName, quantity, foodPrice);
                foodOrderDtos.add(foodOrderDto);
                totalPrice += foodPrice;
            }

            Restaurant restaurant = restaurantRepository.getById(ownerId);
            String name = restaurant.getName();
            int deliveraryFee = restaurant.getDeliveryFee();


            List<OrderDto> orderDtos = new ArrayList<>(name, foodOrderDtos, deliveraryFee, totalPrice);

//            List<OrderDto> orderDtos1 = orderDtos.addAll(name, foodOrderDtos, deliveraryFee, totalPrice);
        }


        return orderDtos;
    }
}
