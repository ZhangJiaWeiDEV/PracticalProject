package com.example.aop.service;

import com.example.aop.model.Login;
import com.example.aop.model.User;
import com.example.aop.util.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserServiceImpl userService;

    @Override
    public User login(Login login) {
        Writer writer;
        User user = new User();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String date_string = simpleDateFormat.format(date);
        try {
            writer = new FileWriter("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/aop/log/login.txt", true);

            if (userService.findUserByTelNumber(login.getAccount(), true).size() != 0) {
                user = userService.findUserByTelNumber(login.getAccount(), true).get(0);
            } else if (userService.findUserByLoginName(login.getAccount(), true).size() != 0) {
                user = userService.findUserByLoginName(login.getAccount(), true).get(0);
            } else {
                writer.write(date_string + ": error: account " + login.getAccount() + " is not exist\n");
                user.setMark(Status.ACCOUNT_NOT_EXIT);
            }

            if (login.getPassword().equals(user.getPassword())) {
                if (!user.getLogin_name().equals("admin"))
                    writer.write(date_string + ": success: account " + login.getAccount() + " successful login\n");
                user.setMark(Status.SUCCESS_LOGIN);
            } else if (user.getPassword() != null) {
                writer.write(date_string + ": error: account " + login.getAccount() + " password is error\n");
                user.setMark(Status.PASSWORD_ERROR);
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
