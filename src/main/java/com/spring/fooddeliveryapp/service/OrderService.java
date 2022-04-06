package com.spring.fooddeliveryapp.service;

import com.spring.fooddeliveryapp.model.Food;
import com.spring.fooddeliveryapp.model.FoodOrder;
import com.spring.fooddeliveryapp.model.IsOrder;
import com.spring.fooddeliveryapp.model.Restaurant;
import com.spring.fooddeliveryapp.repository.FoodOrderRepository;
import com.spring.fooddeliveryapp.repository.FoodRepository;
import com.spring.fooddeliveryapp.repository.IsOrderRepository;
import com.spring.fooddeliveryapp.repository.RestaurantRepository;
import com.spring.fooddeliveryapp.requestDto.FoodOrderDto;
import com.spring.fooddeliveryapp.requestDto.FoodOrderRequestDto;
import com.spring.fooddeliveryapp.requestDto.OrderDto;
import com.spring.fooddeliveryapp.requestDto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final IsOrderRepository orderRepository;
    private final FoodOrderRepository foodOrderRepository;



    @Transactional
    public OrderDto registerOrder(OrderRequestDto orderList) {
        List<FoodOrder> foodOrderLists = new ArrayList<>();
        List<FoodOrderDto> foodOrderDtos = new ArrayList<>();

        Long restaurnatId = orderList.getRestaurantId();
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurnatId);
        String name;
        int deliveryFee;
        int totalPrice = 0;
        int minOrderPrice;

        if (restaurant.isPresent()) {
            name = restaurant.get().getName();
            deliveryFee = restaurant.get().getDeliveryFee();
            totalPrice += deliveryFee;
            minOrderPrice = restaurant.get().getMinOrderPrice();
        } else throw new IllegalArgumentException("등록되지 않은 음식점입니다.");


        List<FoodOrderRequestDto> foodOrderRequestDtoList = orderList.getFoods();
        for (FoodOrderRequestDto foodList : foodOrderRequestDtoList) {
            Long foodId = foodList.getId();
            Food food = foodRepository.getById(foodId);

            String foodName = food.getName();
            int quantity = foodList.getQuantity();
            int price = (food.getPrice() * quantity);

            totalPrice += price;

            if (quantity<1){
                throw new IllegalArgumentException("1이상으로 입력해주세요");
            } else if(quantity>100){
                throw new IllegalArgumentException("100이하로 입력해주세요");
            }


            FoodOrder foodOrder = new FoodOrder(foodName, quantity, price);
            foodOrderLists.add(foodOrder);

            FoodOrderDto foodOrderDto = new FoodOrderDto(foodName, quantity, price);
            foodOrderDtos.add(foodOrderDto);

            IsOrder order = new IsOrder(name, deliveryFee, totalPrice);
            orderRepository.save(order);
        }

        if (totalPrice-deliveryFee < minOrderPrice){
            throw new IllegalArgumentException("최소주문금액이상으로 주문해주세요.");
        }
        foodOrderRepository.saveAll(foodOrderLists);

        OrderDto orderDto = new OrderDto(name, foodOrderDtos, deliveryFee, totalPrice);
        return orderDto;
    }
}
