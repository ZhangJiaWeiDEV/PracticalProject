package com.example.aop.dao;

import com.example.aop.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao{
    Integer insert(User user);

    Integer delete(Integer id);

    Integer update(User user);

    List<User> findAll();

    List<User> findUserByName(String name);

    List<User> findUserByLoginName(String login_name);

    List<User> findUserByTelNumber(String tel_number);

    List<User> findUserByType(Integer type);

    Boolean isTelNumberExit(String tel_number);

    Boolean isLoginNameExit(String login_name);
}