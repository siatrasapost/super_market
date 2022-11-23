package com.sapostolos.super_market.controllers;

import com.sapostolos.super_market.dtos.RegisterUserDto;
import com.sapostolos.super_market.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/")
    public void createUser(@RequestBody RegisterUserDto newUser){
        userService.createUser(newUser);
    }
}
