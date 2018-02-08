package com.jincin.provider.controller;

import com.jincin.provider.domain.ProviderUser;
import com.jincin.provider.service.UserServiceImpl;
import com.jincin.provider.util.Result;
import com.jincin.provider.util.ResultBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Mr_Li on 2017/7/11.
 */
@RestController
@Api
//@RequestMapping("/provider")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/")
    @ApiOperation(value = "主页")
    public String index(@RequestParam Model model) {
        Result rt = new Result("10000", "额外信息", "只对管理员显示", true);
        model.addAttribute("rt", rt);
        return "index";
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public Result register(@RequestBody ProviderUser providerUser) {
        String result = userServiceImpl.register(providerUser);
        return ResultBuilder.success(result);
    }

    @GetMapping("/login")
    @ApiOperation(value = "登录",notes = "前台页面要验证phone不能为空")
    public Result login(@RequestParam(required = true) String username ,@RequestParam(required = true) String password) {
        /**登录方式1*/
        ProviderUser providerUser = new ProviderUser();
        providerUser.setUsername(username);
        providerUser.setPassword(password);
        String result = userServiceImpl.validate(providerUser);
        return ResultBuilder.success(result);
        //登录方式2
    }

    @GetMapping("/findOne/{UserId}")
    @ApiOperation(value = "查找",notes = "根据userId查找一个user")
    public Result findOne(@RequestParam(required = true) int userId){
        ProviderUser res = userServiceImpl.findOne(userId);
        return ResultBuilder.success(res);
    }
    @GetMapping(value = "/getP")

    @ApiOperation(value = "查找",notes = "根据username查找一个user")
    public Result getP(@RequestParam(required = true) String username, HttpServletRequest request){
//        HttpSession session = httpServletRequest.getSession();
//        List<String> list = (List<String>)session.getAttribute("authorities");
//        for (String a : list){
//            if (("ROLE_SEL").equals(a)){
//                break;
//            }else {
//                throw new LogicException("10001","没有操作权限");
//            }
//        }

        String password = userServiceImpl.getPassword(username);
        return ResultBuilder.success(password);
    }

    @GetMapping("/findAll")
    public Result findAll(){
        List<ProviderUser> list = userServiceImpl.findAll();
        return ResultBuilder.success(list);
    }

}