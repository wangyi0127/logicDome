package com.dnsc.logic.controller;

import com.dnsc.logic.entity.Shopp;
import com.dnsc.logic.entity.SpOrders;
import com.dnsc.logic.service.ShoppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingController {

    @Autowired
    private ShoppService shoppService;

    @RequestMapping("/toShopping")
    public String toShopping(Model model){
        List<Shopp> shopps = shoppService.find();
        model.addAttribute("s",shopps);

        return "shopping";
    }
    @RequestMapping("/putOrder")
    public @ResponseBody Map<String,Object> putOrder(HttpSession session, Integer spid, Integer userid, Integer count, double price, double sumprice){
        Map<String,Object> map = new HashMap<>();
        int max=100,min=1;
        int ran2 = (int) (Math.random()*(max-min)+min);
        try {
            SpOrders spOrders = new SpOrders(spid,userid,count,price,sumprice,0);
            map.put("success", true);
            map.put("oid", "SP"+System.currentTimeMillis()+ran2);
            map.put("info", spOrders);
            session.setAttribute("SPINFO", map);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", "订单创建失败");
        }finally {
            return map;
        }

    }

    @RequestMapping("/sendPay")
    public Map<String,Object> sendPay(){
        Map<String,Object> map = new HashMap<>();

        map.put("success", true);
        map.put("message", "支付成功");
        return  map;
    }

}
