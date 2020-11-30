package com.example.activity.service;

import com.example.activity.model.Activity;
import com.example.activity.model.User;

import java.util.List;

public interface UserService {
    Integer insert(User user);

    Integer login(User user);

    User getUserByTelNumber(String telNumber);
}
