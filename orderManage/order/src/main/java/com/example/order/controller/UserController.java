package com.example.order.controller;

import com.example.order.modal.User;
import com.example.order.service.UserServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @CrossOrigin
    @PostMapping("/insertUser")
    public Integer insert(@RequestBody User user){
        return userService.insert(user);
    }

    @CrossOrigin
    @PostMapping("/login")
    public Integer login(@RequestBody User user){
        return userService.login(user);
    }

    @CrossOrigin
    @PostMapping("/getUserByTelNumber/{telNumber}")
    public User getUserByTelNumber(@PathVariable String telNumber){
        return userService.getUserByTelNumber(telNumber);
    }

    @CrossOrigin
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }
}
