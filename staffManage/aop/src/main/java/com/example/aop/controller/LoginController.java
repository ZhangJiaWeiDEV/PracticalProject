package com.example.aop.controller;

import com.example.aop.model.Login;
import com.example.aop.model.User;
import com.example.aop.service.LoginServiceImpl;
import com.example.aop.util.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@EnableAutoConfiguration
public class LoginController {
    @Resource
    private LoginServiceImpl loginService;

    @CrossOrigin
    @PostMapping("/login")
    public User login(@RequestBody Login login) {
        return loginService.login(login);
    }
}
