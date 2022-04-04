package com.spring.fooddeliveryapp.service;

import com.spring.fooddeliveryapp.model.Food;
import com.spring.fooddeliveryapp.model.Restaurant;
import com.spring.fooddeliveryapp.repository.FoodRepository;
import com.spring.fooddeliveryapp.requestDto.FoodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    //리스트로 주어진거니까... 리스트로 받아서, 하나씩 돌면서 음식포함여부, 가격여부 유효성 검사 후, 저장

    public List<Food> registerFoods(List<FoodDto> foodRequests, Restaurant restaurant) {

        List<Food> foodsRequest = new ArrayList<>();

        for (FoodDto registeredfood : foodRequests) {

            String name = registeredfood.getName();
            int price = registeredfood.getPrice();

            //price 유효성 검사
            if (100 > price) {
                throw new IllegalArgumentException("100원 이상으로 입력해주세요.");
            } else if (price > 100000) {
                throw new IllegalArgumentException("1000000원 이하로 입혁해주세요.");
            } else if (price % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로 입력해주세요.");
            }

            Food food = new Food(name, price, restaurant);
            foodsRequest.add(food);
        }

        //이름들 모으기
        List<String> names = new ArrayList<>();
        for (Food name : foodsRequest) {
            names.add(name.getName());
        }
        Collections.sort(names);


        Long restaurantId = restaurant.getId();
        List<Food> sameFood = foodRepository.findAllByRestaurantId(restaurantId);

        //기존에 등록된 이름과 맞는지 확인하기
        for (Food name : foodsRequest) {
            for (Food value : sameFood) {
                if (Objects.equals(value.getName(), name.getName())) {
                    throw new IllegalArgumentException("이미 등록된 음식이 존재합니다.");
                }
            }
        }

        //등록하려는 이름들 중에 중복된 것 있는지 확인하기
        for (int i = 0; i < names.size() - 1; i++) {
            if (Objects.equals(names.get(i), names.get(i + 1))) {
                throw new IllegalArgumentException("등록하려는 음식명 중 중복된 이름이 있습니다.");
            }
        }

        foodRepository.saveAll(foodsRequest);
        return null;
    }


    //주어진 id값 레스토랑에 등록된 음심 찾기
    public List<Food> getFoods(Restaurant restaurant) {
        return foodRepository.findAllByRestaurant(restaurant);
    }
}
