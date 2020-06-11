package com.dnsc.logic.service;

import com.dnsc.logic.entity.User;
import com.dnsc.logic.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRep userRepositoty;

    public List<User> find() {
        List<User> list = null;
        try {
            list = userRepositoty.find();
        } catch (Exception e) {
        }
        return list;
    }

    public User findByUser(String name) {
        User user = null;
        try {
            user = userRepositoty.findByUser(name);
        } catch (Exception e) {
        }
        return user;
    }

    public int updateByUser(String name ,String password){
        int a = userRepositoty.updateByUser(name,password);
        return a;
    }

    public int updateByUserInfo(Integer id ,String name, Integer gid, String cid){
        int a = userRepositoty.updateByUserInfo(id,name,gid,cid);
        return a;
    }
}
