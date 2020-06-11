package com.dnsc.logic.service;

import com.dnsc.logic.entity.Shopp;
import com.dnsc.logic.entity.User;
import com.dnsc.logic.repository.ShoppRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
