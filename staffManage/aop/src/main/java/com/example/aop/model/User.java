package com.example.aop.model;

import lombok.Data;

@Data
public class User {
    private Integer mark = 0;  //用户状态，详见util/Status

    private Integer id;        //用户id

    private String name;       //用户姓名

    private String tel_number; //电话号码（可用作登录账号）

    private Integer type;      //用户类型：0-管理员，1-总经理，2-部门经理，3-职员

    private String login_name; //用户昵称（可用作登录账号）

    private String password;   //密码
}
