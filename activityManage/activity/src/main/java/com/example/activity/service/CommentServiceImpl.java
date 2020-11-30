package com.example.activity.service;

import com.example.activity.dao.ActivityDao;
import com.example.activity.dao.CommentDao;
import com.example.activity.model.Comment;
import com.example.activity.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Resource
    @Autowired
    private CommentDao commentDao;

    @Resource
    @Autowired
    private ActivityDao activityDao;

    @Override
    public Integer insert(Comment comment){
        if (commentDao.insert(comment) == 1){
            activityDao.addCommentNumber(comment.getActivityId());
            return Status.REQUEST_SUCCESS;
        }
        return Status.REQUEST_FAILED;
    }

    @Override
    public List<Comment> getCommentByActivity(Integer activityId){
        return commentDao.getCommentByActivity(activityId);
    }

    @Override
    public List<Comment> getCommentByUser(Integer userId){
        return commentDao.getCommentByUser(userId);
    }

    @Override
    public Integer delete(Comment comment){
        if (commentDao.delete(comment.getId()) == 1){
            activityDao.minusConmmentNumber(comment.getActivityId());
            return Status.REQUEST_SUCCESS;
        }
        return Status.REQUEST_FAILED;
    }
}
