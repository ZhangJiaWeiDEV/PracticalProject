package com.example.activity.controller;

import com.example.activity.model.Register;
import com.example.activity.service.RegisterServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class RegisterController {
    @Resource
    private RegisterServiceImpl registerService;

    @CrossOrigin
    @PostMapping("/register")
    public Integer register(@RequestBody Register register){
        return registerService.register(register);
    }

    @CrossOrigin
    @GetMapping("/getRegisterByActivity/{id}")
    public List<Register> getRegisterByActivity(@PathVariable Integer id){
        return registerService.getRegisterByActivity(id);
    }

    @CrossOrigin
    @GetMapping("getRegisterByUser/{id}")
    public List<Register> getRegisterByUser(@PathVariable Integer id){
        return registerService.getRegisterByUser(id);
    }

    @CrossOrigin
    @DeleteMapping("/cancelRegister")
    public Integer cancelRegister(@RequestBody Register register){
        return registerService.cancelRegister(register);
    }

    @CrossOrigin
    @PostMapping("/isRegisterExit")
    public Boolean isRegisterExit(@RequestBody Register register){
        return registerService.isRegisterExit(register);
    }
}
