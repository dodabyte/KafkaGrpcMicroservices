package com.aston.orderserviceapp.service.impl;

import com.aston.orderserviceapp.dto.entity.order.OrderKafkaMessageDto;
import com.aston.orderserviceapp.dto.entity.order.OrderRequestDto;
import com.aston.orderserviceapp.dto.entity.order.OrderResponseDto;
import com.aston.orderserviceapp.entity.Order;
import com.aston.orderserviceapp.exception.InsertionException;
import com.aston.orderserviceapp.exception.RepositoryException;
import com.aston.orderserviceapp.mapper.OrderMapper;
import com.aston.orderserviceapp.repository.OrderRepository;
import com.aston.orderserviceapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final KafkaTemplate<String, OrderKafkaMessageDto> kafkaTemplate;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) throws RepositoryException, InsertionException {
        Order order =  orderRepository.save(orderMapper.map(dto));
        Optional<Order> optional = Optional.ofNullable(order);
        Order addedOrder = optional.orElseThrow(InsertionException::new);
        kafkaTemplate.send("order-created-message-topic", orderMapper.mapKafka(addedOrder));
        return orderMapper.map(addedOrder);
    }

    @Override
    @Transactional
    public List<OrderResponseDto> findAllOrders() throws RepositoryException {
        return orderMapper.map(orderRepository.findAll());
    }
}
