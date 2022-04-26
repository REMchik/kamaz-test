package com.example.kamaz.demo.controller;

import com.example.kamaz.demo.dto.UserDto;
import com.example.kamaz.demo.model.User;
import com.example.kamaz.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kamaz/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getList() {
        return userService.getList()
                .stream().map(User::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public int create(@RequestBody UserDto userDto) {
        return userService.create(userDto.toModel());
    }

    @PatchMapping
    public void update(@RequestBody UserDto userDto) {
        userService.update(userDto.toModel());
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable int userId) {
        userService.delete(userId);
    }
}

