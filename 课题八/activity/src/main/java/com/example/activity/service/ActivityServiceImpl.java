package com.example.activity.service;

import com.example.activity.dao.ActivityDao;
import com.example.activity.model.Activity;
import com.example.activity.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{
    @Resource
    @Autowired
    private ActivityDao activityDao;

    @Override
    public Integer insert(Activity activity){
        if (activityDao.isThemeExit(activity.getTheme()))
            return Status.THEME_EXIT;
        activity.setRegisterNumber(0);
        activity.setCommentNumber(0);
        activity.setLikeNumber(0);
        activity.setStatus(0);
        return activityDao.insert(activity) == 1?Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public Activity getActivity(String theme){
        return activityDao.getActivity(theme);
    }

    @Override
    public List<Activity> getALLActivity(){
        return activityDao.findAll();
    }

    @Override
    public Integer cancel(Integer id){
        return activityDao.cancel(id) == 1?Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public Integer update(Activity activity){
        return activityDao.update(activity) == 1?Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }
}
