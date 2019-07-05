package com.gsta.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * springboot启动类 . <br>
 * ServletComponentScan表示开启servlet的注解 <br>
 * MapperScan表示扫描mybatis的mapper <br>
 * 
 * @author hkb
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.gsta.app.dao")
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    /**
     * 启动入口,运行此类即可运行整个项目
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
