package com.example.activity.controller;

import com.example.activity.dao.LikeDao;
import com.example.activity.model.Like;
import com.example.activity.service.LikeServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class LikeController {
    @Resource
    private LikeServiceImpl likeService;

    @CrossOrigin
    @PostMapping("/like")
    public Integer like(@RequestBody Like like){
        return likeService.Like(like);
    }

    @CrossOrigin
    @GetMapping("/getLikeByActivity/{activityId}")
    public List<Like> getLikeByActivity(@PathVariable Integer activityId){
        return likeService.getLikeByActivity(activityId);
    }

    @CrossOrigin
    @GetMapping("/getLikeByUser/{userId}")
    public List<Like> getLikeByUser(@PathVariable Integer userId){
        return likeService.getLikeByUser(userId);
    }

    @CrossOrigin
    @DeleteMapping("/deleteLike")
    public Integer deleteLike(@RequestBody Like like){
        return likeService.deleteLike(like);
    }

    @CrossOrigin
    @PostMapping("/isLikeExit")
    public Boolean isLikeExit(@RequestBody Like like){
        return likeService.isLikeExit(like);
    }
}
