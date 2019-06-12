package com.bk.framework.extension.apistart.core.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BK
 * @version V2.0
 * @description: bean工具类
 * @date 2018/9/10 15:30
 */
@Slf4j
public class BeanUtils {
    /**
     * Json格式转换成Bean
     *
     * @param jsonString json格式的字符串
     * @param clazz      转换成的类
     * @param <T>        类泛型
     * @return
     */
    public static <T> T toBean(String jsonString, Class<T> clazz) {
        T t = null;
        if (!StringUtils.isEmpty(jsonString)) {
            t = JSON.parseObject(jsonString, clazz);
        }
        return t;
    }

    /**
     * 将bean转换成Map
     *
     * @param bean 需要转换的bean实例
     * @return
     */
    public static Map<String, String> beanToMap(Object bean) {
        if (bean == null) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(bean);
                    map.put(key, value == null ? "" : value.toString());
                }
            }
        } catch (Exception e) {
            log.error("bean transfer to map has Error : " + JSON.toJSONString(bean), e);
        }
        return map;
    }
}
