package com.bk.framework.extension.logback;

import com.bk.framework.extension.logback.annotation.Desensitization;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @author BK
 * @version V2.0
 * @description: logback脱敏字段扫描器
 * @date 2019-06-12 23:22
 */
@Slf4j
public class LogbackDesensitizationFieldScanner {

    public volatile static boolean initFlag = false;
    public static final Map<String, Set<String>> CACHE = Maps.newHashMap();
    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    public void scan(Set<String> basePackages) throws BeansException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(Thread.currentThread().getContextClassLoader());
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        for (String basePackage : basePackages) {
            if (StringUtils.isBlank(basePackage)) {
                continue;
            }
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage)) + "/" + DEFAULT_RESOURCE_PATTERN;
            try {
                Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                        String className = metadataReader.getClassMetadata().getClassName();
                        try {
                            doCollection(className);
                        } catch (ClassNotFoundException e) {
                            log.error("检查" + className + "是否含有需要信息失败", e);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("scan fail , ", e);
            }

        }
        initFlag = true;
    }

    /**
     * 收集被票房的敏感字段
     *
     * @param fullClassName class全路径名称
     */
    private void doCollection(String fullClassName) throws ClassNotFoundException {
        Class<?> clz = Class.forName(fullClassName);
        Field[] fields = clz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Desensitization.class)) {
                Desensitization anno = field.getAnnotation(Desensitization.class);
                Set<String> context = CACHE.getOrDefault(anno.type().toString(), Sets.newHashSet());
                context.add(field.getName());
                CACHE.put(anno.type().toString(), context);
                log.info(":" + fullClassName + "." + field.getName());
            }
        }
    }
}
