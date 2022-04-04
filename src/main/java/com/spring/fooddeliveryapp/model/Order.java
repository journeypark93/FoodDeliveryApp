package com.spring.fooddeliveryapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name="RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name="FOOD_ID", nullable = false)
    private Food food;

    @Column(nullable = false)
    private int quantity;


    public Order(int quantity, Restaurant restaurant, Food food){  //여기에 왜 restaurant 가 들어가죵?
        this.quantity = quantity;
        this.restaurant = restaurant;
        this.food = food;
    }
}
