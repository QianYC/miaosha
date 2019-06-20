package nju.ycqian.orderservice.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    JSONObject getAllOrders(int userId);

    JSONObject getOrderById(long orderId);

    long getRecentOrderId(int userId, int goodId);

    void saveOrder(int userId, int goodId, double price);

}
