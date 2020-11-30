package com.example.aop.controller;

import com.example.aop.model.Log;
import com.example.aop.model.Login_Log;
import com.example.aop.util.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@EnableAutoConfiguration
public class LogController {

    @CrossOrigin
    @GetMapping("/getLoginLog")
    public List<Login_Log> getLoginLog() throws Exception {
        Resource resource = new FileSystemResource("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/aop/log/login.txt");
        InputStream inputStream = resource.getInputStream();
        byte bytes[] = new byte[(int) resource.contentLength()];
        inputStream.read(bytes);
        String[] string = (new String(bytes)).split("\\r?\\n");

        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(string));
        ArrayList<Login_Log> string_List = new ArrayList<>();
        for (String aStringArrayList : stringArrayList) {
            Login_Log login_log = new Login_Log();
            login_log.setDate(aStringArrayList.substring(0, 20));
            if (aStringArrayList.contains("success")) {
                login_log.setType(Status.SUCCESS_LOGIN);
                login_log.setAccount(aStringArrayList.substring(aStringArrayList.indexOf("account ") + 8, aStringArrayList.indexOf(" successful")));
            } else if (aStringArrayList.contains("password")) {
                login_log.setType(Status.PASSWORD_ERROR);
                login_log.setAccount(aStringArrayList.substring(aStringArrayList.indexOf("account ") + 8, aStringArrayList.indexOf(" password")));
            } else if (aStringArrayList.contains("exist")) {
                login_log.setType(Status.ACCOUNT_NOT_EXIT);
                login_log.setAccount(aStringArrayList.substring(aStringArrayList.indexOf("account ") + 8, aStringArrayList.indexOf(" is")));
            }
            string_List.add(login_log);
        }
        Collections.reverse(string_List);
        return string_List;
    }

    @CrossOrigin
    @GetMapping("/getLog/{date}")
    public List<Log> getLog(@PathVariable String date) throws Exception {
        Resource resource = new FileSystemResource("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/aop/log/spring-log." + date + ".0.log");
        InputStream inputStream = resource.getInputStream();
        byte bytes[] = new byte[(int) resource.contentLength()];
        inputStream.read(bytes);
        String[] string = (new String(bytes)).split("\\r?\\n");

        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(string));
        ArrayList<Log> string_List = new ArrayList<>();
        for (String aStringArrayList : stringArrayList) {
            if (aStringArrayList.contains("!#))#*)@&#*")) {
                Log new_log = new Log();
                new_log.setType(aStringArrayList.substring(aStringArrayList.indexOf("type = '") + 8, aStringArrayList.indexOf("', url")));
                new_log.setUrl(aStringArrayList.substring(aStringArrayList.indexOf("url = '") + 7, aStringArrayList.indexOf("', requestParam")));
                new_log.setDate(aStringArrayList.substring(0, 8));
                new_log.setRequestParam(aStringArrayList.substring(aStringArrayList.indexOf("requestParam = ") + 15, aStringArrayList.indexOf(", responseParam")));
                new_log.setResponseParam(aStringArrayList.substring(aStringArrayList.indexOf("responseParam = ") + 16, aStringArrayList.length()));

                string_List.add(new_log);
            }
        }
        Collections.reverse(string_List);
        return string_List;
    }
}
