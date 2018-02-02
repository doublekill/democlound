package com.jincin.hello.Controller;

import com.jincin.hello.util.Result;
import com.jincin.hello.util.ResultBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {

    @GetMapping("/findAll")
    public Result findAll(){
        return ResultBuilder.success("hello-provider-1");
    }

    @GetMapping("/findOne/{userId}")
    public Result getOne(@PathVariable int userId){
        return ResultBuilder.success(userId);
    }

//    @Secured({ "ROLE_DBA", "ROLE_ADMIN" })
    @GetMapping(value = "/findOne1")
    public Result addOne(){
        return ResultBuilder.success("s");
    }

}
