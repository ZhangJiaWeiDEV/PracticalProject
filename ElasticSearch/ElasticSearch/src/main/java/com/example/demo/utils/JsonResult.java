package com.example.demo.utils;

public class JsonResult {
    private Integer code;

    private String msg;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static JsonResult success(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setMsg("执行成功");
        return jsonResult;
    }

    public static JsonResult failed(String msg){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(1);
        jsonResult.setMsg(msg);
        return jsonResult;
    }

    public static JsonResult success(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setMsg("执行成功");
        jsonResult.setData(data);
        return jsonResult;
    }
}
