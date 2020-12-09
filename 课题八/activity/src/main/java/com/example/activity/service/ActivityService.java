package com.example.activity.service;

import com.example.activity.model.Activity;

import java.util.List;

public interface ActivityService {
    Integer insert(Activity activity);

    Activity getActivity(String theme);

    List<Activity> getALLActivity();

    Integer cancel(Integer id);

    Integer update(Activity activity);
}
