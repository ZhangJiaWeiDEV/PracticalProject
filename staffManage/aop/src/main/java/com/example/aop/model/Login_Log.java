package com.example.aop.model;

import lombok.Data;

@Data
public class Login_Log {
    private String date;     //登录日志：登录时间
    private Integer type;    //登录日志：登录状态（详见util/Status）
    private String account;  //登录日志：登录账户
}
