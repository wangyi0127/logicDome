package com.dnsc.logic.controller;

import com.dnsc.logic.entity.Shopp;
import com.dnsc.logic.service.ShoppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

}
