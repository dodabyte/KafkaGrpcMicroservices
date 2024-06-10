package com.aston.userserviceapp.dto.entity.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
public class UserResponseDto {
    private long id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String email;
    private List<Long> historyOrderIds;
}