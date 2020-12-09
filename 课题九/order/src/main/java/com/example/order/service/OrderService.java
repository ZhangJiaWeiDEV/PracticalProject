package com.example.order.service;

import com.example.order.modal.DailyStatistics;
import com.example.order.modal.Order;

import java.util.List;

public interface OrderService {
    Integer insert(Order order);

    List<Order> getALLOrder();

    List<Order> getOrderByUser(Integer userId);

    Integer cancel(Integer id);

    Integer finish(Integer id);

    List<Order> getOrderById(Integer id);

    List<Order> getOrderByStatus(Integer status);

    List<DailyStatistics> getDailyStatistics(String startTime, String endTime);
}
