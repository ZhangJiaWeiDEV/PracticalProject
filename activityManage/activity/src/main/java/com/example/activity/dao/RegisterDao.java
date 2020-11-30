package com.example.activity.dao;

import com.example.activity.model.Register;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisterDao {
    Integer insert(Register register);

    Integer delete(Register register);

    List<Register> getRegisterByUser(Integer id);

    List<Register> getRegisterByActivity(Integer id);

    Boolean isRegisterExit(Register register);
}
