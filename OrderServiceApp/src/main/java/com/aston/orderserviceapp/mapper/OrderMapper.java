package com.aston.orderserviceapp.mapper;

import com.aston.orderserviceapp.dto.entity.order.OrderKafkaMessageDto;
import com.aston.orderserviceapp.dto.entity.order.OrderRequestDto;
import com.aston.orderserviceapp.dto.entity.order.OrderResponseDto;
import com.aston.orderserviceapp.dto.entity.order.OrderUpdateDto;
import com.aston.orderserviceapp.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "userId", source = "entityDto.userId")
    @Mapping(target = "productName", source = "entityDto.productName")
    @Mapping(target = "count", source = "entityDto.count")
    Order map(OrderRequestDto entityDto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "userId", source = "entity.userId")
    @Mapping(target = "productName", source = "entity.productName")
    @Mapping(target = "count", source = "entity.count")
    OrderResponseDto map(Order entity);

    @Mapping(target = "id", source = "entityDto.id")
    @Mapping(target = "userId", source = "entityDto.userId")
    @Mapping(target = "productName", source = "entityDto.productName")
    @Mapping(target = "count", source = "entityDto.count")
    Order map(OrderUpdateDto entityDto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "userId", source = "entity.userId")
    OrderKafkaMessageDto mapKafka(Order entity);

    List<OrderResponseDto> map(List<Order> entities);
}
