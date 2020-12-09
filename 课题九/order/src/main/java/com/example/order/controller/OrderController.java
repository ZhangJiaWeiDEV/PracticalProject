package com.example.order.controller;

import com.example.order.modal.DailyStatistics;
import com.example.order.modal.Order;
import com.example.order.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class OrderController {
    @Resource
    @Autowired
    private OrderServiceImpl orderService;

    @CrossOrigin
    @PostMapping("/insertOrder")
    public Integer insertOrder(@RequestBody Order order){
        return orderService.insert(order);
    }

    @CrossOrigin
    @GetMapping("/getOrderByUser/{id}")
    public List<Order> getOrderByUser(@PathVariable Integer id){
        return orderService.getOrderByUser(id);
    }

    @CrossOrigin
    @GetMapping("/getAllOrder")
    public List<Order> getAllOrder(){
        return orderService.getALLOrder();
    }

    @CrossOrigin
    @PutMapping("/cancel/{id}")
    public Integer cancel(@PathVariable Integer id){
        return orderService.cancel(id);
    }

    @CrossOrigin
    @PutMapping("/finish/{id}")
    public Integer finish(@PathVariable Integer id){
        return orderService.finish(id);
    }

    @CrossOrigin
    @GetMapping("/getOrderById/{id}")
    public List<Order> getOrderById(@PathVariable Integer id){
        return orderService.getOrderById(id);
    }

    @CrossOrigin
    @GetMapping("/getOrderByStatus/{status}")
    public List<Order> getOrderByStatus(@PathVariable Integer status){
        return orderService.getOrderByStatus(status);
    }

    @CrossOrigin
    @GetMapping("/getDailyStatistics/{startTime}&{endTime}")
    public List<DailyStatistics> getDailyStatistics(@PathVariable String startTime, @PathVariable String endTime){
        return orderService.getDailyStatistics(startTime, endTime);
    }
}
