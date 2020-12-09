package com.example.activity.model;

import lombok.Data;

@Data
public class User {
    private Integer id;             //用户ID

    private String telNumber;       //手机号码

    private String name;            //姓名

    private String password;        //密码

    private String avatar;          //头像

    private String organization;    //所属党支部

    private String anonymity;       //昵称
}
