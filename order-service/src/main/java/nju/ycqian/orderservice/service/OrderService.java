package nju.ycqian.orderservice.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    JSONObject getAllOrders(String userId);

    JSONObject getOrderById(long orderId);

    long getRecentOrderId(String userId, int goodId);

    void saveOrder(String userId, int goodId, double price);

}
