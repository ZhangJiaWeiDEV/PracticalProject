package com.example.activity.dao;

import com.example.activity.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    Integer insert(Comment comment);

    Integer delete(Integer id);

    List<Comment> getCommentByUser(Integer id);

    List<Comment> getCommentByActivity(Integer id);
}
