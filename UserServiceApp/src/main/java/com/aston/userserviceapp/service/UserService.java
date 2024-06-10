package com.aston.userserviceapp.service;

import com.aston.userserviceapp.dto.entity.order.OrderKafkaMessageDto;
import com.aston.userserviceapp.dto.entity.user.UserRequestDto;
import com.aston.userserviceapp.dto.entity.user.UserResponseDto;
import com.aston.userserviceapp.exception.EntityNotFoundException;
import com.aston.userserviceapp.exception.InsertionException;
import com.aston.userserviceapp.exception.RepositoryException;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto) throws RepositoryException, InsertionException;
    UserResponseDto addOrderToHistory(OrderKafkaMessageDto dto) throws RepositoryException, EntityNotFoundException;
    List<UserResponseDto> findAllUsers() throws RepositoryException;
    UserResponseDto findUserById(long id) throws RepositoryException, EntityNotFoundException;
}
