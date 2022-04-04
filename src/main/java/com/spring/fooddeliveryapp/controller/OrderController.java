package com.spring.fooddeliveryapp.controller;

import com.spring.fooddeliveryapp.requestDto.OrderDto;
import com.spring.fooddeliveryapp.requestDto.OrderRequestDto;
import com.spring.fooddeliveryapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/request")
    public List<OrderDto> createOrders(@RequestBody List<OrderRequestDto> orderList){
        return orderService.registerOrder(orderList);
    }
}
