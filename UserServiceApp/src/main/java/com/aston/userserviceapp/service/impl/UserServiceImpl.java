package com.aston.userserviceapp.service.impl;

import com.aston.userserviceapp.dto.entity.order.OrderKafkaMessageDto;
import com.aston.userserviceapp.dto.entity.user.UserRequestDto;
import com.aston.userserviceapp.dto.entity.user.UserResponseDto;
import com.aston.userserviceapp.entity.User;
import com.aston.userserviceapp.exception.EntityNotFoundException;
import com.aston.userserviceapp.exception.InsertionException;
import com.aston.userserviceapp.exception.RepositoryException;
import com.aston.userserviceapp.mapper.UserMapper;
import com.aston.userserviceapp.repository.UserRepository;
import com.aston.userserviceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) throws RepositoryException, InsertionException {
        User user =  userRepository.save(userMapper.map(dto));
        Optional<User> optional = Optional.ofNullable(user);
        return userMapper.map(optional.orElseThrow(InsertionException::new));
    }

    @Override
    @Transactional
    public UserResponseDto addOrderToHistory(OrderKafkaMessageDto dto) throws RepositoryException, EntityNotFoundException {
        if (dto == null || dto.getUserId() == 0 || !userRepository.existsById(dto.getUserId()))
            throw new EntityNotFoundException(new IllegalArgumentException());
        Optional<User> optional = userRepository.findById(dto.getUserId());
        User user = optional.orElseThrow(EntityNotFoundException::new);
        user.getHistoryOrderIds().add(dto.getId());
        userRepository.save(user);
        return userMapper.map(user);
    }

    @Override
    @Transactional
    public List<UserResponseDto> findAllUsers() throws RepositoryException {
        return userMapper.map(userRepository.findAll());
    }

    @Override
    @Transactional
    public UserResponseDto findUserById(long id) throws RepositoryException, EntityNotFoundException {
        return userMapper.map(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
