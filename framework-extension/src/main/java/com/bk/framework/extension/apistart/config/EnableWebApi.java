package com.bk.framework.extension.apistart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author BK
 * @version V2.0
 * @description: 启用API接入
 * @date 2019/5/15 14:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import({ApiImportSelector.class})
public @interface EnableWebApi {

}
