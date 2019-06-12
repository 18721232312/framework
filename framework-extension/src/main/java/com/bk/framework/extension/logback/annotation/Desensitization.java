package com.bk.framework.extension.logback.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author BK
 * @version V2.0
 * @description: 标记需要脱敏字段
 * @date 2019-06-12 23:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Desensitization {
    String type() default "";
}
