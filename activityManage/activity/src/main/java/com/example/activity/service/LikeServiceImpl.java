package com.example.activity.service;

import com.example.activity.dao.ActivityDao;
import com.example.activity.dao.LikeDao;
import com.example.activity.model.Like;
import com.example.activity.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Resource
    @Autowired
    private LikeDao likeDao;

    @Resource
    @Autowired
    private ActivityDao activityDao;

    @Override
    public Integer Like(Like like){
        if (likeDao.insert(like) == 1){
            activityDao.addLikeNumber(like.getActivityId());
            return Status.REQUEST_SUCCESS;
        }
        return Status.REQUEST_FAILED;
    }

    @Override
    public List<Like> getLikeByActivity(Integer id){
        return likeDao.getLikeByActivity(id);
    }

    @Override
    public List<Like> getLikeByUser(Integer id){
        return likeDao.getLikeByUser(id);
    }

    @Override
    public Integer deleteLike(Like like){
        if (likeDao.delete(like) == 1){
            activityDao.minusLikeNumber(like.getActivityId());
            return Status.REQUEST_SUCCESS;
        }
        return Status.REQUEST_FAILED;
    }

    @Override
    public Boolean isLikeExit(Like like){
        return likeDao.isLikeExit(like);
    }
}
