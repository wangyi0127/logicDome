package com.dnsc.logic.controller;


import com.dnsc.logic.entity.User;
import com.dnsc.logic.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/getList")
    @ResponseBody
    public List<User> showList() throws JSONException {
        List<User> list = userService.find();
        return list;
    }
}
