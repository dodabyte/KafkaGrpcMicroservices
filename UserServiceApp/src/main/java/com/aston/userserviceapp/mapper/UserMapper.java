package com.aston.userserviceapp.mapper;

import com.aston.userserviceapp.dto.entity.user.UserRequestDto;
import com.aston.userserviceapp.dto.entity.user.UserResponseDto;
import com.aston.userserviceapp.dto.entity.user.UserUpdateDto;
import com.aston.userserviceapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "firstName", source = "entityDto.firstName")
    @Mapping(target = "secondName", source = "entityDto.secondName")
    @Mapping(target = "patronymic", source = "entityDto.patronymic")
    @Mapping(target = "email", source = "entityDto.email")
    User map(UserRequestDto entityDto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "secondName", source = "entity.secondName")
    @Mapping(target = "patronymic", source = "entity.patronymic")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "historyOrderIds", source = "entity.historyOrderIds")
    UserResponseDto map(User entity);

    @Mapping(target = "id", source = "entityDto.id")
    @Mapping(target = "firstName", source = "entityDto.firstName")
    @Mapping(target = "secondName", source = "entityDto.secondName")
    @Mapping(target = "patronymic", source = "entityDto.patronymic")
    @Mapping(target = "email", source = "entityDto.email")
    User map(UserUpdateDto entityDto);

    List<UserResponseDto> map(List<User> entities);
}
