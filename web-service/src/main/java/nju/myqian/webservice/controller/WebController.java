package nju.myqian.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = {"/index", "/"})
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/goodDetail")
    public String getGoodDetailPage() {
        return "goodDetail";
    }

    @GetMapping("/orderDetail")
    public String getOrderDetailPage() {
        return "orderDetail";
    }

    @GetMapping("/orders")
    public String getOrdersPage() {
        return "orders";
    }
}
