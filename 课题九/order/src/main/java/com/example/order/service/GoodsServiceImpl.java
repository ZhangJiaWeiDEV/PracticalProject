package com.example.order.service;

import com.example.order.dao.GoodsDao;
import com.example.order.modal.Goods;
import com.example.order.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Integer insert(Goods goods){
        if (goodsDao.isGoodsNameExit(goods.getName()))
            return Status.THEME_EXIT;
        return goodsDao.insert(goods) == 1?Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public List<Goods> getALLGoods(){
        return goodsDao.findAll();
    }

    @Override
    public Integer delete(Integer id){
        return goodsDao.delete(id) == 1?Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public Integer update(Goods goods){
        return goodsDao.update(goods) == 1?Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public Integer upGoods(Integer id){
        return goodsDao.upGoods(id) == 1?Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public Integer offGoods(Integer id){
        return goodsDao.offGoods(id) == 1?Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }
    @Override
    public Goods getGoods(Integer id){
        return goodsDao.getGoods(id);
    }

    @Override
    public List<Goods> getGoodsByName(String name){
        return goodsDao.getGoodsByName("%" + name + "%");
    }

    @Override
    public List<Goods> getUpGoods(){
        return goodsDao.getUpGoods();
    }
}
