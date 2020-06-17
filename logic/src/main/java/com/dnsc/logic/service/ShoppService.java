package com.dnsc.logic.service;

import com.dnsc.logic.entity.PayOrders;
import com.dnsc.logic.entity.Shopp;
import com.dnsc.logic.repository.ShoppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppService {

    @Autowired
    private ShoppRepository shoppRepository;

    public List<Shopp> find() {
        List<Shopp> list = null;
        try {
            list = shoppRepository.find();
        } catch (Exception e) {
        }
        return list;
    }
    public int insertOrder(Integer spid,Integer userid,Integer count,double sumprice,Integer status,double price,String oid){
        int a = shoppRepository.insertOrder(spid, userid, count, sumprice, status, price, oid);
        return  a;
    }

    public int insertPayOrder(String orderId,Integer status){
        int a = shoppRepository.insertPayOrder(orderId, status);
        return  a;
    }

    public PayOrders findByPayOrder(String orderId) {
        PayOrders payOrders = null;
        try {
            payOrders = shoppRepository.findByPayOrder(orderId);
        } catch (Exception e) {
        }
        return payOrders;
    }

    public int updateByPayOrder(String orderId){
        int a = shoppRepository.updateByPayOrder(1,orderId);
        return  a;
    }

    public int updateByOrder(String oid){
        int a = shoppRepository.updateByOrder(1,oid);
        return  a;
    }

}
