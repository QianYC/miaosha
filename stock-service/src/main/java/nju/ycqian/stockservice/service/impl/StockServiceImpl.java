package nju.ycqian.stockservice.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import nju.ycqian.stockservice.dao.primary.GStockDao;
import nju.ycqian.stockservice.dao.primary.GoodsDao;
import nju.ycqian.stockservice.dao.secondary.KStockDao;
import nju.ycqian.stockservice.dao.secondary.KillGoodsDao;
import nju.ycqian.stockservice.entity.primary.GStock;
import nju.ycqian.stockservice.entity.primary.Goods;
import nju.ycqian.stockservice.entity.secondary.KStock;
import nju.ycqian.stockservice.entity.secondary.KillGoods;
import nju.ycqian.stockservice.service.MyMessageChanel;
import nju.ycqian.stockservice.service.StockService;
import nju.ycqian.stockservice.utils.KillRes;
import nju.ycqian.stockservice.utils.ObjectJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@EnableBinding(MyMessageChanel.class)
public class StockServiceImpl implements StockService {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    GStockDao gStockDao;
    @Autowired
    KillGoodsDao killGoodsDao;
    @Autowired
    KStockDao kStockDao;
    @Autowired
    MyMessageChanel chanel;

    @Override
    public JSONArray getAllGoods() {
        Map<Integer, JSONObject> goodsMap = new HashMap<>();
        //商品基本信息
        goodsDao.findAll().stream().forEach(x -> {
            goodsMap.put(x.getId(), ObjectJsonConverter.parseObject(x));
        });
        //商品库存
        gStockDao.findAll().stream().forEach(x -> {
            ObjectJsonConverter.merge(goodsMap.get(x.getGoodId()), x);
        });
        //秒杀商品基本信息
        killGoodsDao.findAll().stream().forEach(x -> {
            ObjectJsonConverter.merge(goodsMap.get(x.getGoodId()), x);
        });
        //秒杀商品库存
        kStockDao.findAll().stream().forEach(x -> {
            JSONObject object = goodsMap.get(x.getGoodId());
            int amount = object.getInteger("amount");
            object.put("amount", amount + x.getAmount());
        });

        return goodsMap.entrySet()
                .stream()
                .map(x -> x.getValue())
                .collect(JSONArray::new, JSONArray::add, JSONArray::addAll);
    }

    @Override
    public JSONArray getKillGoods() {
        Map<Integer, JSONObject> goodsMap = new HashMap<>();
        //秒杀商品基本信息
        killGoodsDao.findAll().stream()
                .forEach(x -> goodsMap.put(x.getGoodId(), ObjectJsonConverter.parseObject(x)));
        //商品名
        goodsDao.findAll().stream()
                .forEach(x -> {
                    JSONObject object = goodsMap.get(x.getId());
                    if (object != null) {
                        object.put("name", x.getName());
                    }
                });
        //秒杀商品库存
        kStockDao.findAll().stream()
                .forEach(x -> ObjectJsonConverter.merge(goodsMap.get(x.getGoodId()), x));

        return goodsMap.entrySet()
                .stream()
                .map(x -> x.getValue())
                .collect(JSONArray::new, JSONArray::add, JSONArray::addAll);
    }

    @Override
    public JSONObject getGoodsDetail(Integer id) {
        Optional<Goods> goodsOptional = goodsDao.findById(id);
        if (!goodsOptional.isPresent()) return null;
        JSONObject object = ObjectJsonConverter.parseObject(goodsOptional.get());

        Optional<GStock> gStockOptional = gStockDao.findById(id);
        if (!gStockOptional.isPresent()) return null;
        ObjectJsonConverter.merge(object, gStockOptional.get());

        Optional<KillGoods> killGoodsOptional = killGoodsDao.findById(id);
        if (!killGoodsOptional.isPresent()) return object;//不是秒杀商品
        ObjectJsonConverter.merge(object, killGoodsOptional.get());

        Optional<KStock> kStockOptional = kStockDao.findById(id);
        if (!kStockOptional.isPresent()) return null;
        int amount = object.getInteger("amount");
        object.put("amount", amount + kStockOptional.get().getAmount());

        return object;//是秒杀商品
    }

    @Override
    public int kill(String uid, Integer gid) {
        if (uid == null) {
            return KillRes.ERROR;
        }
        return synKill(uid, gid);
    }

    private synchronized int synKill(String uid, Integer gid) {
        Optional<KStock> kStockOptional = kStockDao.findById(gid);
        Optional<KillGoods> killGoodsOptional = killGoodsDao.findById(gid);
        if (!kStockOptional.isPresent() || !killGoodsOptional.isPresent()) {
            return KillRes.KILL_NOT_FOUND;
        }

        KStock kStock = kStockOptional.get();
        KillGoods killGoods = killGoodsOptional.get();
        if (kStock.getAmount() < 1) {
            return KillRes.KILL_NOT_ENOUGH;
        }
        if (System.currentTimeMillis() < killGoods.getStartTime().getTime()) {
            return KillRes.KILL_NOT_BEGIN;
        }
        if (System.currentTimeMillis() > killGoods.getStartTime().getTime()) {
            return KillRes.KILL_ENDED;
        }
        kStock.setAmount(kStock.getAmount() - 1);
        kStockDao.save(kStock);
        chanel.generateOrder().send(MessageBuilder.withPayload(uid+"_"+gid).build());
        return KillRes.KILL_SUCC;
    }
}
