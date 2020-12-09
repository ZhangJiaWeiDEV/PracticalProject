package com.example.order.service;

import com.example.order.modal.User;

public interface UserService {
    Integer insert(User user);

    Integer login(User user);

    User getUserByTelNumber(String telNumber);

    User getUserById(Integer id);
}
