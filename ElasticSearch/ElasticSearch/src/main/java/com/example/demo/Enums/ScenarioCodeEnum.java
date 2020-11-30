package com.example.demo.Enums;

public enum ScenarioCodeEnum {
    CODE_10111010001("4G业务新装受理开通","10111010001"),
    CODE_11111014001("流量阀值提醒","11111014001"),
    CODE_11111014002("欠费停机提醒","11111014002"),
    CODE_10111011001("移动用户集团直充","10111011001"),
    CODE_10111010002("4G用户可选包","10111010002"),
    CODE_11111110001("宽带新品新装","11111110001"),
    CODE_11111210001("ITV业务新装","11111210001"),
    CODE_11111010004("翼支付功能开通","11111010004");


    private String msg;

    private String value;

    ScenarioCodeEnum(String msg, String value) {
        this.msg = msg;
        this.value = value;
    }

    public static boolean contains(String value) {
        ScenarioCodeEnum[] values = ScenarioCodeEnum.values();
        for (ScenarioCodeEnum scenarioCodeEnum : values) {

            if (scenarioCodeEnum.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public static String getMsgForValue(String value) {
        ScenarioCodeEnum[] values = ScenarioCodeEnum.values();
        for (ScenarioCodeEnum scenarioCodeEnum : values) {

            if (scenarioCodeEnum.getValue().equals(value)) {
                return scenarioCodeEnum.getMsg();
            }
        }

        return value;
    }

    public static String getValueForMsg(String msg) {
        ScenarioCodeEnum[] values = ScenarioCodeEnum.values();
        for (ScenarioCodeEnum scenarioCodeEnum : values) {

            if (scenarioCodeEnum.getMsg().equals(msg)) {
                return scenarioCodeEnum.getValue();
            }
        }

        return msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
