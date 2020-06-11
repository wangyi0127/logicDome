package com.dnsc.logic.controller;

import com.dnsc.logic.entity.User;
import com.dnsc.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ResetPasswdController {

    @Autowired
    private UserService userService;

    @GetMapping("/showanth")
    public String showAnth(){
        return "anthUser";
    }

    @PostMapping("/anthUser")
    public @ResponseBody Map<String,Object> anthUser(String account,String code, HttpServletRequest request, HttpSession session) {
        Map<String,Object> map = new HashMap<>();

        String session_vcode=(String) request.getSession().getAttribute("text");
        if(!code.equals(session_vcode)){
            map.put("success", false);
            map.put("message", "验证码错误");
            return map;
        }

        User user = userService.findByUser(account);
        // 用户暂存存储
        request.getSession().setAttribute("name", user.getLoginName());

        map.put("success", true);
        map.put("message", "认证成功");
        return map;
    }

    @GetMapping("/showrepass")
    public String showRepasswd(){
        return "resetPasswd";
    }

    @PostMapping("/resetPasswd")
    public @ResponseBody Map<String,Object> resetPasswd(String passwd, String rpasswd, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();

        if(!passwd.equals(rpasswd)){
            map.put("success", false);
            map.put("message", "密码不一致");
            return map;
        }
        int u = userService.updateByUser((String) request.getSession().getAttribute("name"),rpasswd);
        if(u > 0){
            map.put("success", true);
            map.put("message", "密码修改成功");
            return map;
        }else {
            map.put("success", false);
            map.put("message", "密码修改失败");
            return map;
        }

    }
}
