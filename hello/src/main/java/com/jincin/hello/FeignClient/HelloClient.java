package com.jincin.hello.FeignClient;

import com.jincin.hello.util.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Provider")
public interface HelloClient {
        @RequestMapping(value = "/findAll",method = RequestMethod.GET)
        Result findAll();
//        Result findOne(@PathVariable(value = "userId") int userId);
}
