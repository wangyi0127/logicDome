package com.dnsc.logic.controller;

import com.dnsc.logic.entity.PayOrders;
import com.dnsc.logic.entity.Shopp;
import com.dnsc.logic.entity.SpOrders;
import com.dnsc.logic.service.ShoppService;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingController {

    @Autowired
    private ObjectMapper mapper;

    final Base64.Decoder decoder = Base64.getDecoder();

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
            SpOrders spOrders = new SpOrders("SP"+System.currentTimeMillis()+ran2,spid,userid,count,price,sumprice,0);
            int i = shoppService.insertOrder(spid, userid, count, sumprice, 0, price, "SP"+System.currentTimeMillis()+ran2);
            if(i>0){
                map.put("success", true);
                map.put("message", "成功创建订单");
                map.put("info", spOrders);
                session.setAttribute("SPINFO", map);
            }else {
                map.put("success", false);
                map.put("message", "订单创建失败");
            }
        }catch (Exception e){
            map.put("success", false);
            map.put("message", "订单创建失败");
        }finally {
            return map;
        }

    }

    @RequestMapping("/spPayOk")
    public @ResponseBody Map<String,Object> spPayOk(String info) throws IOException {
        SpOrders spOrders= mapper.readValue(decoder.decode(info), SpOrders.class);
        Map<String,Object> map = new HashMap<>();
        int i = shoppService.updateByOrder(spOrders.getOid());
        if(i>0){
            map.put("success", true);
            map.put("message", "返回商城》》》");
            return  map;
        }else {
            map.put("success", false);
            map.put("message", "已支付成功，但订单状态刷新失败");
            return  map;
        }
    }

    //第三方pc
    @RequestMapping("/payModel")
    public String payMvc(String info, Model model) throws IOException {
        SpOrders spOrders= mapper.readValue(decoder.decode(info), SpOrders.class);
        shoppService.insertPayOrder(spOrders.getOid(), 0);
        model.addAttribute("payCode","/createQrCode?url=http://192.168.12.37:8080/actionPay?info="+info);
        model.addAttribute("info",info);
        return "payModel";
    }


//第三方pc
    @RequestMapping("/getPayStatus")
    public @ResponseBody Map<String,Object> getPayStatus(String oid){
        Map<String,Object> map = new HashMap<>();

        PayOrders payOrders = shoppService.findByPayOrder(oid);
        if(payOrders.getStatus() == 1){
            map.put("success", true);
            map.put("message", "支付成功");
            return  map;
        }else {
            map.put("success", false);
            map.put("message", "未付款");
            return  map;
        }
    }
//第三方移动
    @RequestMapping("/actionPay")
    public String actionPay(String info,Model model){
        model.addAttribute("info",info);
        return  "actionpay";
    }
//第三方移动
    @RequestMapping("/sendPay")
    public @ResponseBody Map<String,Object> sendPay(String oid){
        Map<String,Object> map = new HashMap<>();
        //修改payorders表中数据状态
        int i = shoppService.updateByPayOrder(oid);
        if(i>0){
            map.put("success", true);
            map.put("message", "支付成功");
            map.put("info", oid);
            return  map;
        }else {
            map.put("success", false);
            map.put("message", "支付失败");
            map.put("info", oid);
            return  map;
        }

    }

}
