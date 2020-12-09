package com.example.activity.controller;

import com.example.activity.model.Comment;
import com.example.activity.service.CommentServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class CommentController {
    @Resource
    private CommentServiceImpl commentService;

    @CrossOrigin
    @PostMapping("/comment")
    public Integer insertComment(@RequestBody Comment comment){
        return commentService.insert(comment);
    }

    @CrossOrigin
    @GetMapping("/getCommentByActivity/{activityId}")
    public List<Comment> getCommentByActivity(@PathVariable Integer activityId){
        return commentService.getCommentByActivity(activityId);
    }

    @CrossOrigin
    @GetMapping("/getCommentByUser/{userId}")
    public List<Comment> getCommentByUser(@PathVariable Integer userId){
        return commentService.getCommentByUser(userId);
    }

    @CrossOrigin
    @DeleteMapping("/deleteComment")
    public Integer deleteComment(@RequestBody Comment comment){
        return commentService.delete(comment);
    }
}
