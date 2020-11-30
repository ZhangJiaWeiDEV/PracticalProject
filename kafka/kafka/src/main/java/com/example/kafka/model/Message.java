package com.example.kafka.model;

import lombok.Data;

@Data
public class Message {
    private String time;
    private Integer status;
    private String content;
}
