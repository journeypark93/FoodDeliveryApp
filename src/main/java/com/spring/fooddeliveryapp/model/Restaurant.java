package com.spring.fooddeliveryapp.model;

import com.spring.fooddeliveryapp.requestDto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO) //id 자동생성(++1)
    @Id //얘가 대장이야~
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    // unique: 중복 허용 여부 (false 일때 중복 허용) (unique = true)

    public Restaurant(String name, int minOrderPrice, int deliveryFee){
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }

//    public void registeredRestaurants(RestaurantDto requestDto){
//        this.name = requestDto.getName();
//        this.minOrderPrice = requestDto.getMinOrderPrice();
//        this.deliveryFee = requestDto.getDeliveryFee();
//    }
}
