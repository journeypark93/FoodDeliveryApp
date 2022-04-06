package com.spring.fooddeliveryapp.controller;

import com.spring.fooddeliveryapp.requestDto.OrderDto;
import com.spring.fooddeliveryapp.requestDto.OrderRequestDto;
import com.spring.fooddeliveryapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@ResponseStatus(HttpStatus.OK)
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrderDto createOrders(@RequestBody OrderRequestDto orderList){
        OrderDto orders = orderService.registerOrder(orderList);
        return orders;
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders(){

        return orderService.getOrder();
    }
}
