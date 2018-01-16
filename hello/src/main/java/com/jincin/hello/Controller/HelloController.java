package com.jincin.hello.Controller;

import com.jincin.hello.FeignClient.HelloClient;
import com.jincin.hello.util.Result;
import com.jincin.hello.util.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    HelloClient helloClient;

//    @Bean
//    public RestTemplate getRTL(){
//        return new RestTemplate();
//    }

    @GetMapping("/findAll")
    public Result findAll(){
        return ResultBuilder.success("hello-provider-1");
    }

    @GetMapping("/findOne")
    public Result getOne(@RequestParam int userId){
        return helloClient.findAll();
    }

}
