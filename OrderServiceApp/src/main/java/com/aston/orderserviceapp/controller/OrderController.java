package com.aston.orderserviceapp.controller;


import com.aston.orderserviceapp.dto.entity.order.OrderRequestDto;
import com.aston.orderserviceapp.dto.entity.order.OrderResponseDto;
import com.aston.orderserviceapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAllGroups() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.findAllOrders());
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<OrderResponseDto> insertOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.createOrder(orderRequestDto));
    }
}
