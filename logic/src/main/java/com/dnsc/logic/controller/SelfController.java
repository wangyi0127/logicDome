package com.dnsc.logic.controller;

import com.dnsc.logic.entity.User;
import com.dnsc.logic.service.UserService;
import com.dnsc.logic.service.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SelfController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getSelfInfo")
    public String getSelfInfo(HttpServletRequest request, HttpSession session, Model model){

        User user = userService.findByUser((String) session.getAttribute(WebSecurityConfig.SESSION_KEY));
        model.addAttribute("s",user);
        return "selfInfo";
    }

    @RequestMapping("/updateInfo")
    public @ResponseBody Map<String,Object> updateInfo(Integer id, String name, Integer gid, String cid){
        Map<String,Object> map = new HashMap<>();
        int s = userService.updateByUserInfo(id,name,gid,cid);
        if(s>0){
            map.put("success", true);
            map.put("message", "更新成功");
            return map;
        }else {
            map.put("success", false);
            map.put("message", "更新失败");
            return map;
        }
    }
}
