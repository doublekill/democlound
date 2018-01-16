package com.jincin.book.controller;

import com.jincin.book.util.Result;
import com.jincin.book.util.ResultBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class CallController {
    @Bean
    @LoadBalanced
    public RestTemplate getRTL(){
        return new RestTemplate();
    }

    @GetMapping("/book/findAll.json")
    @ApiOperation(value = "调用Provider",response = Result.class)
    public Result call2(){
        RestTemplate rt = getRTL();
        Result res = rt.getForObject("http://provider/findAll", Result.class);
        return ResultBuilder.success(res);
    }
}
