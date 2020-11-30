package com.example.kafka.dao;

import com.example.kafka.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {
    Integer insert(Message message);

    List<Message> getAll();
}
