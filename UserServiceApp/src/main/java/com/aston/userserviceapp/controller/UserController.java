package com.aston.userserviceapp.controller;

import com.aston.userserviceapp.dto.entity.user.UserRequestDto;
import com.aston.userserviceapp.dto.entity.user.UserResponseDto;
import com.aston.userserviceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findAllUsers());
    }

    @SneakyThrows
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findUserById(userId));
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<UserResponseDto> insertUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.createUser(userRequestDto));
    }
}
