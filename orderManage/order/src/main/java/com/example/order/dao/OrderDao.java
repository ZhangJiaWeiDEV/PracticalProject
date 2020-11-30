package com.example.order.dao;

import com.example.order.modal.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {
    Integer insert(Order order);

    List<Order> findAll();

    List<Order> findByUser(Integer userId);

    Integer cancel(Integer id);

    Integer finish(Integer id);

    List<Order> getOrderById(Integer id);

    List<Order> getOrderByStatus(Integer status);
}
