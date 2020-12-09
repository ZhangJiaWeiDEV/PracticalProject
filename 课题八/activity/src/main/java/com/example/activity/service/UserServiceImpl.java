package com.example.activity.service;

import com.example.activity.dao.UserDao;
import com.example.activity.model.User;
import com.example.activity.util.Status;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public Integer insert(User user){
        if (user.getTelNumber().length() != 11)
            return Status.TELNUMBER_LENGTH_ERROR;
        if (!isPhone(user.getTelNumber()))
            return Status.TELNUMBER_NOT_EXIT;
        if (userDao.isTelNumberExit(user.getTelNumber()))
            return Status.TELNUMBER_SIGN_UP;
        return userDao.insert(user) == 1?Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public Integer login(User user){
        System.out.println(user.getTelNumber());
        if (!userDao.isTelNumberExit(user.getTelNumber()))
            return Status.ACCOUNT_NOT_EXIT;
        if (!userDao.login(user.getTelNumber()).get(0).getPassword().equals(user.getPassword()))
            return Status.PASSWORD_ERROR;
        return Status.SUCCESS_LOGIN;
    }

    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[1]|[4-9])|(15([0-3]|[5-9]))|(16[2]|[5-7])|(17[0-3]|[5-8])|(18[0-9])|(19[1|8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    @Override
    public User getUserByTelNumber(String telNumber){
        return userDao.login(telNumber).get(0);
    }
}
