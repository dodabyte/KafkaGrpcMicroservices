package com.aston.orderserviceapp.service;

import com.aston.orderserviceapp.dto.entity.order.OrderRequestDto;
import com.aston.orderserviceapp.dto.entity.order.OrderResponseDto;
import com.aston.orderserviceapp.exception.InsertionException;
import com.aston.orderserviceapp.exception.RepositoryException;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto dto) throws RepositoryException, InsertionException;
    List<OrderResponseDto> findAllOrders() throws RepositoryException;
}
