package com.bk.framework.extension.logback.config;

import com.bk.framework.extension.logback.LogbackDesensitizationFieldScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author BK
 * @version V2.0
 * @description: logback脱敏配置
 * @date 2019-06-12 23:49
 */
@Configuration
public class LogbackDesensitizationRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> enableAttrMap = metadata.getAnnotationAttributes(EnableLogbackDesensitization.class.getName());
        AnnotationAttributes enableAttrs = AnnotationAttributes.fromMap(enableAttrMap);
        String[] basePackagesArray = enableAttrs.getStringArray("basePackages");
        Set<String> basePackages = new LinkedHashSet<>();
        for (String pkg : basePackagesArray) {
            String[] tokenized = StringUtils.tokenizeToStringArray(pkg, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
            basePackages.addAll(Arrays.asList(tokenized));
        }
        if (basePackages.isEmpty()) {
            basePackages.add(ClassUtils.getPackageName(metadata.getClassName()));
        }
        new LogbackDesensitizationFieldScanner().scan(basePackages);
    }
}