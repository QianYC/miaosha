package nju.ycqian.orderservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import nju.ycqian.orderservice.dao.OrdersRespository;
import nju.ycqian.orderservice.entity.Orders;
import nju.ycqian.orderservice.service.OrderService;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRespository ordersRespository;

    @Override
    public JSONObject getAllOrders(int userId) {
        return (JSONObject) JSON.toJSON(ordersRespository.findAllByUserId(userId));
    }

    @Override
    public long getRecentOrderId(int userId, int goodId) {
        Orders order = ordersRespository.findRecentOne(userId, goodId);
        return order == null ? -1 : order.getId();
    }

    @Override
    public void saveOrder(int userId, int goodId, double price) {
        Orders order = new Orders(userId, goodId, price);
        ordersRespository.save(order);
    }
}
