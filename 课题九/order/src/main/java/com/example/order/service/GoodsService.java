package com.example.order.service;

import com.example.order.modal.Goods;

import java.util.List;

public interface GoodsService {

    Integer insert(Goods goods);

    List<Goods> getALLGoods();

    Integer delete(Integer id);

    Integer update(Goods goods);

    Integer upGoods(Integer id);

    Integer offGoods(Integer id);

    Goods getGoods(Integer id);

    List<Goods> getGoodsByName(String name);

    List<Goods> getUpGoods();
}
