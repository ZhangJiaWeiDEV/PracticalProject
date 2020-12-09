package com.example.order.modal;

import lombok.Data;

@Data
public class Goods {
    private Integer id;

    private String name;

    private Integer price;

    private String picture;

    private String description;

    private Integer status;
}
