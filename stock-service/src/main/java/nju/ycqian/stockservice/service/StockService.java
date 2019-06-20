package nju.ycqian.stockservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import nju.ycqian.stockservice.entity.primary.Goods;

import java.util.List;

public interface StockService {

    JSONArray getAllGoods();

    JSONArray getKillGoods();

    JSONObject getGoodsDetail(Integer id);

    int kill(String uid, Integer gid);
}
