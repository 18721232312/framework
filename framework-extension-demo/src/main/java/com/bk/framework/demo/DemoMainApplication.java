package com.bk.framework.demo;

import com.alibaba.fastjson.JSONObject;
import com.bk.framework.demo.dto.UserInfo;
import com.bk.framework.extension.apistart.config.EnableWebApi;
import com.bk.framework.extension.logback.config.EnableLogbackDesensitization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author BK
 * @version V2.0
 * @description: demo应用启动类
 * @date 2019-06-12 23:30
 */
@Slf4j
@EnableWebApi
@EnableLogbackDesensitization(basePackages = {"com.bk.framework.demo"})
@SpringBootApplication(scanBasePackages = {"com.bk.framework.demo"})
public class DemoMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoMainApplication.class, args);
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("s_zhangsan@163.com");
        userInfo.setUserName("张老三");
        userInfo.setSex("男");
        userInfo.setMobile("18955559999");
        log.info("查到的用户信息为:{}" , JSONObject.toJSONString(userInfo));
    }
}
