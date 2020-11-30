package com.example.activity.dao;

import com.example.activity.model.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityDao {
    Integer insert(Activity activity);

    Integer cancel(Integer id);

    Integer update(Activity activity);

    List<Activity> findAll();

    Activity getActivity(String theme);

    boolean isThemeExit(String theme);

    Integer addRegisterNumber(Integer id);

    Integer minusRegisterNumber(Integer id);

    Integer addCommentNumber(Integer id);

    Integer minusConmmentNumber(Integer id);

    Integer addLikeNumber(Integer id);

    Integer minusLikeNumber(Integer id);
}
