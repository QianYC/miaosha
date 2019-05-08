package nju.ycqian.stockservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import nju.ycqian.stockservice.dao.GoodsDao;
import nju.ycqian.stockservice.entity.Goods;
import nju.ycqian.stockservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<Goods> listGoods() {
        return goodsDao.findAll();
    }

    @Override
    public Goods goodsDetail(long id) {
        Optional<Goods> optionalGoods = goodsDao.findById(id);
        return optionalGoods.isPresent() ? optionalGoods.get() : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized JSONObject reduceGoodsAmount(long id, int amount) {
        JSONObject object = new JSONObject();
        Goods goods = goodsDetail(id);
        if (goods != null) {
            if (goods.getAmount() >= amount) {
                object.put("success", true);
                goods.setAmount(goods.getAmount() - amount);
                goodsDao.save(goods);
            } else {
                object.put("success", false);
                object.put("msg", String.format("Goods id:%d not enough", id));
            }
        } else {
            object.put("success", false);
            object.put("msg", String.format("Goods id:%d not found", id));
        }
        return object;
    }

    @Override
    public JSONObject increaseGoodsAmount(long id, int amount) {
        return reduceGoodsAmount(id, -amount);
    }
}
