package com.example.aop.service;

import com.example.aop.model.User;

import java.util.List;

public interface UserService {
    Integer createUser(User user);

    List<User> findAllUser();

    List<User> findUserByName(String name, boolean isClear);

    List<User> findUserByLoginName(String login_name, boolean isClear);

    List<User> findUserByTelNumber(String tel_number, boolean isClear);

    List<User> findUserByType(Integer type);

    Integer updateUser(User user);

    Integer deleteUser(Integer id);
}
