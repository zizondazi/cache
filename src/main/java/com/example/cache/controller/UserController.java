package com.example.cache.controller;

import com.example.cache.domain.entitiy.RedisHashUser;
import com.example.cache.domain.entitiy.User;
import com.example.cache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getCacheUser(id);
    }

    @GetMapping("/redishash-user/{id}")
    public RedisHashUser getRedisHashUser(@PathVariable Long id) {
        return userService.getRedisHashUser(id);
    }
}
