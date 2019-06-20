package com.bk.framework.extension.neo4j.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BK
 * @version V2.0
 * @description: 断言工具类
 * @date 2019/6/19 9:57
 */
public class AssertUtils {

    private static final String V_NUMBER = "^([+-]?)\\d*\\.?\\d+$";

    /**
     * 断言字符串不能为空白
     *
     * @param value   传入对象
     * @param message 错误消息
     */
    public static void assertNotBlank(String value, String message) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言集合不为空
     *
     * @param coll 集合
     * @return
     */
    public static void isNotEmpty(Collection coll, String message) {
        if (!(coll == null || coll.isEmpty())) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言不为空
     *
     * @param value   传入对象
     * @param message 错误消息
     * @throws IllegalArgumentException
     */
    public static void assertNotNull(Object value, String message) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言字符串可以转换成Long
     *
     * @param value   传入对象
     * @param message 错误消息
     * @throws IllegalArgumentException
     */
    public static void assertIsLong(String value, String message) throws IllegalArgumentException {
        try {
            Long.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言Long类型不粉空不为0
     *
     * @param value   传入对象
     * @param message 错误消息
     * @throws IllegalArgumentException
     */
    public static void assertIsLong(Long value, String message) throws IllegalArgumentException {
        if (value == null || value == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertIsNumber(String value, String message) {
        if (StringUtils.isBlank(value) || !match(V_NUMBER, value)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * @param regex 正则表达式字符串
     * @param value 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
