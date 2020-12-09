package com.example.demo.controller;

import com.example.demo.model.ESObject;
import com.example.demo.service.ESObjectServiceImpl;
import com.example.demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Iterator;

@RestController
@EnableAutoConfiguration
public class ESObjectController {
    @Resource
    @Autowired
    private ESObjectServiceImpl esObjectService;

    @CrossOrigin
    @RequestMapping("/count")
    public long count() {
        return esObjectService.count();
    }

    @CrossOrigin
    @RequestMapping("/getScenarios/{scenarioCode}")
    public Iterator<ESObject> getScenarios(@PathVariable String scenarioCode) {

        return esObjectService.getScenarios(scenarioCode);
    }

    @CrossOrigin
    @GetMapping("/AllScenarios")
    public JsonResult queryScenarios(@RequestParam(required = false) String scenarioCode) {
        return esObjectService.queryScenarios(scenarioCode);
    }
}
