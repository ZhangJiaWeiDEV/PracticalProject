package com.example.activity.service;

import com.example.activity.model.Register;

import java.util.List;

public interface RegisterService {
    Integer register(Register register);

    List<Register> getRegisterByActivity(Integer activityId);

    List<Register> getRegisterByUser(Integer userId);

    Integer cancelRegister(Register register);

    Boolean isRegisterExit(Register register);
}
