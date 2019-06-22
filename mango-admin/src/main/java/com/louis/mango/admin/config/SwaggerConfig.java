package com.louis.mango.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author wangkaishuang
 * @Description 启动应用，访问 http://localhost:8080/swagger-ui.html#/,swagger官方文档https://www.swagger.io/docs
 * @Date 2019-06-21 23:56
 * @Param
 * @Return
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("mango后台Api文档")
                .description("google Copyright 2018-2028 (C) All Rights Reserved.")
                .termsOfServiceUrl("https://www.google.com")
                .contact(new Contact("wangkaishuang", null, "ksmasterasy@icloud.com"))
                .build();
    }
}
