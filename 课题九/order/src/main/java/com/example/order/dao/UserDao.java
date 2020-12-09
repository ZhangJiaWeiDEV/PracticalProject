package com.example.order.dao;

import com.example.order.modal.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    Integer insert(User user);

    Integer delete(Integer id);

    Integer update(User user);

    boolean isTelNumberExit(String telNumber);

    boolean isAnonymityExit(String anonymity);

    List<User> login(String telNumber);

    User getUserById(Integer id);
}
