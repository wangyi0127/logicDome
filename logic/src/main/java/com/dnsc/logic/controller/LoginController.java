package com.dnsc.logic.controller;

import com.dnsc.logic.entity.User;
import com.dnsc.logic.service.UserService;
import com.dnsc.logic.service.WebSecurityConfig;
import com.dnsc.logic.tools.CreateImgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
        model.addAttribute("name", account);
        User user = userService.findByUser(account);
        model.addAttribute("s",user);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginPost")
    public @ResponseBody Map<String, Object> loginPost(String account, String password, String code, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        User user = userService.findByUser(account);
        String session_vcode=(String) request.getSession().getAttribute("text");    //从session中获取真正的验证码
        if (!code.equals(session_vcode)) {
            map.put("success", false);
            map.put("message", "验证码错误");
            return map;
        }

        if (!password.equals(user.getPassWord())) {
            map.put("success", false);
            map.put("message", "密码错误");
            return map;
        }

        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, user.getLoginName());

        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }

    @PostMapping("/getCode")
    public @ResponseBody Map<String, Object> getCode(String account, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        CreateImgCode ivc = new CreateImgCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();  //获取验证码
        request.getSession().setAttribute("text", ivc.getText()); //将验证码的文本存在session中

        User user = userService.findByUser(account);
        // 用户暂存存储
        request.getSession().setAttribute("name", user.getLoginName());

        map.put("success", true);
        map.put("message", "验证码已发送");
        return map;
    }

    @PostMapping("/loginPost1")
    public @ResponseBody Map<String, Object> loginPost1(String account,String code, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String session_vcode=(String) request.getSession().getAttribute("text");    //从session中获取真正的验证码
        if (!code.equals(session_vcode)) {
            map.put("success", false);
            map.put("message", "验证码错误");
            return map;
        }

        User user = userService.findByUser(account);
        System.out.print(user.getLoginName());
        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, user.getLoginName());

        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }

}