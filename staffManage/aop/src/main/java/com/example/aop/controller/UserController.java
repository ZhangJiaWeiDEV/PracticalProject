package com.example.aop.controller;

import com.example.aop.model.Log;
import com.example.aop.model.User;
import com.example.aop.service.UserServiceImpl;
import com.example.aop.util.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@EnableAutoConfiguration
public class UserController {
    @Resource
    private UserServiceImpl userService;

    private String sign = "!#))#*)@&#*";   //日志标记字段，用于筛选日志记录

    @CrossOrigin
    @PostMapping("/user")
    public Integer insertUser(@RequestBody User user) {
        Integer result = userService.createUser(user);
        if (result == Status.SUCCESS_REQUEST){
            log.info(sign + " type = 'POST', url = '/user', requestParam = " + user + ", responseParam = " + result);
        } else {
            log.error(sign + " type = 'POST', url = '/user', requestParam = " + user + ", responseParam = " + result);
        }
        return result;
    }

    @CrossOrigin
    @DeleteMapping("/user/{id}")
    public Integer deleteUser(@PathVariable Integer id) {
        Integer result = userService.deleteUser(id);
        if (result == Status.SUCCESS_REQUEST){
            log.info(sign + " type = 'DELETE', url = '/user/{id}', requestParam = " + id + ", responseParam = " + result);
        } else if (result == Status.FAILED_REQUEST){
            log.error(sign + " type = 'DELETE', url = '/user/{id}', requestParam = " + id + ", responseParam = " + result);
        }
        return result;
    }

    @CrossOrigin
    @PutMapping("/user")
    public Integer updateUser(@RequestBody User user) {
        Integer result = userService.updateUser(user);
        if (result == Status.SUCCESS_REQUEST){
            log.info(sign + " type = 'PUT', url = '/user', requestParam = " + user + ", responseParam = " + result);
        } else {
            log.error(sign + " type = 'PUT', url = '/user', requestParam = " + user + ", responseParam = " + result);
        }
        return result;
    }

    @CrossOrigin
    @GetMapping("/user")
    public List<User> getUser() {
        List<User> userList = userService.findAllUser();
        for (int i=0;i < userList.size();i++) {
            userList.get(i).setPassword("*******");
        }
        log.info(sign + " type = 'GET', url = '/user', requestParam = " + ", responseParam = " + userList);
        return userList;
    }

    @CrossOrigin
    @GetMapping("/userByName/{name}")
    public List<User> getUserByName(@PathVariable String name) {
        List<User> userList = userService.findUserByName(name, false);
        log.info(sign + "type = 'GET', url = '/userByName/{name}', requestParam = " + name + ", responseParam = " + userList);
        return userList;
    }

    @CrossOrigin
    @GetMapping("/userByLoginName/{login_name}")
    public List<User> getUserByLoginName(@PathVariable String login_name) {
        List<User> userList = userService.findUserByLoginName(login_name, false);
        log.info(sign + "type = 'GET', url = '/userByLoginName/{login_name}', requestParam = " + login_name + ", responseParam = " + userList);
        return userList;
    }

    @CrossOrigin
    @GetMapping("/userByTelNumber/{tel_number}")
    public List<User> getUserByTelNumber(@PathVariable String tel_number) {
        List<User> userList = userService.findUserByTelNumber(tel_number, false);
        log.info("url = '/userByTelNumber/{tel_number}', type = 'GET' param = " + tel_number + " result = " + userList);
        return userList;
    }

    @CrossOrigin
    @GetMapping("/userByType/{type}")
    public List<User> getUserByType(@PathVariable Integer type) {
        List<User> userList = userService.findUserByType(type);
        log.info("url = '/userByType/{type}', type = 'GET' param = " + type + " result = " + userList);
        return  userList;
    }
}
