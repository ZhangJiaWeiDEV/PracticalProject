package com.example.order.service;

import com.example.order.dao.OrderDao;
import com.example.order.modal.DailyStatistics;
import com.example.order.modal.Order;
import com.example.order.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    @Autowired
    private OrderDao orderDao;

    @Override
    public Integer insert(Order order){
        return orderDao.insert(order)  == 1? Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public List<Order> getALLOrder(){
        return orderDao.findAll();
    }

    @Override
    public List<Order> getOrderByUser(Integer userId){
        return orderDao.findByUser(userId);
    }

    @Override
    public Integer cancel(Integer id){
        return orderDao.cancel(id) == 1? Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public Integer finish(Integer id){
        return orderDao.finish(id) == 1? Status.REQUEST_SUCCESS:Status.REQUEST_FAILED;
    }

    @Override
    public List<Order> getOrderById(Integer id){
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> getOrderByStatus(Integer status){
        return orderDao.getOrderByStatus(status);
    }

    @Override
    public List<DailyStatistics> getDailyStatistics(String startTime, String endTime) {
        List<Order> orderList = orderDao.findAll();
        List<DailyStatistics> dailyStatisticsList = new ArrayList<>();
        for (Order anOrderList : orderList) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            try {
                Date date = sdf.parse(anOrderList.getTime());
                if (date.after(sdf.parse(startTime)) && date.before(sdf.parse(endTime))) {
                    int j;
                    for (j = 0; j < dailyStatisticsList.size(); j++) {
                        if (dailyStatisticsList.get(j).getDate().equals((date.getYear()+1900) + "-" + (date.getMonth()+1) + "-" + date.getDate())) {
                            dailyStatisticsList.get(j).setCount(dailyStatisticsList.get(j).getCount() + 1);
                            dailyStatisticsList.get(j).setAmount(dailyStatisticsList.get(j).getAmount() + anOrderList.getPrice());
                            break;
                        }
                    }
                    if (j == dailyStatisticsList.size()){
                        DailyStatistics dailyStatistics = new DailyStatistics();
                        dailyStatistics.setDate((date.getYear()+1900) + "-" + (date.getMonth()+1) + "-" + date.getDate());
                        dailyStatistics.setAmount(anOrderList.getPrice());
                        dailyStatistics.setCount(1);
                        dailyStatisticsList.add(dailyStatistics);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dailyStatisticsList;
    }
}
