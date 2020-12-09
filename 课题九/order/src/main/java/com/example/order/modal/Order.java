package com.example.order.modal;

import lombok.Data;

@Data
public class Order {
    private Integer id;

    private Integer userId;

    private String goodsId;

    private Integer price;

    private String address;

    private String time;

    private Integer status;
}
