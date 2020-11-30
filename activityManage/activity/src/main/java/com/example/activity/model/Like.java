package com.example.activity.model;

import lombok.Data;

@Data
public class Like {
    private Integer id;         //点赞记录ID
    private Integer userId;     //点赞者ID
    private String userName;    //点赞者名称
    private Integer activityId; //点赞活动ID
    private String likeTime;    //点赞时间
}
