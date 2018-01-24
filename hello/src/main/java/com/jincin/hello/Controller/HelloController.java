package com.jincin.hello.Controller;

import com.jincin.hello.FeignClient.HelloClient;
import com.jincin.hello.util.Result;
import com.jincin.hello.util.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/findOne1")
    public Result addOne(){
        return ResultBuilder.success("呦西");
    }

}
