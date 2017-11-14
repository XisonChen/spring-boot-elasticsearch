package com.xison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * spring boot 应用启动类
 * created by Xison Chen
 * on 2017/10/30-1:09
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.xison.repository")
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class,args);
    }
}
