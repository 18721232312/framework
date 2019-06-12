package com.bk.framework.extension.logback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author BK
 * @version V2.0
 * @description: 启用logback脱敏功能
 * @date 2019-06-12 23:43
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import({LogbackDesensitizationRegister.class})
public @interface EnableLogbackDesensitization {
    String[] basePackages();
}
