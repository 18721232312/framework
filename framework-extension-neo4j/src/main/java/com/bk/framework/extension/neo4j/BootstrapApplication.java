package com.bk.framework.extension.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author BK
 * @version V2.0
 * @description: 启动类
 * @date 2019-06-19 23:11
 */
@SpringBootApplication(scanBasePackages = "com.bk.framework.extension.neo4j", excludeName = {"classpath*:application.yml"})
@EnableJpaRepositories()
public class BootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }
}
