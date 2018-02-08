package com.jincin.security.controller;

import com.jincin.security.domain.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String index(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Result msg = new Result("测试","以下是针对"+role+"的额外消息","你好，尊敬的"+role+
                "，这条消息只有充钱的vip才能看到噢"+username,true);
        model.addAttribute("msg", msg);
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Result msg = new Result("测试",role+",","你好，尊敬的"+username,true);
        model.addAttribute("msg", msg);
        return "hello";
    }

    @RequestMapping("/403")
    public String error() {
        return "error";
    }

    @RequestMapping("/login")
    public String login() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        HttpSession session = httpServletRequest.getSession();
//        session.setAttribute("authorities", authentication.getAuthorities());
        return "login";
    }



    //    List<SessionInformation> sessionInformations = sessionRegistry
//            .getAllSessions(SecurityContextHolder.getContext().getAuthentication()
//                    .getPrincipal(), false);
//        for (SessionInformation sessionInformation : sessionInformations) {
//        sessionInformation.expireNow();
//    }
}
