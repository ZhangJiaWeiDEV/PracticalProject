package com.example.activity.controller;

import com.example.activity.model.Activity;
import com.example.activity.service.ActivityServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class ActivityController {
    @Resource
    private ActivityServiceImpl activityService;

    @CrossOrigin
    @PostMapping("/insertActivity")
    public Integer insertActivity(@RequestBody Activity activity){
        return activityService.insert(activity);
    }

    @CrossOrigin
    @GetMapping("/getActivity/{theme}")
    public Activity getActivity(@PathVariable String theme){
        return activityService.getActivity(theme);
    }

    @CrossOrigin
    @GetMapping("/getAllActivity")
    public List<Activity> getAllActivity(){
        return activityService.getALLActivity();
    }

    @CrossOrigin
    @PutMapping("/cancel/{id}")
    public Integer cancel(@PathVariable Integer id){
        return activityService.cancel(id);
    }

    @CrossOrigin
    @PutMapping("/updateActivity")
    public Integer update(@RequestBody Activity activity){
        return activityService.update(activity);
    }
}
