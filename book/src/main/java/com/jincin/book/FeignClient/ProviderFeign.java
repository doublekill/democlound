package com.jincin.book.FeignClient;

import com.jincin.book.util.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Provider")
public interface ProviderFeign {
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    Result findAll();
    @RequestMapping(value = "/call",method = RequestMethod.GET)
    String call();
}
