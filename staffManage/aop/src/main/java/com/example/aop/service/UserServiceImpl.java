package com.example.aop.service;

import com.example.aop.dao.UserDao;
import com.example.aop.model.User;
import com.example.aop.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    @Autowired
    private UserDao userDao;

    @Override
    public Integer createUser(User user) {
        if(user.getTel_number().length() != 11)
            return Status.TELNUMBER_LENGTH_ERROR;
        else if (!isPhone(user.getTel_number()))
            return Status.TELNUMBER_NOT_EXIT;
        if (userDao.isTelNumberExit(user.getTel_number()))
            return Status.TELNUMBER_SIGN_UP;
        if (userDao.isLoginNameExit(user.getLogin_name()))
            return Status.LOGIN_NAME_EXIT;
        user.setPassword("123456");
        if (userDao.insert(user) == 1)
            return Status.SUCCESS_REQUEST;
        else
            return Status.FAILED_REQUEST;
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    @Override
    public List<User> findUserByName(String name, boolean isClear) {
        if (!isClear)
            return userDao.findUserByName("%" + name + "%");
        else
            return userDao.findUserByName(name);
    }

    @Override
    public List<User> findUserByLoginName(String login_name, boolean isClear) {
        if (!isClear)
            return userDao.findUserByLoginName("%" + login_name + "%");
        else
            return userDao.findUserByLoginName(login_name);
    }

    @Override
    public List<User> findUserByTelNumber(String tel_number, boolean isClear) {
        if (!isClear)
            return userDao.findUserByTelNumber("%" + tel_number + "%");
        else
            return userDao.findUserByTelNumber(tel_number);
    }

    @Override
    public List<User> findUserByType(Integer type) {
        return userDao.findUserByType(type);
    }

    @Override
    public Integer updateUser(User user) {
        if(user.getTel_number().length() != 11)
            return Status.TELNUMBER_LENGTH_ERROR;
        else if (!isPhone(user.getTel_number()))
            return Status.TELNUMBER_NOT_EXIT;
        if (userDao.isTelNumberExit(user.getTel_number())) {
            List<User> userList = userDao.findUserByTelNumber(user.getTel_number());
            if (!userList.get(0).getId().equals(user.getId()))
                return Status.TELNUMBER_SIGN_UP;
        }
        if (userDao.isLoginNameExit(user.getLogin_name())) {
            List<User> userList = userDao.findUserByLoginName(user.getLogin_name());
            if (!userList.get(0).getId().equals(user.getId()))
                return Status.LOGIN_NAME_EXIT;
        }
        if (userDao.update(user) == 1)
            return Status.SUCCESS_REQUEST;
        else
            return Status.FAILED_REQUEST;
    }

    @Override
    public Integer deleteUser(Integer id) {
        Integer result = userDao.delete(id);
        if (result == 1)
            return Status.SUCCESS_REQUEST;
        else
            return Status.FAILED_REQUEST;
    }

    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[1]|[4-9])|(15([0-3]|[5-9]))|(16[2]|[5-7])|(17[0-3]|[5-8])|(18[0-9])|(19[1|8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
