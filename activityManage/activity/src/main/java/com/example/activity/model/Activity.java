package com.example.activity.model;

import lombok.Data;

import java.util.List;

@Data
public class Activity {
    private Integer id;               //活动ID

    private String organization;      //所属党支部

    private String theme;             //活动主题

    private String cover;             //封面

    private String summary;           //摘要

    private String startTime;         //活动开始时间

    private String endTime;           //活动结束时间

    private String location;          //活动地点

    private String registerStartTime; //报名开始时间

    private String registerEndTime;   //报名结束时间

    private Integer quota;            //名额

    private String description;       //活动描述

    private String demand;            //活动要求

    //private String range;         //可见范围

    private Integer registerNumber;   //报名人数

    private Integer commentNumber;    //评论数

    private Integer likeNumber;       //点赞数

    private Integer status;           //活动状态
}