package com.example.aop.model;

import lombok.Data;

@Data
public class Log {
    private String type;          //接口日志：请求方式
    private String url;           //接口日志：请求url
    private String date;          //接口日志：记录时间
    private String requestParam;  //接口日志：请求参数
    private String responseParam; //接口日志：响应参数
}
