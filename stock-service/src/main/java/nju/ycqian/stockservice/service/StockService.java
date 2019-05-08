package nju.ycqian.stockservice.service;

import com.alibaba.fastjson.JSONObject;
import nju.ycqian.stockservice.entity.Goods;

import java.util.List;

public interface StockService {
    List<Goods> listGoods();

    Goods goodsDetail(long id);

    JSONObject reduceGoodsAmount(long id, int amount);

    JSONObject increaseGoodsAmount(long id, int amount);
}
