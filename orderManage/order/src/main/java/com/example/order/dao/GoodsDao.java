package com.example.order.dao;

import com.example.order.modal.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsDao {

    Integer insert(Goods goods);

    Integer delete(Integer id);

    Integer update(Goods goods);

    List<Goods> findAll();

    boolean isGoodsNameExit(String name);

    Integer upGoods(Integer id);

    Integer offGoods(Integer id);

    Goods getGoods(Integer id);

    List<Goods> getGoodsByName(String name);

    List<Goods> getUpGoods();
}
