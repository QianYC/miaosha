package nju.ycqian.stockservice;

import com.alibaba.fastjson.JSONObject;
import nju.ycqian.stockservice.entity.primary.GStock;
import nju.ycqian.stockservice.entity.primary.Goods;
import nju.ycqian.stockservice.entity.secondary.KillGoods;
import nju.ycqian.stockservice.utils.ObjectJsonConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceApplicationTests {

    @Test
    public void testObjectJsonConverter() {
        Goods goods = new Goods("测试商品1", 14.99f);
        JSONObject object = ObjectJsonConverter.parseObject(goods);
        System.out.println(object.toJSONString());

        GStock gStock = new GStock(0, 1000);
        object = ObjectJsonConverter.merge(object, gStock);
        System.out.println(object.toJSONString());

        Map<Integer, Goods> goodsMap = new HashMap<>();
        goodsMap.put(goods.getId(), goods);
        goods.setPrice(16.09f);
        System.out.println(goodsMap.get(goods.getId()).getPrice());

        KillGoods killGoods = new KillGoods(0, 998f, new Date(9002), new Date(10092));
        System.out.println(ObjectJsonConverter.parseObject(killGoods));
    }
}
