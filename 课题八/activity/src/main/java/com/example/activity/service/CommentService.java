package com.example.activity.service;

import com.example.activity.model.Comment;

import java.util.List;

public interface CommentService {
    Integer insert(Comment comment);

    List<Comment> getCommentByActivity(Integer activityId);

    List<Comment> getCommentByUser(Integer userId);

    Integer delete(Comment comment);
}
