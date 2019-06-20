package nju.ycqian.stockservice.controller;

import com.alibaba.fastjson.JSONObject;
import nju.ycqian.stockservice.entity.primary.Goods;
import nju.ycqian.stockservice.service.In;
import nju.ycqian.stockservice.service.MyMessageChanel;
import nju.ycqian.stockservice.service.StockService;
import nju.ycqian.stockservice.utils.KillRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableBinding(In.class)
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping("")
    public String helloWorld() {
        return "stock service";
    }

    @GetMapping("/hello")
    public void greet() {
        System.out.println("greeting from order service");
    }

    @StreamListener(In.INPUT)
    public void receive(String msg) {
        System.out.println("receive message from order service : " + msg);
    }

    @GetMapping("/goodsList/{type}")
    public JSONObject getGoodsList(@PathVariable("type")String type) {
        JSONObject object = new JSONObject();
        object.put("error_code", 0);
        if ("kill".equals(type)) {
            object.put("data", stockService.getKillGoods());
        } else {
            object.put("data", stockService.getAllGoods());
        }
        return object;
    }

    @GetMapping("/goods/{id}")
    public JSONObject getGoodsDetail(@PathVariable("id") Integer id) {
        JSONObject object = new JSONObject();
        JSONObject goodsDetail = stockService.getGoodsDetail(id);
        object.put("error_code", goodsDetail == null ? 1 : 0);
        if (goodsDetail != null) {
            object.put("data", goodsDetail);
        }
        return object;
    }

    @PostMapping("/goods/kill")
    public JSONObject kill(@RequestParam("gid") Integer gid, @RequestParam("uid") String uid) {
        JSONObject object = new JSONObject();
        int res = stockService.kill(uid, gid);
        object.put("error_code", res);
        if (res != KillRes.KILL_SUCC) {
            object.put("msg", KillRes.getMsg(res));
        }
        return object;
    }
}
