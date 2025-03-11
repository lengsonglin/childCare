package com.cqut.childcare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.cqut.childcare.**.mapper"})
@SpringBootApplication
public class ChildCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildCareApplication.class, args);
    }

}
