package com.szqz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/*
@MapperScan：标注在 springboot 的启动类上面，配置 basePackages 属性，可以去扫描指定路径下的接口扫描为 Mapper 接口。
@Mapper：标注在接口上，表名这是一个 Mapper 接口。
 */
@MapperScan(basePackages = "com.szqz.mapper")
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
