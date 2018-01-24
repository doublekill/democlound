package com.jincin.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Created by MarMing on 2017/5/15.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket initSwagger() {
        ApiInfo apiInfo = new ApiInfoBuilder().
                title("Book测试API").
                description("" +
//                        "1、所有关于金额的请求和响应单位均为分，需前端自行转换<br/>" +
//                        "2、所有概率性质的响应参数均为0.01/0.90格式，并不是1/90，需前端自行转换<br/>" +
//                        "3、errorCode = alipay-insufficient-permissions，支付宝授权权限不足需要弹窗询问是否进行重新授权"
                "这里应该写介绍，然后假装这里有介绍。"
                ).build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
//                .globalOperationParameters(new ArrayList<Parameter>(){{add(
//                        new ParameterBuilder().name("token").defaultValue("b0cd07096e22ba272d12dc5636c75e97").description("token默认值会失效-新值可以从浏览器本地存储中取").modelRef(new ModelRef("string")).parameterType("header").required(false).build()
//                );}})
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jincin.book.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
