package com.example.activity.service;

import com.example.activity.model.Like;

import java.util.List;

public interface LikeService {
    Integer Like(Like like);

    List<Like> getLikeByActivity(Integer activityId);

    List<Like> getLikeByUser(Integer userId);

    Integer deleteLike(Like like);

    Boolean isLikeExit(Like like);
}
