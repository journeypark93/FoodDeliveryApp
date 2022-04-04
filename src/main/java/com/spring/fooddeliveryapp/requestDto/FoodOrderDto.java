package com.spring.fooddeliveryapp.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderDto {
    String name;
    int quantity;
    int price;

    public FoodOrderDto(String foodName, int quantity, int foodPrice) {
        this.name = foodName;
        this.quantity = quantity;
        this.price = foodPrice;
    }
}
