package com.example.activity.model;

import lombok.Data;

@Data
public class Comment {
    private Integer id;         //评论记录ID

    private Integer userId;     //评论者ID

    private String userName;    //评论者名称

    private Integer activityId; //评论活动ID

    private String commentTime; //评论时间

    private String content;     //评论内容
}
