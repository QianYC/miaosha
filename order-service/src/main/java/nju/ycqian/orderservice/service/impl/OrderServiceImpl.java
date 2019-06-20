package nju.ycqian.orderservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import nju.ycqian.orderservice.dao.OrdersRepository;
import nju.ycqian.orderservice.entity.Orders;
import nju.ycqian.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public JSONObject getAllOrders(int userId) {
        return (JSONObject) JSON.toJSON(ordersRepository.findAllByUserId(userId));
    }

    @Override
    public JSONObject getOrderById(long orderId) {
        return (JSONObject) JSON.toJSON(ordersRepository.findById(orderId));
    }

    @Override
    public long getRecentOrderId(int userId, int goodId) {
        Orders order = ordersRepository.findRecentOne(userId, goodId);
        return order == null ? -1 : order.getId();
    }

    @Override
    public void saveOrder(int userId, int goodId, double price) {
        Orders order = new Orders(userId, goodId, price);
        ordersRepository.save(order);
    }
}
