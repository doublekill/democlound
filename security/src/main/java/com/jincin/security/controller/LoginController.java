package com.jincin.security.controller;

import com.jincin.security.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    SessionRegistry sessionRegistry;

    @RequestMapping("/")
    public String index(Model model) {
//        System.err.println(username);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        String role = SimpleGrantedAuthority
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Result msg = new Result("测试","以下是针对用户的额外消息","你好，尊敬的"+role+
                "，这条消息只有充钱的vip才能看到噢"+username,true);
        model.addAttribute("msg", msg);
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
//    List<SessionInformation> sessionInformations = sessionRegistry
//            .getAllSessions(SecurityContextHolder.getContext().getAuthentication()
//                    .getPrincipal(), false);
//        for (SessionInformation sessionInformation : sessionInformations) {
//        sessionInformation.expireNow();
//    }
    @RequestMapping("/403")
    public String error() {
        return "error";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
