package com.example.order.controller;

import com.example.order.modal.Goods;
import com.example.order.service.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class GoodsController {
    @Resource
    @Autowired
    private GoodsServiceImpl goodsService;

    @CrossOrigin
    @PostMapping("/insertGoods")
    public Integer insertGoods(@RequestBody Goods goods){
        return goodsService.insert(goods);
    }

    @CrossOrigin
    @GetMapping("/getAllGoods")
    public List<Goods> getAllGoods(){
        return goodsService.getALLGoods();
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable Integer id){
        return goodsService.delete(id);
    }

    @CrossOrigin
    @PutMapping("/updateGoods")
    public Integer update(@RequestBody Goods goods){
        return goodsService.update(goods);
    }

    @CrossOrigin
    @PutMapping("/upGoods/{id}")
    public Integer upGoods(@PathVariable Integer id){
        return goodsService.upGoods(id);
    }

    @CrossOrigin
    @PutMapping("/offGoods/{id}")
    public Integer offGoods(@PathVariable Integer id){
        return goodsService.offGoods(id);
    }

    @CrossOrigin
    @GetMapping("/getGoods/{id}")
    public Goods getGoods(@PathVariable Integer id){
        return goodsService.getGoods(id);
    }

    @CrossOrigin
    @GetMapping("/getGoodsByName/{name}")
    public List<Goods> getGoodsByName(@PathVariable String name){
        return goodsService.getGoodsByName(name);
    }

    @CrossOrigin
    @GetMapping("/getUpGoods")
    public List<Goods> getUpGoods(){
        return goodsService.getUpGoods();
    }
}
