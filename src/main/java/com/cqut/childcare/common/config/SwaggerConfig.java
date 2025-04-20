package com.cqut.childcare.common.config;


import com.cqut.childcare.common.intecepter.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-03-23
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean(value = "defaultApi")
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                //配置网站的基本信息
                .apiInfo(new ApiInfoBuilder()
                        //网站标题
                        .title("childCare接口文档")
                        //标题后面的版本号
                        .version("v1.0")
                        .description("childCare接口文档")
                        //联系人信息
                        .contact(new Contact("faiz", "", ""))
                        .build())
                .select()
                //指定接口的位置
                .apis(RequestHandlerSelectors
                        .withClassAnnotation(RestController.class)
                )
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(getGlobalRequestParameters());

    }

    //生成全局通用参数
    private List<Parameter> getGlobalRequestParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name(TokenInterceptor.AUTHORIZATION_HEADER)
                .description("认证")
                .parameterType("header")
                .modelRef(new ModelRef("string"))
                .required(false)
                .build());
        return parameters;
    }
}
