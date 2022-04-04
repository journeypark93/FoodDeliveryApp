package com.spring.fooddeliveryapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;


@NoArgsConstructor
@Setter
@Getter
@Entity
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name="RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    // unique: 중복 허용 여부 (false 일때 중복 허용) (unique = true)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;


    public Food(String name, int price, Restaurant restaurant){  //여기에 왜 restaurant 가 들어가죵?
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }
}
