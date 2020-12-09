package com.example.activity.service;

import com.example.activity.dao.ActivityDao;
import com.example.activity.dao.RegisterDao;
import com.example.activity.dao.UserDao;
import com.example.activity.model.Activity;
import com.example.activity.model.Register;
import com.example.activity.model.User;
import com.example.activity.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    @Autowired
    private RegisterDao registerDao;

    @Resource
    @Autowired
    private ActivityDao activityDao;

    @Override
    public Integer register(Register register){
        if (registerDao.insert(register) == 1) {
            activityDao.addRegisterNumber(register.getActivityId());
            return Status.REQUEST_SUCCESS;
        }
        return Status.REQUEST_FAILED;
    }

    @Override
    public List<Register> getRegisterByActivity(Integer activityId){
        return registerDao.getRegisterByActivity(activityId);
    }

    @Override
    public List<Register> getRegisterByUser(Integer userId){
        return registerDao.getRegisterByUser(userId);
    }

    @Override
    public Integer cancelRegister(Register register){
        if (registerDao.delete(register) == 1){
            activityDao.minusRegisterNumber(register.getActivityId());
            return Status.REQUEST_SUCCESS;
        }
        return Status.REQUEST_FAILED;
    }

    @Override
    public  Boolean isRegisterExit(Register register){
        return registerDao.isRegisterExit(register);
    }
}
