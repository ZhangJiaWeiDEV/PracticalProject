package com.example.activity.model;

import lombok.Data;

@Data
public class Register {
    private Integer id;            //报名记录ID

    private Integer userId;        //报名者ID

    private Integer activityId;    //报名活动ID

    private String avatar;         //报名者头像

    private String name;           //报名者姓名

    private String organization;//报名者所属党支部

    private String registerTime;   //报名时间
}
