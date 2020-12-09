package com.example.activity.dao;

import com.example.activity.model.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeDao {
    Integer insert(Like like);

    Integer delete(Like like);

    List<Like> getLikeByUser(Integer id);

    List<Like> getLikeByActivity(Integer id);

    Boolean isLikeExit(Like like);
}
