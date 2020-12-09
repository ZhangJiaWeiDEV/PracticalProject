package com.example.demo.service;

import com.example.demo.model.ESObject;
import com.example.demo.utils.JsonResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;

public interface ESObjectService {
    long count();

    Iterator<ESObject> getScenarios(String scenarioCode);

    JsonResult queryScenarios(@RequestParam(required = false) String scenarioCode);
}
